package com.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.custom.adapter.ListTableAdapter;
import com.custom.andcustom.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tandewei on 2016/6/27.
 */
public class ListTableView extends LinearLayout {

    Context mContext;
    ListTableAdapter mAdapter;

    TableLayout body;
    TableLayout header;

    int rowSize; //行数, 若<=0, 则显示全部

    public ListTableView(Context context) {
        this(context, null);
    }

    public ListTableView(Context context, AttributeSet attrs) {
        super( context, attrs );
        mContext = context;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(lp);
        this.setOrientation(VERTICAL);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ListTableView);
        rowSize = array.getInt(R.styleable.ListTableView_rowSize, 5);
        Log.d("test ListTableView", "rowsize=" + rowSize);

        header = initHeader();
        body = initBody();
    }



    public void setmAdapter(ListTableAdapter mAdapter) {
        this.mAdapter = mAdapter;
        TableRow tr = mAdapter.getHeader();
        if (tr != null && header != null) {
            header.addView(tr);
            Log.d("ttt", "height:" + header.getHeight());
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        Log.d("test onLayout", "rowsize=" + rowSize);
        List<Integer> colWidths = new LinkedList<Integer>();

        if (body.getChildCount() > 0) {

            for (int rownum = 0; rownum < 1; rownum++) {
                TableRow row = (TableRow) body.getChildAt(rownum);
                for (int cellnum = 0; cellnum < row.getChildCount(); cellnum++) {
                    View cell = row.getChildAt(cellnum);
                    TableRow.LayoutParams params = (TableRow.LayoutParams) cell.getLayoutParams();
                    Integer cellWidth = params.span == 1 ? cell.getWidth() : 0;
                    if (colWidths.size() <= cellnum) {
                        colWidths.add(cellWidth);
                    } else {
                        Integer current = colWidths.get(cellnum);
                        if (cellWidth > current) {
                            colWidths.remove(cellnum);
                            colWidths.add(cellnum, cellWidth);
                        }
                    }
                }
            }

            for (int rownum = 0; rownum < header.getChildCount(); rownum++) {
                TableRow row = (TableRow) header.getChildAt(rownum);
                for (int cellnum = 0; cellnum < row.getChildCount(); cellnum++) {
                    View cell = row.getChildAt(cellnum);
                    TableRow.LayoutParams params = (TableRow.LayoutParams) cell.getLayoutParams();
                    params.width = 0;
                    for (int span = 0; span < params.span; span++) {
                        params.width += colWidths.get(cellnum + span);
                    }
                }
            }
        }
    }

    private TableLayout initHeader() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        TableLayout header = new TableLayout(mContext);
        header.setLayoutParams(lp);
        header.setStretchAllColumns(true);
        header.setShrinkAllColumns(true);
        this.addView(header, lp);

        return header;
    }

    private TableLayout initBody() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ScrollView sv = new ScrollView(mContext);
        sv.setLayoutParams(lp);
        TableLayout body = new TableLayout(mContext);
        body.setLayoutParams(lp);
        body.setStretchAllColumns(true);
        body.setShrinkAllColumns(true);
        sv.addView(body, lp);
        this.addView(sv, lp);

        return body;
    }

    public void loadData() {
        int size = rowSize <= 0? mAdapter.getCount():rowSize;
        size = size > mAdapter.getCount()?mAdapter.getCount():size;
        TableRow row;
        for (int i = 0; i < size; ++i) {
            row = mAdapter.getRow(i);
            if (row != null && body != null) {
                body.addView(row);
            }
            Log.d("ttt", "height bbody:" + body.getHeight());
        }

    }
}
