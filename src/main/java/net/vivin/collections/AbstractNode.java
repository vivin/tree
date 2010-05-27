package main.java.net.vivin.collections;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 25, 2010
 * Time: 12:41:56 PM
 */
public abstract class AbstractNode<T> implements Node<T> {

    protected T data;

    protected AbstractNode() {
    }

    protected AbstractNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }
}
