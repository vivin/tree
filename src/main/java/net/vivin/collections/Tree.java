package main.java.net.vivin.collections;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 3, 2010
 * Time: 10:31:11 PM
 */
public interface Tree<T, TreeNode extends Node<T>> {

    TreeNode getRoot();

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    TreeNode find(Object o);

    List<TreeNode> traversalSequence(TreeTraversalOrder traversalOrder);

    List<TreeNode> traversalSequence(TreeNode node, TreeTraversalOrder traversalOrder);

    List<NodeWithDepth<TreeNode>> traversalSequenceWithDepth(TreeTraversalOrder traversalOrder);

    List<NodeWithDepth<TreeNode>> traversalSequenceWithDepth(TreeNode node, TreeTraversalOrder traversalOrder);

    interface NodeWithDepth<TreeNode> {

        TreeNode getNode();

        int getDepth();

        boolean equals(Object o);

        int hashCode();
    }
}
