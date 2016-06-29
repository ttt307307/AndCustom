package com.custom.pagerIndicator;

/**
 * Created by tandewei on 2016/6/28.
 */
public interface IPageIndicator {
    void setPageCount(int pageCount);
    int getPageCount();
    int getCurrentPageIndex();
    int setCurrentPageIndex(int pageIndex);
    int next();
    int previous();
    int first();
    int last();
}
