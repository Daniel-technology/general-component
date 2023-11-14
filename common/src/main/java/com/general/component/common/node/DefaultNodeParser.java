package com.general.component.common.node;

import com.general.component.common.node.tree.NodeParser;
import com.general.component.common.node.tree.Tree;
import com.general.component.common.node.tree.TreeNode;

import java.util.Map;

/**
 * 默认的简单转换器
 *
 * @author littlesnail
 * @create 2023−03-07 9:20 PM
 */
public class DefaultNodeParser<T> implements NodeParser<TreeNode<T>, T> {

    @Override
    public void parse(TreeNode<T> treeNode, Tree<T> tree) {
        tree.setId(treeNode.getId());
        tree.setParentId(treeNode.getParentId());
        tree.setWeight(treeNode.getWeight());
        tree.setName(treeNode.getName());

        //扩展字段
        final Map<String, Object> extra = treeNode.getExtra();

        if (extra.size() != 0) {
            extra.forEach(tree::putExtra);
        }
    }
}
