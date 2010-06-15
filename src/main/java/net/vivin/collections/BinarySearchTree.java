package main.java.net.vivin.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: Jun 7, 2010
 * Time: 5:18:09 PM
 */
public class BinarySearchTree<T extends Comparable> extends AbstractTree<T, BinarySearchTreeNode<T>> implements Tree<T, BinarySearchTreeNode<T>> {
    public BinarySearchTree(BinarySearchTreeNode<T> root) {
        super(root);
    }

    public void insert(T data) {
        _insert(root, data);
    }

    private BinarySearchTreeNode<T> _insert(BinarySearchTreeNode<T> currentNode, T data) {
        if(currentNode == null) {
            currentNode = new BinarySearchTreeNode<T>(data);
        }

        else if(data.compareTo(currentNode.getData()) < 0) {
            currentNode.setLeft(_insert(currentNode.getLeft(), data));
        }

        else {
            currentNode.setRight(_insert(currentNode.getRight(), data));
        }

        return currentNode;
    }

    private void replace(BinarySearchTreeNode<T> nodeToReplace, BinarySearchTreeNode<T> replacementNode) {
        if(nodeToReplace == nodeToReplace.getParent().getLeft()) {
            nodeToReplace.getParent().setLeft(replacementNode);
        }

        else {
            nodeToReplace.getParent().setRight(replacementNode);
        }

        if(replacementNode != null) {
            replacementNode.setParent(nodeToReplace.getParent());
        }
    }

    public boolean delete(T data) {
        boolean deleted = false;
        BinarySearchTreeNode<T> nodeToDelete = find(data);

        if(nodeToDelete != null) {

            if(nodeToDelete.hasRight() && nodeToDelete.hasLeft()) {
                BinarySearchTreeNode<T> successor = findMinimum(nodeToDelete.getRight());
                nodeToDelete.setData(successor.getData());
                replace(successor, successor.getRight());
            }

            else if(nodeToDelete.hasRight() || nodeToDelete.hasLeft()) {
                if(nodeToDelete.hasRight()) {
                    replace(nodeToDelete, nodeToDelete.getRight());
                }

                else {
                    replace(nodeToDelete, nodeToDelete.getLeft());
                }
            }

            else {
                if(nodeToDelete == nodeToDelete.getParent().getLeft()) {
                    replace(nodeToDelete, null);
                }

                else {
                    replace(nodeToDelete, null);
                }
            }
        }

        return deleted;
    }

    public int size() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = _size(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int _size(BinarySearchTreeNode<T> node) {
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

    public BinarySearchTreeNode<T> find(Object data) {
        BinarySearchTreeNode<T> returnNode = null;

        if(this.root != null) {
            returnNode = _find(root, data);
        }

        return returnNode;
    }

    private BinarySearchTreeNode<T> _find(BinarySearchTreeNode<T> currentNode, Object data) {
        BinarySearchTreeNode<T> returnNode = null;

        if (currentNode.getData().compareTo(data) == 0) {
            returnNode = currentNode;
        }

        else if(currentNode.hasChildren()) {

            if(currentNode.hasLeft() && ((T) data).compareTo(currentNode.getLeft().getData()) < 0) {
                returnNode = _find(currentNode.getLeft(), data);
            }

            else if(currentNode.hasRight() && ((T) data).compareTo(currentNode.getRight().getData()) > 0) {
                returnNode = _find(currentNode.getRight(), data);
            }
        }

        return returnNode;
    }

    public BinarySearchTreeNode<T> findMinimum() {
        BinarySearchTreeNode<T> minimum = null;

        if(root != null) {
            minimum = findMinimum(root);
        }

        return minimum;
    }

    public BinarySearchTreeNode<T> findMinimum(BinarySearchTreeNode<T> node) {
        while(node.hasRight()) {
            node = node.getRight();
        }

        return node;
    }

    public BinarySearchTreeNode<T> findMaximum() {
        BinarySearchTreeNode<T> maximum = null;

        if(root != null) {
            maximum = findMaximum(root);
        }

        return maximum;
    }

    public BinarySearchTreeNode<T> findMaximum(BinarySearchTreeNode<T> node) {
        while(node.hasLeft()) {
            node = node.getLeft();
        }

        return node;
    }

    public List<BinarySearchTreeNode<T>> traversalSequence(TreeTraversalOrder traversalOrder) {
        List<BinarySearchTreeNode<T>> returnList = null;

        if(root != null) {
            returnList = traversalSequence(root, traversalOrder);
        }

        return returnList;
    }

    public List<BinarySearchTreeNode<T>> traversalSequence(BinarySearchTreeNode<T> node, TreeTraversalOrder traversalOrder) {
        List<BinarySearchTreeNode<T>> traversalResult = new ArrayList<BinarySearchTreeNode<T>>();

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

    private void preOrderTraversalSequence(BinarySearchTreeNode<T> node, List<BinarySearchTreeNode<T>> traversalResult) {
        traversalResult.add(node);

        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }
    }

    private void postOrderTraversalSequence(BinarySearchTreeNode<T> node, List<BinarySearchTreeNode<T>> traversalResult) {
        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }

        traversalResult.add(node);
    }

    private void inOrderTraversalSequence(BinarySearchTreeNode<T> node, List<BinarySearchTreeNode<T>> traversalResult) {
        if(node.hasLeft()) {
            preOrderTraversalSequence(node.getLeft(), traversalResult);
        }

        traversalResult.add(node);

        if(node.hasRight()) {
            preOrderTraversalSequence(node.getRight(), traversalResult);
        }
    }

    public List<NodeWithDepth<BinarySearchTreeNode<T>>> traversalSequenceWithDepth(TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<BinarySearchTreeNode<T>>> returnList = null;

        if(root != null) {
            returnList = traversalSequenceWithDepth(root, traversalOrder);
        }

        return returnList;
    }

    public List<NodeWithDepth<BinarySearchTreeNode<T>>> traversalSequenceWithDepth(BinarySearchTreeNode<T> node, TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<BinarySearchTreeNode<T>>> traversalResult = new ArrayList<NodeWithDepth<BinarySearchTreeNode<T>>>();

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

    private void preOrderTraversalSequenceWithDepth(BinarySearchTreeNode<T> node, List<NodeWithDepth<BinarySearchTreeNode<T>>> traversalResult, int depth) {
        traversalResult.add(new NodeWithDepthI<BinarySearchTreeNode<T>>(node, depth));

        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }
    }

    private void postOrderTraversalSequenceWithDepth(BinarySearchTreeNode<T> node, List<NodeWithDepth<BinarySearchTreeNode<T>>> traversalResult, int depth) {
        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<BinarySearchTreeNode<T>>(node, depth));
    }

    private void inOrderTraversalSequenceWithDepth(BinarySearchTreeNode<T> node, List<NodeWithDepth<BinarySearchTreeNode<T>>> traversalResult, int depth) {
        if(node.hasLeft()) {
            preOrderTraversalSequenceWithDepth(node.getLeft(), traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<BinarySearchTreeNode<T>>(node, depth));

        if(node.hasRight()) {
            preOrderTraversalSequenceWithDepth(node.getRight(), traversalResult, depth + 1);
        }
    }
}
