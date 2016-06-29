package com.custom.listableviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TableRow;
import android.widget.TextView;

import com.custom.listableviewdemo.entity.AlertDefine;
import com.custom.listableviewdemo.entity.IRule;
import com.custom.pagerIndicator.ButtonPageIndicator;
import com.custom.pagerIndicator.listener.CurrentPageIndexChangeListener;
import com.custom.adapter.ListTableAdapter;
import com.custom.view.ListTableView;
import com.custom.view.cell.MethodNameValueFactory;
import com.custom.view.cell.PropertyValueFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tandewei on 2016/6/28.
 */
public class ActivityTestListTableView extends Activity{
    Activity activity;
    ListTableView listTableView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listtableview);
        activity = this;
        initListTable();
        initPageIndicator();
    }

    private void initListTable() {
        listTableView = (ListTableView) findViewById(R.id.listTableView);

        String id1 = "12345";
        String title1 = "title1";
        int category1 = 1;
        int level1 = 2;
        String descriptionPattern1 = "descriptionPattern1";
        IRule rule1 = new IRule() {
            @Override
            public boolean check() {
                return false;
            }

            @Override
            public String getName() {
                return "iurlename";
            }
        };
        boolean enable1 = true;

        AlertDefine alertDefine = new AlertDefine(id1,title1,category1,level1,descriptionPattern1,rule1, enable1);

        List<AlertDefine> alertDefines = new LinkedList<>();
        alertDefines.add(alertDefine);

        ListTableAdapter<AlertDefine> adapter = new ListTableAdapter<AlertDefine>(this, alertDefines) {
            LayoutInflater mInflater = LayoutInflater.from(activity);
            PropertyValueFactory<AlertDefine, String > factory_id = new PropertyValueFactory<>("id");
            PropertyValueFactory<AlertDefine, String > factory_title = new PropertyValueFactory<>("title");
            PropertyValueFactory<AlertDefine, Integer > factory_category = new PropertyValueFactory<>("category");
            PropertyValueFactory<AlertDefine, Integer > factory_level = new PropertyValueFactory<>("level");
            PropertyValueFactory<AlertDefine, String > factory_descriptionPattern = new PropertyValueFactory<>("descriptionPattern");
            PropertyValueFactory<AlertDefine, IRule > factory_rule = new PropertyValueFactory<>("rule");
            MethodNameValueFactory<AlertDefine, Boolean > factory_enable = new MethodNameValueFactory<>("isEnable");
            @Override
            public TableRow getRow(int index) {
                TableRow tableRow;
                TableRow.LayoutParams tlp = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tableRow = (TableRow) mInflater.inflate(R.layout.alertdefine_row, null);
                tableRow.setLayoutParams(tlp);

                TextView id = (TextView) tableRow.findViewById(R.id.id_alertdefine);
                TextView title = (TextView) tableRow.findViewById(R.id.title_alertdefine);
                TextView category = (TextView) tableRow.findViewById(R.id.category_alertdefine);
                TextView level = (TextView) tableRow.findViewById(R.id.level_alertdefine);
                TextView description = (TextView) tableRow.findViewById(R.id.description_alertdefine);
                TextView rule = (TextView) tableRow.findViewById(R.id.rule_alertdefine);
                TextView enable = (TextView) tableRow.findViewById(R.id.enable_alertdefine);

                AlertDefine alertDefine = getData(index);
                id.setText(factory_id.getValue(alertDefine));
                title.setText(factory_title.getValue(alertDefine));
                int category1 = factory_category.getValue(alertDefine);
                category.setText(category1 + "");
                level.setText(factory_level.getValue(alertDefine) + "");
                description.setText(factory_descriptionPattern.getValue(alertDefine));
                rule.setText(factory_rule.getValue(alertDefine).getName());
                Boolean b = factory_enable.getValue(alertDefine);
                enable.setText(b.booleanValue()?"true":"false");
                return tableRow;
            }

            @Override
            public AlertDefine getData(int index) {
                return super.getData(0);
            }

            @Override
            public int getCount() {
                return 15;
            }

            @Override
            public TableRow getHeader() {
                TableRow header;
                header = (TableRow) mInflater.inflate(R.layout.alertdefine_header, null);
                return header;
            }
        };
        listTableView.setmAdapter(adapter);
        listTableView.loadData();
    }

    private void initPageIndicator() {
        ButtonPageIndicator pageIndicator = (ButtonPageIndicator) findViewById(R.id.pageIndicator);
        pageIndicator.setPageCount(20);
        pageIndicator.addChangeListener(new CurrentPageIndexChangeListener() {
            @Override
            public void changed(int oldValue, int newValue) {
                Log.d("ttt","old:" + oldValue + ",new:" + newValue);
            }
        });
    }
}
