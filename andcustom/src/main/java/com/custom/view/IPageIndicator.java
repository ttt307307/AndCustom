package com.custom.view;

/**
 * Created by tandewei on 2016/6/27.
 */
public interface IPageIndicator {
    //获取当前页
    int getCurrent();
    //获取总数
    int getTotal();
    //下一页
    boolean next();
    //上一页
    boolean last();
}
