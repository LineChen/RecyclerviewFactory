package com.line.recyclerviewfactory;

import android.os.Handler;

import com.line.recyclerview.factory.ListViewDataSource;
import com.line.recyclerview.factory.LoadMoreCallback;
import com.line.recyclerview.factory.RefreshCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenliu on 2020/7/14.
 */
public class SeaDataSource extends ListViewDataSource {

    private static String[] resList = {
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2335285852,4163333478&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1877576411,1449482084&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2884965936,2003029691&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3350438028,3808547423&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1291270041,1575688769&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1004797756,1727636616&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1264427981,3005381196&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3115232241,982222576&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=272724722,4101403107&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2347210306,1301780588&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3743163287,2447693799&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3312593151,490419934&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2825401958,1823070045&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=106471481,1525131833&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1327314908,2564724490&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3589315283,1774782721&fm=26&gp=0.jpg"
    };

    @Override
    public void refresh(final RefreshCallback callback) {
        final List dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemSea item = new ItemSea();
            item.name = "sea " + i;
            item.res = resList[i];
            item.desc = "what a beautiful sea!";
            dataList.add(item);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                callback.onRefreshSuccess(dataList, true);
                callback.onRefreshError();
            }
        }, 500);
    }

    @Override
    public void loadMore(final LoadMoreCallback callback) {
        final List dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemSea item = new ItemSea();
            item.name = "sea " + i;
            item.res = resList[(int) (Math.random() * resList.length)];
            item.desc = "what a beautiful sea!";
            dataList.add(item);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onLoadMoreSuccess(dataList, true);
            }
        }, 500);
    }

    public void insert(int position) {
        ItemSea item = new ItemSea();
        item.name = "sea insert at " + position;
        item.res = resList[(int) (Math.random() * resList.length)];
        item.desc = "what a beautiful sea!";
        List currentSnapshots = getCurrentSnapshots();
        if (position >= 0 && position < currentSnapshots.size()) {
            currentSnapshots.add(position, item);
        } else {
            currentSnapshots.add(0, item);
        }
        submitList(currentSnapshots);
    }

    public void remove(int position) {
        List currentSnapshots = getCurrentSnapshots();
        if (position >= 0 && position < currentSnapshots.size()) {
            currentSnapshots.remove(position);
            submitList(currentSnapshots);
        }
    }
}
