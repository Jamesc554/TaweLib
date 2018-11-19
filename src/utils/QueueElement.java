package utils;


/**
 * <h1>QueueElement</h1>
 * <p>QueueElement is an object and link to the next QueueElement in a queue.
 * T - the type of object stored in the queue.</p>
 * @author James Carter
 * @version 0.1
 * @since 18/11/2018
 */

public class QueueElement<T> {
    private T element; // The element contained in this linked list
    private QueueElement<T> next; // The next element of the linked list

    public QueueElement (T e, QueueElement<T> n) {
        this.element = e;
        this.next = n;
    }

    /**
     * Sets the value of this QueueElement
     * @param element
     * The value of this QueueElement
     */
    public void setElement (T element) {
        this.element = element;
    }

    /**
     * Sets the next QueueElement in the queue
     * @param e
     * The next QueueElement in the queue
     */
    public void setNext (QueueElement<T> e) {
        this.next = e;
    }

    /**
     * Returns the value of this QueueElement
     */
    public T getElement () {
        return element;
    }

    /**
     * Returns the next QueueElement in the queue
     */
    public QueueElement<T> getNext () {
        return next;
    }

}

