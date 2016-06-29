package com.custom.view.cell;

import com.custom.util.ObjUtil;

/**
 * Created by tandewei on 2016/6/28.
 */
public class PropertyValueFactory<S, R> implements ICellValueFactory<S, R> {

    @Override
    public R getValue(S o) {
        if (property == null || property.isEmpty() || o == null) {
            return null;
        }
        try {
            return (R) ObjUtil.getFieldValueByName(property, o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PropertyValueFactory(String property) {
        this.property = property;
    }

    private final String property;
}
