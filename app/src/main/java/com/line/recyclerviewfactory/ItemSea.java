package com.line.recyclerviewfactory;

import android.text.TextUtils;

import com.line.recyclerview.factory.diff.Diffable;

/**
 * Created by chenliu on 2020/7/14.
 */
public class ItemSea implements Diffable {
    public String res;
    public String name;
    public String desc;

    @Override
    public boolean isSameId(Object o) {
        if (o instanceof ItemSea) {
            ItemSea other = (ItemSea) o;
            return TextUtils.equals(name, other.name);
        }
        return false;
    }

    @Override
    public boolean isSameContent(Object o) {
        if (o instanceof ItemSea) {
            ItemSea other = (ItemSea) o;
            return TextUtils.equals(name, other.name)
                    && TextUtils.equals(res, other.res)
                    && TextUtils.equals(desc, other.desc);
        }
        return false;
    }
}
