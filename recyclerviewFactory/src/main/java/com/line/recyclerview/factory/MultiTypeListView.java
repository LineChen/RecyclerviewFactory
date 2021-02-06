package com.line.recyclerview.factory;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;

import com.line.recyclerview.factory.databinding.LayoutMultiListViewBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.xiaoyu.lib.statuspage.PageLayout;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by chenliu on 2019-10-15.
 */
public class MultiTypeListView extends FrameLayout {

    private static final String TAG = "MultiTypeListView";

    public MultiTypeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private LayoutMultiListViewBinding binding;
    private View emptyView;
    private View errorView;
    private PageLayout pageLayout;
    private ListViewDataSource listViewDataSource;
    private MultiTypeAdapter multiTypeAdapter;


    public static MultiTypeListView get(Context context, @NonNull MultiTypeAdapter multiTypeAdapter, View emptyView, View errorView, @NonNull ListViewDataSource listViewDataSource) {
        LayoutMultiListViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_multi_list_view, new FrameLayout(context), false);
        MultiTypeListView listView = (MultiTypeListView) binding.getRoot();
        listView.binding = binding;
        listView.multiTypeAdapter = multiTypeAdapter;
        listView.emptyView = emptyView;
        listView.errorView = errorView;
        listView.listViewDataSource = listViewDataSource;
        listView.multiTypeAdapter.setItems(listView.listViewDataSource.getCurrentList());
        listView.init();
        return listView;
    }

    private void init() {
        //page layout
        pageLayout = new PageLayout.Builder(getContext())
                .initPage(binding.content)
                .setCustomView(emptyView)
                .setLoading(R.layout.layout_loading_theme, R.id.tv_page_loading_blink)
                .setError(errorView)
                .create();
        pageLayout.hide();

        errorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pageLayout.hide();
                binding.smartRefresh.autoRefresh();
            }
        });

        //smart refresh
        RefreshConfig.defaultConfig().enableAutoLoadMore(false).into(binding.smartRefresh);


        binding.smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                listViewDataSource.loadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                listViewDataSource.refresh();
            }
        });

        //recycler view
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(multiTypeAdapter);

        //load callback
        listViewDataSource.setInnerDataSourceObserver(new DataSourceObserver() {

            @Override
            public void onRefreshEmpty() {
                binding.smartRefresh.finishRefresh();
                binding.smartRefresh.setEnableLoadMore(false);
                pageLayout.showCustom();
            }

            @Override
            public void onLoadMoreEmpty() {
                binding.smartRefresh.finishLoadMore();
            }

            @Override
            public void onRefreshError() {
                binding.smartRefresh.finishRefresh();
                binding.smartRefresh.setEnableLoadMore(false);
                pageLayout.showError();
            }

            @Override
            public void onLoadMoreError() {
                binding.smartRefresh.finishLoadMore();
            }

            @Override
            public void onRefreshSuccess(boolean hasMore) {
                pageLayout.hide();
                binding.smartRefresh.finishRefresh();
                binding.smartRefresh.setEnableLoadMore(hasMore);
            }

            @Override
            public void onLoadMoreSuccess(boolean hasMore) {
                pageLayout.hide();
                binding.smartRefresh.finishLoadMore();
                binding.smartRefresh.setEnableLoadMore(hasMore);
            }
        });

        //data change callback
        listViewDataSource.setListUpdateCallback(new ListUpdateCallback() {
            @Override
            public void onInserted(int position, int count) {
                Log.i(TAG, "onInserted->position:" + position + ",count:" + count);
                multiTypeAdapter.notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                Log.i(TAG, "onRemoved->position:" + position + ",count:" + count);
                multiTypeAdapter.notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                Log.i(TAG, "onMoved->fromPosition:" + fromPosition + ",toPosition:" + toPosition);
                multiTypeAdapter.notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onChanged(int position, int count, Object payload) {
                Log.i(TAG, "onChanged->position:" + position + ",count:" + count);
                multiTypeAdapter.notifyItemRangeChanged(position, count, payload);
            }
        });
    }

    public MultiTypeListView refresh() {
        pageLayout.showLoading();
        listViewDataSource.refresh();
        return this;
    }

    public RecyclerView getListView() {
        return binding.recyclerView;
    }
}
