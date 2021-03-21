package tree.java.iterators;

import java.util.Spliterator;

public interface TreeSpliterator<T> extends Spliterator<T> {
    TreeSpliterator<T> trySplitChildren();
}
