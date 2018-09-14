package com.example.administrator.osc.base;

import android.content.Context;
import android.view.View;

public abstract class BaseViewHolder<T> {
    private final View rootView;
    protected final Context mc;
    private T data;

    public BaseViewHolder(Context mc) {
        this.mc = mc;
        rootView = initView();
        rootView.setTag(this);
    }

    public abstract View initView();

    public View getRootView() {
        return rootView;
    }

    public void setData(T data) {
        this.data = data;
        refreshView(data);
    }

    public T getData() {
        return data;
    }

    public abstract void refreshView(T data);

}
