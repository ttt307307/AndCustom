package com.custom.view.cell;

import com.custom.util.ObjUtil;

/**
 * Created by tandewei on 2016/6/28.
 */
public class MethodNameValueFactory <S, R> implements ICellValueFactory<S, R> {

    @Override
    public R getValue(S o) {
        if (methodName == null || methodName.isEmpty() || o == null) {
            return null;
        }
        try {
            return (R) ObjUtil.getValueByMethodName(o, methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MethodNameValueFactory(String methodName) {
        this.methodName = methodName;
    }

    private final String methodName;
}