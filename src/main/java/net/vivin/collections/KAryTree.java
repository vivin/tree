package main.java.net.vivin.collections;

/**
 * Created by IntelliJ IDEA.
 * User: vivin
 * Date: May 26, 2010
 * Time: 4:06:15 PM
 */
public class KAryTree<T> extends AbstractTree<T, GenericNode<T>> implements Tree<T, GenericNode<T>> {

    public KAryNode<T> createNode() {
        return new KAryNode<T>();
    }
}
