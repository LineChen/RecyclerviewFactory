package com.line.recyclerview.factory;

import android.content.Context;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RefreshConfig {
    private boolean enableRefresh;                      //开启下拉刷新
    private boolean enableHeaderTranslationContent;     //内容是否随头部刷新移动
    private boolean enableAutoLoadMore;                 //开启自动加载更多
    private boolean enableLoadMore;                     //开启加载更多
    private Class<? extends RefreshHeader> header;      //下拉刷新头部

    private RefreshConfig() {
    }

    /**
     * 开启下拉刷新
     *
     * @param enableRefresh
     * @return
     */
    public RefreshConfig enableRefresh(boolean enableRefresh) {
        this.enableRefresh = enableRefresh;
        return this;
    }

    /**
     * 内容是否随头部刷新移动
     *
     * @param enableHeaderTranslationContent
     * @return
     */
    public RefreshConfig enableHeaderTranslationContent(boolean enableHeaderTranslationContent) {
        this.enableHeaderTranslationContent = enableHeaderTranslationContent;
        return this;
    }

    /**
     * 开启自动加载更多
     *
     * @param enableAutoLoadMore
     * @return
     */
    public RefreshConfig enableAutoLoadMore(boolean enableAutoLoadMore) {
        this.enableAutoLoadMore = enableAutoLoadMore;
        return this;
    }

    /**
     * 开启加载更多
     *
     * @param enableLoadMore
     * @return
     */
    public RefreshConfig enableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
        return this;
    }

    /**
     * 下拉刷新头部
     *
     * @param header
     * @return
     */
    public RefreshConfig setRefreshHeader(Class<? extends RefreshHeader> header) {
        this.header = header;
        return this;
    }

    /**
     * 获取默认配置
     *
     * @return
     */
    public static RefreshConfig defaultConfig() {
        return new RefreshConfig().enableAutoLoadMore(true).enableLoadMore(true)
                .enableRefresh(true).enableHeaderTranslationContent(false)
                .setRefreshHeader(MaterialHeader.class);
    }

    /**
     * 设置对应的SmartRefreshLayout
     *
     * @param smartRefreshLayout
     */
    public void into(SmartRefreshLayout smartRefreshLayout) {
        try {
            Constructor<? extends RefreshHeader> constructor = header.getConstructor(Context.class);
            smartRefreshLayout.setRefreshHeader(constructor.newInstance(smartRefreshLayout.getContext()));
            smartRefreshLayout.setEnableAutoLoadMore(enableAutoLoadMore);
            smartRefreshLayout.setEnableLoadMore(enableLoadMore);
            smartRefreshLayout.setEnableHeaderTranslationContent(enableHeaderTranslationContent);
            smartRefreshLayout.setEnableRefresh(enableRefresh);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
