package main.java.net.vivin.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 25, 2010
 * Time: 12:46:14 PM
 */
public class GenericTreeNode<T> extends AbstractNode<T> implements Node<T> {
    private List<GenericTreeNode<T>> children;

    public GenericTreeNode() {
        this.children = new ArrayList<GenericTreeNode<T>>();
    }

    public GenericTreeNode(T data) {
        this.children = new ArrayList<GenericTreeNode<T>>();
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<GenericTreeNode<T>> getChildren() {
        return new ArrayList<GenericTreeNode<T>>(this.children); /* return a shallow copy */
    }

    public void removeChildren() {
        this.children = new ArrayList<GenericTreeNode<T>>();
    }

    public int numberOfChildren() {
        return this.children.size();
    }

    public boolean hasChildren() {
        return numberOfChildren() == 0;
    }

    public void addChild(GenericTreeNode<T> child) {
        this.children.add(child);
    }

    public void insert(T data) {
        addChild(new GenericTreeNode<T>(data));
    }

    public void addChildAt(int index, GenericTreeNode<T> child) {
        this.children.add(index, child);
    }

    public void insertAt(int index, T data) {
        addChildAt(index, new GenericTreeNode<T>(data));
    }

    public GenericTreeNode<T> getChildAt(int index) {
        return this.children.get(index);
    }

    public void removeChildAt(int index) {
        this.children.remove(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        GenericTreeNode<?> other = (GenericTreeNode<?>) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String stringRepresentation = data.toString() + ":[";

        for (GenericTreeNode<T> node : getChildren()) {
            stringRepresentation += node.getData().toString() + ", ";
        }

        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }
}
