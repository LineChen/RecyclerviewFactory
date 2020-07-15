package com.line.recyclerview.factory.diff;


import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * 配合两个接口使用
 * {@link Diffable }
 * {@link UnDiffable }
 * Created by chenchengle on 2020/3/20.
 */
public class CommonDiffCallback<T> extends DiffUtil.Callback {
    private List<T> current;
    private List<T> next;

    public CommonDiffCallback(List<T> current, List<T> next) {
        this.current = current;
        this.next = next;
    }

    @Override
    public int getOldListSize() {
        return current.size();
    }

    @Override
    public int getNewListSize() {
        return next.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        try {
            T currentItem = current.get(oldItemPosition);
            T nextItem = next.get(newItemPosition);
            if (currentItem instanceof Diffable) {
                return ((Diffable) currentItem).isSameId(nextItem);
            } else if (currentItem instanceof UnDiffable && currentItem.getClass() == nextItem.getClass()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        try {
            T currentItem = current.get(oldItemPosition);
            T nextItem = next.get(newItemPosition);
            if (currentItem instanceof Diffable) {
                return ((Diffable) currentItem).isSameContent(nextItem);
            } else if (currentItem instanceof UnDiffable) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
