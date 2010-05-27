package main.java.net.vivin.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 26, 2010
 * Time: 11:12:20 AM
 */
public class BinaryTree<T> extends AbstractTree<T, BinaryNode<T>> implements Tree<T, BinaryNode<T>> {

    public BinaryTree(BinaryNode<T> root) {
        super(root);
    }

    public int size() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = auxiliarySize(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int auxiliarySize(BinaryNode<T> node) {
        int numberOfNodes = node.numberOfChildren();

        if(node.hasLeft()) {
            numberOfNodes += auxiliarySize(node.getLeft());
        }

        if(node.hasRight()) {
            numberOfNodes += auxiliarySize(node.getRight());
        }

        return numberOfNodes;
    }

    public boolean contains(Object data) {
        return (find(data) != null);
    }

    public BinaryNode<T> find(Object data) {
        BinaryNode<T> returnNode = null;

        if(this.root != null) {
            returnNode = auxiliaryFind(root, data);
        }

        return returnNode;
    }

    private BinaryNode<T> auxiliaryFind(BinaryNode<T> currentNode, Object data) {
        BinaryNode<T> returnNode = null;

        if (currentNode.getData().equals(data)) {
            returnNode = currentNode;
        }

        else if(currentNode.hasChildren()) {

            if(currentNode.hasLeft()) {
                returnNode = auxiliaryFind(currentNode.getLeft(), data);
            }

            if(returnNode == null && currentNode.hasRight()) {
                returnNode = auxiliaryFind(currentNode.getRight(), data);
            }
        }

        return returnNode;
    }

    public List<BinaryNode<T>> traversalSequence(TreeTraversalOrder traversalOrder) {
        List<BinaryNode<T>> returnList = null;

        if(root != null) {
            returnList = traversalSequence(root, traversalOrder);
        }

        return returnList;
    }

    public List<BinaryNode<T>> traversalSequence(BinaryNode<T> node, TreeTraversalOrder traversalOrder) {
        List<BinaryNode<T>> traversalResult = new ArrayList<BinaryNode<T>>();

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

    private void preOrderTraversalSequence(BinaryNode<T> node, List<BinaryNode<T>> traversalResult) {
        traversalResult.add(node);

        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }
    }

    private void postOrderTraversalSequence(BinaryNode<T> node, List<BinaryNode<T>> traversalResult) {
        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }

        traversalResult.add(node);
    }

    private void inOrderTraversalSequence(BinaryNode<T> node, List<BinaryNode<T>> traversalResult) {
        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        traversalResult.add(node);

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }
    }

    public List<NodeWithDepth<BinaryNode<T>>> traversalSequenceWithDepth(TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<BinaryNode<T>>> returnList = null;

        if(root != null) {
            returnList = traversalSequenceWithDepth(root, traversalOrder);
        }

        return returnList;
    }

    public List<NodeWithDepth<BinaryNode<T>>> traversalSequenceWithDepth(BinaryNode<T> node, TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<BinaryNode<T>>> traversalResult = new ArrayList<NodeWithDepth<BinaryNode<T>>>();

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

    private void preOrderTraversalSequenceWithDepth(BinaryNode<T> node, List<NodeWithDepth<BinaryNode<T>>> traversalResult, int depth) {
        traversalResult.add(new NodeWithDepthI<BinaryNode<T>>(node, depth));

        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }
    }

    private void postOrderTraversalSequenceWithDepth(BinaryNode<T> node, List<NodeWithDepth<BinaryNode<T>>> traversalResult, int depth) {
        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<BinaryNode<T>>(node, depth));
    }

    private void inOrderTraversalSequenceWithDepth(BinaryNode<T> node, List<NodeWithDepth<BinaryNode<T>>> traversalResult, int depth) {
        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<BinaryNode<T>>(node, depth));

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }
    }
}
