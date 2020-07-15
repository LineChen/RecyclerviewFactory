package com.line.recyclerview.factory.factory;


import androidx.annotation.NonNull;

import com.line.recyclerview.factory.MultiTypeListView;


/**
 * Created by chenliu on 2019-10-16.
 */
public final class MultiTypeListViewFactory {
    public static MultiTypeListView getMultiTypeListView(@NonNull BaseListViewFactory factory) {
        return factory.get();
    }
}
