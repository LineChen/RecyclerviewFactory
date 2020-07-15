package com.line.recyclerview.factory;

/**
 * Created by chenliu on 2019-10-16.
 */
public interface IDataSource {

    void refresh(RefreshCallback callback);

    void loadMore(LoadMoreCallback callback);
}
