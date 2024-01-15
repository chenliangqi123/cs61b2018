package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /**
     * return size of buffer
     */
    int capacity();

    /**
     * return number of items currently in the buffer
     */
    int fillCount();

    /**
     * add item to the end
     */
    void enqueue(T x);

    /**
     * delete and return item from the front
     */
    T dequeue();

    /**
     * return (but do not delete) item from the front
     */
    T peek();

    /**
     * return an iterator
     */
    Iterator<T> iterator();

    /**
     * is the buffer empty?
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     * is the buffer full
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }


}
