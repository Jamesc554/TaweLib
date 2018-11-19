package utils;


/**
 * <h1>Queue</h1>
 * <p>Queue is a self implemented version of the Queue functionality Java provides, this is to allow us more control
 * over what can be stored in the queue, and how big it can be.
 * T - the type of object stored in the queue.</p>
 * @author James Carter
 * @version 0.1
 * @since 18/11/2018
 */

import java.util.NoSuchElementException;

public class Queue<T> {

    private QueueElement<T> head; // The object at the start of the queue
    private QueueElement<T> tail; // The object at the end of the queue

    /**
     * Constructs an empty Queue.
     */
    public Queue() {

    }

    /**
     * Returns true if the queue is empty
     */
    public boolean isEmpty() {
        return (head == null || tail == null);
    }

    /**
     * Returns the element at the head of the queue
     */
    public Object peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        return head.getElement();
    }

    /**
     * Removes the front element of the queue
     */
    public void dequeue() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        head = head.getNext();
    }

    /**
     * Puts an element on the back of the queue.
     * @param element
     * The element being put onto the back of the queue
     */
    public void enqueue(T element) {
        QueueElement<T> tmp;
        if (isEmpty()){
            tmp = new QueueElement<>(element, null);
            head = tmp;
            tail = tmp;
        } else {
            tmp = tail;
            tail = new QueueElement<>(element, null);
            tmp.setNext(tail);
        }
    }

    /**
     * Method to print the full contents of the queue in order from head to
     * tail.
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("The queue is empty.");
            return;
        }

        QueueElement<T> tmp = head;
        System.out.println(tmp.getElement() + ",");

        while (tmp.getNext() != null){
            tmp = tmp.getNext();
            System.out.println(tmp.getElement() + ",");
        }
    }
}
