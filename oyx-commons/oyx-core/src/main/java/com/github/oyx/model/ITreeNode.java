package com.github.oyx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树接口
 * @author OYX
 * @date 2019-12-10 15:54:30
 */
public interface ITreeNode<T, I extends Serializable> {

    /**
     * 获取id
     *
     * @return
     */
    I getId();

    /**
     * 获取父id
     *
     * @return
     */
    I getParentId();

    /**
     * 获取子类
     *
     * @return
     */
    List<T> getChildren();

    /**
     * 设置子类
     *
     * @param children
     */
    void setChildren(List<T> children);

    /**
     * 初始化子类
     */
    default void initChildren() {
        if (getChildren() == null) {
            this.setChildren(new ArrayList<>());
        }
    }
}
