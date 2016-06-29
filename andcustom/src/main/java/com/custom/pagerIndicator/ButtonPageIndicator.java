package com.custom.pagerIndicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.custom.andcustom.R;


/**
 * Created by tandewei on 2016/6/28.
 */
public class ButtonPageIndicator extends BasePageIndicator {
    int pageCount;
    int currentPageIndex;

    Button first;
    Button previous;
    Button next;
    Button last;
    EditText targetIndex;
    TextView total;
    Button change;

    Context mContext;

    public ButtonPageIndicator(Context context) {
        super(context);
    }

    public ButtonPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        this.setLayoutParams(lp);
        this.setOrientation(HORIZONTAL);
        initView();
    }

    @Override
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public int getPageCount() {
        return pageCount;
    }

    @Override
    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    private void initView() {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        LinearLayout layout = (LinearLayout) mInflater.inflate(R.layout.pageindicator_button, null);
        first = (Button) layout.findViewById(R.id.first);
        previous = (Button) layout.findViewById(R.id.previous);
        next = (Button) layout.findViewById(R.id.next);
        last = (Button) layout.findViewById(R.id.last);
        targetIndex = (EditText) layout.findViewById(R.id.target_page);
        total = (TextView) layout.findViewById(R.id.total);
        change = (Button) layout.findViewById(R.id.change);

        first.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                first();
                changeCurrent(getCurrentPageIndex());
            }
        });

        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
                changeCurrent(getCurrentPageIndex());
            }
        });

        previous.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                previous();
                changeCurrent(getCurrentPageIndex());
            }
        });

        last.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                last();
                changeCurrent(getCurrentPageIndex());
            }
        });

        change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int target = Integer.valueOf(targetIndex.getText().toString());
                setCurrentPageIndex(target);
            }
        });
        this.addView(layout);
    }

    private void changeCurrent(int pageIndex) {
        int current = Integer.valueOf(targetIndex.getText().toString());
        if (current != pageIndex) {
            targetIndex.setText(pageIndex);
        }
    }

}
