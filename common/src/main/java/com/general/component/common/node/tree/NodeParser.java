package com.general.component.common.node.tree;

/**
 * 树节点解析器
 *
 * @author littlesnail
 * @create 2023−03-07 9:18 PM
 */
@FunctionalInterface
public interface NodeParser<T, E> {
    /**
     * @param object   源数据实体
     * @param treeNode 树节点实体
     */
    void parse(T object, Tree<E> treeNode);
}
