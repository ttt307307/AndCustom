package com.custom.pagerIndicator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by tandewei on 2016/6/28.
 */
public abstract class BasePageIndicator extends LinearLayout implements IPageIndicator {

    CurrentPageIndexChangeListener changeListener;

    public BasePageIndicator(Context context) {
        this(context, null);
    }

    public BasePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int next() {
        return setCurrentPageIndex(getCurrentPageIndex() + 1);
    }

    @Override
    public int previous() {
        return setCurrentPageIndex(getCurrentPageIndex() - 1);
    }

    @Override
    public int last() {
        return setCurrentPageIndex(getPageCount());
    }

    @Override
    public int first() {
        return setCurrentPageIndex(0);
    }

    @Override
    public int setCurrentPageIndex(int pageIndex) {
        int oldPageIndex = getCurrentPageIndex();
        int newPagIndex = pageIndex;
        if (changeListener != null) {
            changeListener.changed(oldPageIndex, newPagIndex);
        }
        return pageIndex;
    }

    public void addChangeListener(CurrentPageIndexChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public void removeChangeListener() {
        this.changeListener = null;
    }
}
