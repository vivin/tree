package main.java.net.vivin.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 6, 2010
 * Time: 11:42:40 PM
 */
public class GenericTree<T> extends AbstractTree<T, GenericTreeNode<T>> implements Tree<T, GenericTreeNode<T>> {

    public GenericTree(GenericTreeNode<T> root) {
        super(root);
    }

    public int size() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = _size(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int _size(GenericTreeNode<T> node) {
        int numberOfNodes = node.numberOfChildren();

        for(GenericTreeNode<T> child : node.getChildren()) {
            numberOfNodes += _size(child);
        }

        return numberOfNodes;
    }
    
    public boolean contains(Object data) {
        return (find(data) != null);
    }

    public GenericTreeNode<T> find(Object data) {
        GenericTreeNode<T> returnNode = null;

        if(this.root != null) {
            returnNode = _find(root, data);
        }

        return returnNode;
    }

    private GenericTreeNode<T> _find(GenericTreeNode<T> currentNode, Object data) {
        GenericTreeNode<T> returnNode = null;

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
    
    public List<GenericTreeNode<T>> traversalSequence(TreeTraversalOrder traversalOrder) {
        List<GenericTreeNode<T>> returnList = null;

        if(root != null) {
            returnList = traversalSequence(root, traversalOrder);
        }

        return returnList;
    }

    public List<GenericTreeNode<T>> traversalSequence(GenericTreeNode<T> node, TreeTraversalOrder traversalOrder) {
        List<GenericTreeNode<T>> traversalResult = new ArrayList<GenericTreeNode<T>>();

        if(traversalOrder == TreeTraversalOrder.PRE_ORDER) {
            preOrderTraversalSequence(node, traversalResult);
        }

        else if(traversalOrder == TreeTraversalOrder.POST_ORDER) {
            postOrderTraversalSequence(node, traversalResult);
        }

        else if(traversalOrder == TreeTraversalOrder.IN_ORDER) {
            throw new UnsupportedOperationException("An in-order traversal is not defined for a generic (n-ary) tree");
        }

        return traversalResult;
    }

    private void preOrderTraversalSequence(GenericTreeNode<T> node, List<GenericTreeNode<T>> traversalResult) {
        traversalResult.add(node);

        for(GenericTreeNode<T> child : node.getChildren()) {
            preOrderTraversalSequence(child, traversalResult);
        }
    }

    private void postOrderTraversalSequence(GenericTreeNode<T> node, List<GenericTreeNode<T>> traversalResult) {
        for(GenericTreeNode<T> child : node.getChildren()) {
            postOrderTraversalSequence(child, traversalResult);
        }

        traversalResult.add(node);
    }

    public List<NodeWithDepth<GenericTreeNode<T>>> traversalSequenceWithDepth(TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<GenericTreeNode<T>>> returnList = null;

        if(root != null) {
            returnList = traversalSequenceWithDepth(root, traversalOrder);
        }

        return returnList;
    }

    public List<NodeWithDepth<GenericTreeNode<T>>> traversalSequenceWithDepth(GenericTreeNode<T> node, TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<GenericTreeNode<T>>> traversalResult = new ArrayList<NodeWithDepth<GenericTreeNode<T>>>();

        if(traversalOrder == TreeTraversalOrder.PRE_ORDER) {
            preOrderTraversalSequenceWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == TreeTraversalOrder.POST_ORDER) {
            postOrderTraversalSequenceWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == TreeTraversalOrder.IN_ORDER) {
            throw new UnsupportedOperationException("An in-order traversal is not defined for a generic (n-ary) tree");
        }

        return traversalResult;
    }

    private void preOrderTraversalSequenceWithDepth(GenericTreeNode<T> node, List<NodeWithDepth<GenericTreeNode<T>>> traversalResult, int depth) {
        traversalResult.add(new NodeWithDepthI<GenericTreeNode<T>>(node, depth));

        for(GenericTreeNode<T> child : node.getChildren()) {
            preOrderTraversalSequenceWithDepth(child, traversalResult, depth + 1);
        }
    }

    private void postOrderTraversalSequenceWithDepth(GenericTreeNode<T> node, List<NodeWithDepth<GenericTreeNode<T>>> traversalResult, int depth) {
        for(GenericTreeNode<T> child : node.getChildren()) {
            postOrderTraversalSequenceWithDepth(child, traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<GenericTreeNode<T>>(node, depth));
    }
}
