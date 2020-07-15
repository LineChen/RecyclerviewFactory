package com.line.recyclerview.factory;

/**
 * Created by chenliu on 2019-10-15.
 */
public interface DataSourceObserver {

    void onRefreshEmpty();

    void onLoadMoreEmpty();

    void onRefreshError();

    void onLoadMoreError();

    void onRefreshSuccess(boolean hasMore);

    void onLoadMoreSuccess(boolean hasMore);
}
