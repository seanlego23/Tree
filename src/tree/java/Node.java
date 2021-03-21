package tree.java;

import java.util.Collection;
import java.util.LinkedList;

final class Node<T> {

    private final T value;
    private final Edge<T> parentEdge;
    private LinkedList<Edge<T>> childrenEdges;

    public Node(T value, Node<T> parent) {
        this.value = value;
        if (parent == null)
            this.parentEdge = null;
        else
            this.parentEdge = new Edge<>(parent, this);
        this.childrenEdges = null;
    }

    public T getValue() {
        return this.value;
    }

    public Node<T> getParent() {
        if (this.parentEdge == null)
            return null;
        return this.parentEdge.getParent();
    }

    public Node<T> getChild(int index) {
        if (this.childrenEdges == null)
            throw new IndexOutOfBoundsException();
        if (index >= this.childrenEdges.size())
            throw new IndexOutOfBoundsException();

        return this.childrenEdges.get(index).getChild();
    }

    public void addChild(Node<T> child) {
        if (this.childrenEdges == null)
            this.childrenEdges = new LinkedList<>();
        this.childrenEdges.addLast(new Edge<>(this, child));
    }

    public void addAllChildren(Collection<Node<T>> children) {
        if (this.childrenEdges == null)
            this.childrenEdges = new LinkedList<>();
        for (Node<T> node : children)
            this.addChild(node);
    }

    public void removeChild(int index) {
        if (this.childrenEdges == null)
            throw new IndexOutOfBoundsException();
        if (index >= this.childrenEdges.size())
            throw new IndexOutOfBoundsException();

        this.childrenEdges.remove(index);
    }

}
