package com.line.recyclerviewfactory;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.line.recyclerview.factory.MultiTypeListView;
import com.line.recyclerview.factory.factory.BaseListViewFactory;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by chenliu on 2020/7/14.
 */
public class SeaFactory extends BaseListViewFactory {

    private SeaDataSource liveDataSource = new SeaDataSource();

    public SeaFactory(Context context) {
        super(context);
    }

    @Override
    public MultiTypeListView get() {

        MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter();
        multiTypeAdapter.register(ItemSea.class, new ItemSeaBinder());
        TextView errorView = new TextView(context);
        errorView.setBackgroundColor(Color.RED);
        TextView emptyView = new TextView(context);
        emptyView.setBackgroundColor(Color.GRAY);
        emptyView.setText("暂无数据");
        return MultiTypeListView.get(context, multiTypeAdapter, emptyView, errorView, liveDataSource);
    }

    public void insert(int position) {
        liveDataSource.insert(position);
    }

    public void remove(int position) {
        liveDataSource.remove(position);
    }
}
