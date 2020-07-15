package com.line.recyclerview.factory;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;

import com.line.recyclerview.factory.diff.CommonDiffCallback;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;

/**
 * Created by chenliu on 2019-10-15.
 */
public abstract class ListViewDataSource implements IDataSource {

    private DataSourceObserver innerDataSourceObserver;
    private ListUpdateCallback listUpdateCallback;
    private List currentList;

    public ListViewDataSource() {
        currentList = new Items();
    }

    void setInnerDataSourceObserver(@NonNull DataSourceObserver innerDataSourceObserver) {
        this.innerDataSourceObserver = innerDataSourceObserver;
    }

    void setListUpdateCallback(ListUpdateCallback listUpdateCallback) {
        this.listUpdateCallback = listUpdateCallback;
    }

    List getCurrentList() {
        return currentList;
    }

    private RefreshCallback refreshCallback = new RefreshCallback() {
        @Override
        public void onRefreshEmpty() {
            innerDataSourceObserver.onRefreshEmpty();
        }

        @Override
        public void onRefreshSuccess(List<Object> result, boolean hasMore) {
            List snapshots = new Items(currentList);
            currentList.clear();
            if (result != null) {
                currentList.addAll(result);
            }
            diff(currentList, snapshots);
            innerDataSourceObserver.onRefreshSuccess(hasMore);
        }

        @Override
        public void onRefreshError() {
            innerDataSourceObserver.onRefreshError();
        }
    };

    private LoadMoreCallback loadMoreCallback = new LoadMoreCallback() {

        @Override
        public void onLoadMoreEmpty() {
            innerDataSourceObserver.onLoadMoreEmpty();
        }

        @Override
        public void onLoadMoreSuccess(List<Object> result, boolean hasMore) {
            List snapshots = new Items(currentList);
            currentList.addAll(result);
            diff(currentList, snapshots);
            innerDataSourceObserver.onLoadMoreSuccess(hasMore);
        }

        @Override
        public void onLoadMoreError() {
            innerDataSourceObserver.onLoadMoreError();
        }
    };

    void refresh() {
        refresh(refreshCallback);
    }

    void loadMore() {
        loadMore(loadMoreCallback);
    }

    private void diff(List newList, List oldList) {
        CommonDiffCallback diffCallback = new CommonDiffCallback(oldList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(new ListUpdateCallback() {
            @Override
            public void onInserted(int position, int count) {
                listUpdateCallback.onInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                listUpdateCallback.onRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                listUpdateCallback.onMoved(fromPosition, toPosition);
            }

            @Override
            public void onChanged(int position, int count, @Nullable Object payload) {
                listUpdateCallback.onChanged(position, count, payload);
            }
        });
    }


    public void submitList(List dataList) {
        List snapshots = new Items(currentList);
        currentList.clear();
        currentList.addAll(dataList);
        diff(currentList, snapshots);
    }

    public List getCurrentSnapshots() {
        return new ArrayList(currentList);
    }

}
