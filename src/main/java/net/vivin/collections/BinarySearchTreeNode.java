package main.java.net.vivin.collections;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: Jun 7, 2010
 * Time: 5:04:36 PM
 */
public class BinarySearchTreeNode<T extends Comparable> extends AbstractNode<T> implements Node<T> {

    private BinarySearchTreeNode<T> parent;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode() {
        left = null;
        right = null;
        parent = null;
    }

    public BinarySearchTreeNode(T data) {
        super(data);
        left = null;
        right = null;
        parent = null;
    }

    /* Making this package-private. Developers should not be able to change the data of a node */
    void setData(T data) {
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

    public BinarySearchTreeNode<T> getLeft() {
        return left;
    }

    /* Making this package-private - developers should only be inserting nodes via the tree's insert() method */
    void setLeft(BinarySearchTreeNode<T> left) {
        if(left.data.compareTo(this.data) < 0) {
            this.left = left;
            left.parent = this;
        }

         else {
            throw new IllegalArgumentException("The data of the left child of a BinarySearchTreeNode must be lesser than the data of the node itself");
        }
    }

    /* Making this package-private - developers should only be inserting nodes via the tree's insert() method */
    void insertLeft(T data) {
        setLeft(new BinarySearchTreeNode<T>(data));
    }

    /* Making this package-private - developers should only be inserting nodes via the tree's insert() method */
    void setRight(BinarySearchTreeNode<T> right) {
        if(right.data.compareTo(this.data) >= 0) {
            this.right = right;
            right.parent = this;
        }

        else {
            throw new IllegalArgumentException("The data of the right child of a BinarySearchTreeNode must be greater than or equal to the data of the node itself");
        }
    }

    /* Making this package-private - developers should only be inserting nodes via the tree's insert() method */
    void insertRight(T data) {
        setRight(new BinarySearchTreeNode<T>(data));
    }

    public BinarySearchTreeNode<T> getRight() {
        return right;
    }

    public BinarySearchTreeNode<T> getParent() {
        return parent;
    }

    /* Making this package-private - developers should not be able to change the parent of a node. Only the Tree object can */
    void setParent(BinarySearchTreeNode<T> parent) {
        this.parent = parent;
    }

    public void removeLeft() {
        this.left = null;
    }

    public void removeRight() {
        this.right = null;
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
