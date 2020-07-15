package com.line.recyclerview.factory.factory;

import android.content.Context;

import com.line.recyclerview.factory.MultiTypeListView;


/**
 * Created by chenliu on 2019-10-16.
 */
public abstract class BaseListViewFactory {
    protected Context context;

    public BaseListViewFactory(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract MultiTypeListView get();
}
