package main.java.net.vivin.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 26, 2010
 * Time: 11:12:20 AM
 */
public class BinaryTree<T> extends AbstractTree<T, BinaryTreeNode<T>> implements Tree<T, BinaryTreeNode<T>> {

    public BinaryTree(BinaryTreeNode<T> root) {
        super(root);
    }

    public int size() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = _size(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int _size(BinaryTreeNode<T> node) {
        int numberOfNodes = node.numberOfChildren();

        if(node.hasLeft()) {
            numberOfNodes += _size(node.getLeft());
        }

        if(node.hasRight()) {
            numberOfNodes += _size(node.getRight());
        }

        return numberOfNodes;
    }

    public boolean contains(Object data) {
        return (find(data) != null);
    }

    public BinaryTreeNode<T> find(Object data) {
        BinaryTreeNode<T> returnNode = null;

        if(this.root != null) {
            returnNode = _find(root, data);
        }

        return returnNode;
    }

    private BinaryTreeNode<T> _find(BinaryTreeNode<T> currentNode, Object data) {
        BinaryTreeNode<T> returnNode = null;

        if (currentNode.getData().equals(data)) {
            returnNode = currentNode;
        }

        else if(currentNode.hasChildren()) {

            if(currentNode.hasLeft()) {
                returnNode = _find(currentNode.getLeft(), data);
            }

            if(returnNode == null && currentNode.hasRight()) {
                returnNode = _find(currentNode.getRight(), data);
            }
        }

        return returnNode;
    }

    public List<BinaryTreeNode<T>> traversalSequence(TreeTraversalOrder traversalOrder) {
        List<BinaryTreeNode<T>> returnList = null;

        if(root != null) {
            returnList = traversalSequence(root, traversalOrder);
        }

        return returnList;
    }

    public List<BinaryTreeNode<T>> traversalSequence(BinaryTreeNode<T> node, TreeTraversalOrder traversalOrder) {
        List<BinaryTreeNode<T>> traversalResult = new ArrayList<BinaryTreeNode<T>>();

        if(traversalOrder == TreeTraversalOrder.PRE_ORDER) {
            preOrderTraversalSequence(node, traversalResult);
        }

        else if(traversalOrder == TreeTraversalOrder.POST_ORDER) {
            postOrderTraversalSequence(node, traversalResult);
        }

        else if(traversalOrder == TreeTraversalOrder.IN_ORDER) {
            inOrderTraversalSequence(node, traversalResult);
        }

        return traversalResult;
    }

    private void preOrderTraversalSequence(BinaryTreeNode<T> node, List<BinaryTreeNode<T>> traversalResult) {
        traversalResult.add(node);

        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }
    }

    private void postOrderTraversalSequence(BinaryTreeNode<T> node, List<BinaryTreeNode<T>> traversalResult) {
        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }

        traversalResult.add(node);
    }

    private void inOrderTraversalSequence(BinaryTreeNode<T> node, List<BinaryTreeNode<T>> traversalResult) {
        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        traversalResult.add(node);

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }
    }

    public List<NodeWithDepth<BinaryTreeNode<T>>> traversalSequenceWithDepth(TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<BinaryTreeNode<T>>> returnList = null;

        if(root != null) {
            returnList = traversalSequenceWithDepth(root, traversalOrder);
        }

        return returnList;
    }

    public List<NodeWithDepth<BinaryTreeNode<T>>> traversalSequenceWithDepth(BinaryTreeNode<T> node, TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<BinaryTreeNode<T>>> traversalResult = new ArrayList<NodeWithDepth<BinaryTreeNode<T>>>();

        if(traversalOrder == TreeTraversalOrder.PRE_ORDER) {
            preOrderTraversalSequenceWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == TreeTraversalOrder.POST_ORDER) {
            postOrderTraversalSequenceWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == TreeTraversalOrder.IN_ORDER) {
            inOrderTraversalSequenceWithDepth(node, traversalResult, 0);
        }

        return traversalResult;
    }

    private void preOrderTraversalSequenceWithDepth(BinaryTreeNode<T> node, List<NodeWithDepth<BinaryTreeNode<T>>> traversalResult, int depth) {
        traversalResult.add(new NodeWithDepthI<BinaryTreeNode<T>>(node, depth));

        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }
    }

    private void postOrderTraversalSequenceWithDepth(BinaryTreeNode<T> node, List<NodeWithDepth<BinaryTreeNode<T>>> traversalResult, int depth) {
        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<BinaryTreeNode<T>>(node, depth));
    }

    private void inOrderTraversalSequenceWithDepth(BinaryTreeNode<T> node, List<NodeWithDepth<BinaryTreeNode<T>>> traversalResult, int depth) {
        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<BinaryTreeNode<T>>(node, depth));

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }
    }
}
