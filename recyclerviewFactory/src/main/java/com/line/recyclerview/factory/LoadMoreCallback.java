package com.line.recyclerview.factory;

import java.util.List;

/**
 * Created by chenliu on 2020/6/11.
 */
public interface LoadMoreCallback {

    void onLoadMoreEmpty();

    void onLoadMoreSuccess(List<Object> result, boolean hasMore);

    void onLoadMoreError();
}
