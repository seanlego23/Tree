package tree.java;

import tree.java.iterators.TraversalOrder;
import tree.java.iterators.TreeIterator;

import java.util.Collection;
import java.util.Spliterator;

/**
 * The root interface for all tree data structure types. The tree data structure
 * is a non-linear data structure made up of nodes. Each node contains
 * data and references to it's parent node and child nodes. The <i>root</i>
 * node's parent is null.
 *
 * <p>There are many different types of trees. {@code General Trees} can have
 * any number of children for each node. {@code Binary Trees} can only have
 * two children per node. {@code K-ary Trees} can only have <i>k</i>
 * children per node where {@code k > 2}. {@code Family Trees} can have any
 * number of children and each child has two parents. Etc.
 *
 * <p>Classes that implement this interface must have a movable reference to
 * the {@code current} node. Methods that move this reference include {@code
 * getChild}, {@code getParent}, {@code getNextSibling}, {@code
 * getPreviousSibling}, and {@code split}.
 *
 * @param <T>
 */
public interface Tree<T> extends Collection<T> {

    /**
     * Gets the root of this tree.
     *
     * @return data held by the root node.
     */
    T getRoot();

    /**
     * Gets the data for the {@code current} node.
     *
     * @return data held by the {@code current} node.
     */
    T get();

    /**
     * Gets the data for the child node at the given <i>index</i>. This moves
     * the {@code current} reference node to the child.
     *
     * @param index index at which the child node exists.
     * @return data held by the child node.
     * @throws IndexOutOfBoundsException if <i>index</i> is greater than or
     * equal to the integer returned by {@link #getChildrenCount() getChildrenCount}
     */
    T getChild(int index);

    /**
     * Gets the data for the parent node of the {@code current} node. This
     * moves the {@code current} reference node to the parent.
     *
     * @return data held by the parent node.
     */
    T getParent();

    /**
     * Gets the data for the next sibling of the {@code current} node. This
     * moves the {@code current} reference node to the next sibling.
     *
     * <p>Let <i>index</i> satisfy
     * {@code current.getParent().getChild(index) == current} is true.
     * Then the next sibling is defined as
     * {@code current.getParent().getChild(index + 1)}.
     *
     * @return data held by the next sibling node.
     * @throws java.util.NoSuchElementException if the next sibling does
     * not exist
     */
    T getNextSibling();

    /**
     * Gets the data for the previous sibling of the {@code current} node.
     * This moves the {@code current} reference node to the previous sibling.
     *
     * <p>Let <i>index</i> satisfy
     * {@code current.getParent().getChild(index) == current} is true.
     * Then the previous sibling is defined as
     * {@code current.getParent().getChild(index - 1)}
     *
     * @return data held by the previous sibling node.
     * @throws IndexOutOfBoundsException if {@code index - 1 < 0}.
     * @throws java.util.NoSuchElementException if the previous sibling does
     * not exist.
     */
    T getPreviousSibling();

    /**
     * Sets the {@code current} node's data to <i>obj</i>. If the {@code Tree}
     * does not accept null, then this method will throw a
     * {@link NullPointerException NullPointerException}.
     *
     * <p>If this {@code Tree} does not allow duplicate items and
     * {@code this.contains(obj)} is true then the node referenced by
     * {@code current} will be removed.
     *
     * @param obj object to set the {@code current} node's data to.
     * @return the previous data.
     * @throws UnsupportedOperationException if this method is unsupported.
     * @throws NullPointerException if this {@code Tree} does not accept null,
     * and {@code obj == null} is true.
     */
    T set(T obj);

    /**
     * Adds a child to the {@code current} node with <i>obj</i> data.
     * If this {@code Tree} does not accept null, then this method will throw a
     * {@link NullPointerException NullPointerException}.
     *
     * <p>If this {@code Tree} does not allow duplicate items and
     * {@code this.contains(obj)} is true then the node referenced by
     * {@code current} will be removed.
     *
     * <p>If this {@code Tree} has a limited amount of children and
     * all of the children slots are full, then this method should
     * return false instead of throwing an exception.
     *
     * @param obj data to put into the child node.
     * @return true if creation of the child node was successful, false if not.
     * @throws UnsupportedOperationException if this method is unsupported.
     * @throws NullPointerException if this {@code Tree} does not accept null,
     * and {@code obj == null} is true.
     */
    boolean addChild(T obj);

