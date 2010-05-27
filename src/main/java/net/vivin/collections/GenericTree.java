package main.java.net.vivin.collections;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 6, 2010
 * Time: 11:42:40 PM
 */
public class GenericTree<T> extends AbstractTree<T, GenericNode<T>> implements Tree<T, GenericNode<T>> {

    public GenericTree(GenericNode<T> root) {
        super(root);
    }

    public int size() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = auxiliarySize(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int auxiliarySize(GenericNode<T> node) {
        int numberOfNodes = node.numberOfChildren();

        for(GenericNode<T> child : node.getChildren()) {
            numberOfNodes += auxiliarySize(child);
        }

        return numberOfNodes;
    }
    
    public boolean contains(Object data) {
        return (find(data) != null);
    }

    public GenericNode<T> find(Object data) {
        GenericNode<T> returnNode = null;

        if(this.root != null) {
            returnNode = auxiliaryFind(root, data);
        }

        return returnNode;
    }

    private GenericNode<T> auxiliaryFind(GenericNode<T> currentNode, Object data) {
        GenericNode<T> returnNode = null;

        if (currentNode.getData().equals(data)) {
            returnNode = currentNode;
        }

        else if(currentNode.hasChildren()) {
            int i = 0;
            while(returnNode == null && i < currentNode.numberOfChildren()) {
                returnNode = auxiliaryFind(currentNode.getChildAt(i), data);
                i++;
            }
        }

        return returnNode;
    }
    
    public List<GenericNode<T>> traversalSequence(TreeTraversalOrder traversalOrder) {
        List<GenericNode<T>> returnList = null;

        if(root != null) {
            returnList = traversalSequence(root, traversalOrder);
        }

        return returnList;
    }

    public List<GenericNode<T>> traversalSequence(GenericNode<T> node, TreeTraversalOrder traversalOrder) {
        List<GenericNode<T>> traversalResult = new ArrayList<GenericNode<T>>();

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

    private void preOrderTraversalSequence(GenericNode<T> node, List<GenericNode<T>> traversalResult) {
        traversalResult.add(node);

        for(GenericNode<T> child : node.getChildren()) {
            preOrderTraversalSequence(child, traversalResult);
        }
    }

    private void postOrderTraversalSequence(GenericNode<T> node, List<GenericNode<T>> traversalResult) {
        for(GenericNode<T> child : node.getChildren()) {
            postOrderTraversalSequence(child, traversalResult);
        }

        traversalResult.add(node);
    }

    public List<NodeWithDepth<GenericNode<T>>> traversalSequenceWithDepth(TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<GenericNode<T>>> returnList = null;

        if(root != null) {
            returnList = traversalSequenceWithDepth(root, traversalOrder);
        }

        return returnList;
    }

    public List<NodeWithDepth<GenericNode<T>>> traversalSequenceWithDepth(GenericNode<T> node, TreeTraversalOrder traversalOrder) {
        List<NodeWithDepth<GenericNode<T>>> traversalResult = new ArrayList<NodeWithDepth<GenericNode<T>>>();

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

    private void preOrderTraversalSequenceWithDepth(GenericNode<T> node, List<NodeWithDepth<GenericNode<T>>> traversalResult, int depth) {
        traversalResult.add(new NodeWithDepthI<GenericNode<T>>(node, depth));

        for(GenericNode<T> child : node.getChildren()) {
            preOrderTraversalSequenceWithDepth(child, traversalResult, depth + 1);
        }
    }

    private void postOrderTraversalSequenceWithDepth(GenericNode<T> node, List<NodeWithDepth<GenericNode<T>>> traversalResult, int depth) {
        for(GenericNode<T> child : node.getChildren()) {
            postOrderTraversalSequenceWithDepth(child, traversalResult, depth + 1);
        }

        traversalResult.add(new NodeWithDepthI<GenericNode<T>>(node, depth));
    }
}
