package com.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TableRow;

import java.util.List;

/**
 * Created by tandewei on 2016/6/28.
 */
public class ListTableAdapter<T> {
    LayoutInflater mInflater;

    List<T> alertDefines;

    public ListTableAdapter(Context context, List<T> alertDefines) {
        this.alertDefines = alertDefines;
        this.mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return alertDefines == null?0:alertDefines.size();
    }

    public T getData(int index){
        return alertDefines == null?null:alertDefines.get(index);
    }

    public TableRow getRow(int index) {
        TableRow tableRow = null;
//        TableRow.LayoutParams tlp = new TableRow.LayoutParams(
//                TableRow.LayoutParams.WRAP_CONTENT,
//                TableRow.LayoutParams.WRAP_CONTENT);
//        tableRow = (TableRow) mInflater.inflate(R.layout.mytable_row, null);
//        tableRow.setLayoutParams(tlp);
        return tableRow;
    }

    public TableRow getHeader() {
        return null;
    }
}