    /**
     * Adds a child to the {@code current} node with <i>obj</i> data at index.
     * If this {@code Tree} does not accept null, then this method will throw a
     * {@link NullPointerException NullPointerException}.
     *
     * <p>If this {@code Tree} does not allow duplicate items and
     * {@code this.contains(obj)} is true then the node referenced by
     * {@code current} will be removed.
     *
     * <p>If this {@code Tree} has a limited amount of children and
     * all of the children slots are full, then this method should
     * return false instead of throwing an exception.
     *
     * @param index index at which to place this child node.
     * @param obj data to put into the child node.
     * @return true if creation of the child node was successful, false if not.
     * @throws UnsupportedOperationException if this method is unsupported.
     * @throws NullPointerException if this {@code Tree} does not accept null,
     * and {@code obj == null} is true.
     * @throws IndexOutOfBoundsException if <i>index</i> is negative, or if
     * {@code this.{@link #maxChildren() maxChildren()} > 0 && index >
     * this.{@link #maxChildren() maxChildren()}}.
     */
    boolean addChild(int index, T obj);

    /**
     * Adds the collection <i>objects</i> as children of the {@code current}
     * node. The collection <i>objects</i> will not be modified.
     *
     * <p>If the collection <i>objects</i> is an instance of
     * {@code Tree<T>}, then the root node is added as a child of
     * the {@code current} node and the descendants of that root node will
     * be copied over. If <i>objects</i> is a different type of {@code Tree}
     * than this one, the implementation is dependent on what type of
     * {@code Tree} this is.
     *
     * <p>If this {@code Tree} does not allow duplicate items and
     * {@code this.contains(obj)} is true then the node referenced by
     * {@code current} will be removed.
     *
     * <p>If this {@code Tree} has a limited amount of children and
     * all of the children slots are full, then this method should
     * return false instead of throwing an exception.
     *
     * @param objects the collection to add as children.
     * @return true if the collection was added successfully, false if not.
     * @throws UnsupportedOperationException if this method is unsupported.
     * @throws NullPointerException if <i>objects</i> is null or if this
     * {@code Tree} does not accept null and an object held by <i>objects</i>
     * is null.
     */
    boolean addAllChildren(Collection<T> objects);

    /**
     * Adds the collection <i>objects</i> as children of the {@code current}
     * node at the given <i>index</i>. The collection <i>objects</i> will
     * not be modified.
     *
     * <p>If the collection <i>objects</i> is an instance of
     * {@code Tree<T>}, then the root node is added as a child of
     * the {@code current} node and the descendants of that root node will
     * be copied over. If <i>objects</i> is a different type of {@code Tree}
     * than this one, the implementation is dependent on what type of
     * {@code Tree} this is.
     *
     * <p>If this {@code Tree} does not allow duplicate items and
     * {@code this.contains(obj)} is true then the node referenced by
     * {@code current} will be removed.
     *
     * <p>If this {@code Tree} has a limited amount of children and
     * all of the children slots are full, then this method should
     * return false instead of throwing an exception.
     *
     * @param objects the collection to add as children.
     * @return true if the collection was added successfully, false if not.
     * @throws UnsupportedOperationException if this method is unsupported.
     * @throws NullPointerException if <i>objects</i> is null or if this
     * {@code Tree} does not accept null and an object held by <i>objects</i>
     * is null.
     * @throws IndexOutOfBoundsException if <i>index</i> is negative, or if
     * {@code this.{@link #maxChildren() maxChildren()} > 0 && index >
     * this.{@link #maxChildren() maxChildren()}}.
     */
    boolean addAllChildren(int index, Collection<T> objects);

    /**
     * Removes the child that has data equal to <i>obj</i>. Any descendants
     * of that child will also be removed.
     *
     * @param obj the object to remove.
     * @return true if removed, false if not.
     * @throws UnsupportedOperationException if this method is unsupported.
     */
    boolean removeChild(T obj);

    /**
     * Removes the child at the given <i>index</i>. Any descendants of that
     * child will also be removed.
     *
     * @param index index of the child node to be removed.
     * @return true if removed, false if not.
     * @throws UnsupportedOperationException if this method is unsupported.
     */
    boolean removeChild(int index);

    /**
     * Removes any children that have data equal to any of the objects in
     * <i>objects</i>. Any descendants of that child will also be removed.
     *
     * <p>This method will only check the children of the {@code current} node.
     *
     * @param objects the collection of objects to remove.
     * @return true if any elements were removed.
     * @throws UnsupportedOperationException if this method is unsupported.
     */
    boolean removeAllChildren(Collection<T> objects);

    /**
     * Removes any descendants that have data equal to any of the objects in
     * <i>objects</i>.
     *
     * <p>This will recursively check every descendant of the {@code current}
     * node, and remove if {@code objects.contains(descendant)} is true.
     *
     * @param objects
     * @return
     */
    boolean removeAllDescendants(Collection<T> objects);
    boolean isRoot();
    boolean isLeaf();
    Tree<T> subtree();
    Tree<T> split();
    int getChildrenCount();
    int depth();
    int height();
    int level();
    int maxChildren();
    TreeIterator<T> treeIterator(TraversalOrder traversalOrder);
    TreeIterator<T> treeIteratorAt(TraversalOrder traversalOrder);

    @Override
    default Spliterator<T> spliterator() {
        return null;
    }
}
