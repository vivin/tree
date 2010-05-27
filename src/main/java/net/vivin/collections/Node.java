package main.java.net.vivin.collections;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 25, 2010
 * Time: 12:36:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Node<T> {

    T getData();

    void removeChildren();

    int numberOfChildren();

    boolean hasChildren();

    boolean equals(Object o);

    int hashCode();

    String toString();
}
