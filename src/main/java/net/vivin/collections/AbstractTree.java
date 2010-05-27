package main.java.net.vivin.collections;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 5, 2010
 * Time: 10:06:24 PM
 */
public abstract class AbstractTree<T, TreeNode extends Node<T>> implements Tree<T, TreeNode> {

    protected TreeNode root;

    protected AbstractTree() {
    }

    protected AbstractTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    static class NodeWithDepthI<TreeNode> implements NodeWithDepth<TreeNode> {

        protected TreeNode node;
        protected int depth;

        protected NodeWithDepthI(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }

        public TreeNode getNode() {
            return this.node;
        }

        public int getDepth() {
            return this.depth;
        }
    }
}
