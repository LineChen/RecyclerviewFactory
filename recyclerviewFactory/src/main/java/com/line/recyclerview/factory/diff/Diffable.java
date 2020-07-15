package com.line.recyclerview.factory.diff;

/**
 * Created by chenchengle on 2020-01-16.
 */
public interface Diffable {

    /**
     * item的唯一id是否相同
     */
    boolean isSameId(Object o);

    /**
     * item的id相同的情况下，判断内容是否相同
     *
     * @return true表示item完全相同
     */
    boolean isSameContent(Object o);
}
