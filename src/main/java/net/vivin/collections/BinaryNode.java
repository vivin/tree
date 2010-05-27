package main.java.net.vivin.collections;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 26, 2010
 * Time: 10:43:30 AM
 */
public class BinaryNode<T> extends AbstractNode<T> implements Node<T> {

    private BinaryNode<T> left;
    private BinaryNode<T> right;

    public BinaryNode() {
        left = null;
        right = null;
    }

    public BinaryNode(T data) {
        super(data);
        left = null;
        right = null;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void removeChildren() {
        left = null;
        right = null;
    }

    public int numberOfChildren() {
        int numberOfChildren = 0;

        if(left != null) {
            numberOfChildren++;
        }

        if(right != null) {
            numberOfChildren++;
        }

        return numberOfChildren;
    }

    public boolean hasChildren() {
        return left != null || right != null;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void removeLeft() {
        setLeft(null);
    }

    public void removeRight() {
        setRight(null);
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

        BinaryNode<?> other = (BinaryNode<?>) obj;
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
        return data.toString() + ":[LEFT: " + left.getData().toString() +
                                 ", RIGHT: " + right.getData().toString() + "]";
    }
}
