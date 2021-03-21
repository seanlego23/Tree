package io.github.seanlego23.tree.iterators;

import io.github.seanlego23.tree.Tree;

import java.util.Iterator;
import java.util.function.Consumer;

public interface TreeIterator<T> extends Iterator<T> {

    TraversalOrder getTraversalOrder();
    boolean hasNextSibling();
    boolean hasPreviousSibling();
    boolean hasParent();
    boolean hasChildren();
    T nextSibling();
    T previousSibling();
    T parent();
    T nextChild();
    T set(T obj);
    boolean isRoot();
    boolean isLeaf();
    int getChildrenCount();
    int depth();
    int height();
    int level();
    Tree<T> subtree();
    Tree<T> split();


    @Override
    default void forEachRemaining(Consumer<? super T> action) {
        if (action == null)
            throw new NullPointerException();
        while (hasNext())
            action.accept(next());
    }
}
