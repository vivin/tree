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
public class GenericNode<T> extends AbstractNode<T> implements Node<T> {
    private List<GenericNode<T>> children;

    public GenericNode() {
        this.children = new ArrayList<GenericNode<T>>();
    }

    public GenericNode(T data) {
        this.children = new ArrayList<GenericNode<T>>();
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<GenericNode<T>> getChildren() {
        return new ArrayList<GenericNode<T>>(this.children); /* return a shallow copy */
    }

    public void removeChildren() {
        this.children = new ArrayList<GenericNode<T>>();
    }

    public int numberOfChildren() {
        return this.children.size();
    }

    public boolean hasChildren() {
        return numberOfChildren() == 0;
    }

    public void addChild(GenericNode<T> child) {
        this.children.add(child);
    }

    public void addChildAt(int index, GenericNode<T> child) {
        this.children.add(index, child);
    }

    public GenericNode<T> getChildAt(int index) {
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

        GenericNode<?> other = (GenericNode<?>) obj;
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

        for (GenericNode<T> node : getChildren()) {
            stringRepresentation += node.getData().toString() + ", ";
        }

        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }
}
