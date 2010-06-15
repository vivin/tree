package main.java.net.vivin.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 26, 2010
 * Time: 4:06:15 PM
 */
public class KAryTree<T> extends AbstractTree<T, KAryTreeNode<T>> implements Tree<T, KAryTreeNode<T>> {

    private int k;

    public KAryTree(int k, KAryTreeNode<T> root) {
        this.k = k;

        if(this.k != root.getK()) {
            throw new IllegalArgumentException("k-ary tree with k=" + this.k + " can only consist of nodes with the same k value. You are trying to set the root of the tree to a node with k=" + root.getK());
        }

        else {
            this.root = root;
        }
    }

    public int getK() {
        return k;
    }

    public int getMaxOutDegree() {
        return k;
    }

    public int size() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = _size(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int _size(KAryTreeNode<T> node) {
        int numberOfNodes = node.numberOfChildren();

        for(KAryTreeNode<T> child : node.getChildren()) {
            numberOfNodes += _size(child);
        }

        return numberOfNodes;
    }

    public boolean contains(Object data) {
        return (find(data) != null);
    }

    public KAryTreeNode<T> find(Object data) {
        KAryTreeNode<T> returnNode = null;

        if(this.root != null) {
            returnNode = _find(root, data);
        }

        return returnNode;
    }

    private KAryTreeNode<T> _find(KAryTreeNode<T> currentNode, Object data) {
        KAryTreeNode<T> returnNode = null;

        if (currentNode.getData().equals(data)) {
            returnNode = currentNode;
        }

        else if(currentNode.hasChildren()) {
            int i = 0;
            while(returnNode == null && i < currentNode.numberOfChildren()) {
                returnNode = _find(currentNode.getChildAt(i), data);
                i++;
            }
        }

        return returnNode;
    }
   
    public List<KAryTreeNode<T>> traversalSequence(TreeTraversalOrder traversalOrder) {
        List<KAryTreeNode<T>> returnList = null;

        if(root != null) {
            returnList = traversalSequence(root, traversalOrder);
        }

        return returnList;
    }

    public List<KAryTreeNode<T>> traversalSequence(KAryTreeNode<T> node, TreeTraversalOrder traversalOrder) {
        List<KAryTreeNode<T>> traversalResult = new ArrayList<KAryTreeNode<T>>();

        if(traversalOrder == TreeTraversalOrder.PRE_ORDER) {
            preOrderTraversalSequence(node, traversalResult);
        }

        else if(traversalOrder == TreeTraversalOrder.POST_ORDER) {
            postOrderTraversalSequence(node, traversalResult);
        }

        else if(traversalOrder == TreeTraversalOrder.IN_ORDER) {
            throw new UnsupportedOperationException("An in-order traversal is not defined for a k-ary tree");
        }

        return traversalResult;
    }

    private void preOrderTraversalSequence(KAryTreeNode<T> node, List<KAryTreeNode<T>> traversalResult) {
        traversalResult.add(node);

        for(KAryTreeNode<T> child : node.getChildren()) {
            preOrderTraversalSequence(child, traversalResult);
        }
    }

    private void postOrderTraversalSequence(KAryTreeNode<T> node, List<KAryTreeNode<T>> traversalResult) {
        for(KAryTreeNode<T> child : node.getChildren()) {
            postOrderTraversalSequence(child, traversalResult);
        }

        traversalResult.add(node);
    }

    public List<NodeWithDepth<KAryTreeNode<T>>> traversalSequenceWithDepth(TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<KAryTreeNode<T>>> returnList = null;

        if(root != null) {
            returnList = traversalSequenceWithDepth(root, traversalOrder);
        }

        return returnList;
    }

    public List<NodeWithDepth<KAryTreeNode<T>>> traversalSequenceWithDepth(KAryTreeNode<T> node, TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<KAryTreeNode<T>>> traversalResult = new ArrayList<NodeWithDepth<KAryTreeNode<T>>>();

        if(traversalOrder == TreeTraversalOrder.PRE_ORDER) {
            preOrderTraversalSequenceWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == TreeTraversalOrder.POST_ORDER) {
            postOrderTraversalSequenceWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == TreeTraversalOrder.IN_ORDER) {
            throw new UnsupportedOperationException("An in-order traversal is not defined for a k-ary tree");
        }

        return traversalResult;
    }

    private void preOrderTraversalSequenceWithDepth(KAryTreeNode<T> node, List<NodeWithDepth<KAryTreeNode<T>>> traversalResult, int depth) {
        traversalResult.add(new NodeWithDepthI<KAryTreeNode<T>>(node, depth));

        for(KAryTreeNode<T> child : node.getChildren()) {
            preOrderTraversalSequenceWithDepth(child, traversalResult, depth + 1);
        }
    }

    private void postOrderTraversalSequenceWithDepth(KAryTreeNode<T> node, List<NodeWithDepth<KAryTreeNode<T>>> traversalResult, int depth) {
        for(KAryTreeNode<T> child : node.getChildren()) {
            postOrderTraversalSequenceWithDepth(child, traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<KAryTreeNode<T>>(node, depth));
    }
}
