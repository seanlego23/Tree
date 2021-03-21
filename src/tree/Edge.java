package io.github.seanlego23.tree;

final class Edge<T> {

    private final Node<T> parent;
    private final Node<T> child;

    public Edge(Node<T> parent, Node<T> child) {
        this.parent = parent;
        this.child = child;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    public Node<T> getChild() {
        return this.child;
    }
}
