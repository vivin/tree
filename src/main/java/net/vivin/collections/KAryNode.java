package main.java.net.vivin.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 26, 2010
 * Time: 4:19:00 PM
 */
public class KAryNode<T> extends AbstractNode<T> implements Node<T> {

    private List<KAryNode<T>> children;
    private int k;

    /*
    Constructors are package-private and cannot be accessed from outside
     */

    KAryNode(int k) {
        this.k = k;
        children = new ArrayList<KAryNode<T>>(this.k);
    }

    KAryNode(int k, T data) {
        this.k = k;
        this.data = data;
        children = new ArrayList<KAryNode<T>>(this.k);
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getK() {
        return k;
    }

    public int getMaxOutDegree() {
        return k;
    }

    public int numberOfChildren() {
        return children.size();
    }

    public boolean hasChildren() {
        return children.size() > 0;
    }

    public List<KAryNode<T>> getChildren() {
        return new ArrayList<KAryNode<T>>(this.children); /* return a shallow copy */
    }

    public void removeChildren() {
        children = new ArrayList<KAryNode<T>>(k);
    }

    public void addChild(KAryNode<T> child) {
        if(isFull()) {
            throw new IndexOutOfBoundsException("This node already has " + k + " children and is full");
        }

        else {
            this.children.add(child);
        }
    }

    public void addChildAt(int index, KAryNode<T> child) {
        if(index >= k) {
            throw new IndexOutOfBoundsException("This node can only have " + k + " children, which implies that only positions 0 through " + (k - 1) + " are available");
        }

        else {
            this.children.add(index, child);
        }
    }

    public KAryNode<T> getChildAt(int index) {
        return this.children.get(index);
    }

    public void removeChildAt(int index) {
        this.children.remove(index);
    }

    public boolean isFull() {
        return this.children.size() == k;
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

        KAryNode<?> other = (KAryNode<?>) obj;
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

        for (KAryNode<T> node : getChildren()) {
            stringRepresentation += node.getData().toString() + ", ";
        }

        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }
}
