package com.custom.view.cell;

/**
 * Created by tandewei on 2016/6/28.
 */
public interface ICellValueFactory<S, R> {
    R getValue(S s);
}
