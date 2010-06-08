package main.java.net.vivin.collections;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: Jun 7, 2010
 * Time: 5:04:36 PM
 */
public class BinarySearchTreeNode<T extends Comparable> extends AbstractNode<T> implements Node<T> {

    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode() {
        left = null;
        right = null;
    }

    public BinarySearchTreeNode(T data) {
        super(data);
        left = null;
        right = null;
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

    public void setLeft(BinarySearchTreeNode<T> left) {
        if(left.getData().compareTo(this.data) < 0) {
            this.left = left;
        }

        else {
            throw new IllegalArgumentException("Value of left child in a Binary-Search Tree must be lesser than value of parent");
        }
    }

    public void setRight(BinarySearchTreeNode<T> right) {
        if(right.getData().compareTo(this.data) >= 0) {
            this.right = right;
        }

        else {
            throw new IllegalArgumentException("Value of right child in Binary-Search Tree must be greater than or equal to value of parent");
        }
    }

    public BinarySearchTreeNode<T> getLeft() {
        return left;
    }

    public BinarySearchTreeNode<T> getRight() {
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

        BinarySearchTreeNode<?> other = (BinarySearchTreeNode<?>) obj;
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
