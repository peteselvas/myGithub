package org.academiadecodigo.bootcamp.concurrency.bqueue;

import java.util.ArrayList;

/**
 * Blocking Queue
 * @param <T> the type of elements stored by this queue
 */
public class BQueue<Integer> {

    ArrayList<Integer> list;
    private int limit;

    /**
     * Constructs a new queue with a maximum size
     * @param limit the queue size
     */
    public BQueue(int limit) {
        this.limit = limit;
        list = new ArrayList<>(limit);
    }

    /**
     * Inserts the specified element into the queue
     * Blocking operation if the queue is full
     * @param data the data to add to the queue
     */
    public synchronized void offer(Integer data) {

        while (getSize() == getLimit()-1){
            try {
                System.out.println("Queue is full. Waiting for space available...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Queue has space available now...");
        list.add(data);
        System.out.println("added new item to queue: " + data);
        notifyAll();
    }

    /**
     * Retrieves and removes data from the head of the queue
     * Blocking operation if the queue is empty
     * @return the data from the head of the queue
     */
    public synchronized Integer poll() {

        while (getSize() == 0){
            try {
                System.out.println("queue is empty");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Queue has item that can be removed...");
        Integer result = list.get(0);
        list.remove(0);
        System.out.println("Removing item " + result);
        notifyAll();
        return result;
    }

    /**
     * Gets the number of elements in the queue
     * @return the number of elements
     */
    public synchronized int getSize() {

        int counter = 0;

        for (Integer item : list){
            if (item != null){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Gets the maximum number of elements that can be present in the queue
     * @return the maximum number of elements
     */
    public synchronized int getLimit() {

        return limit;

    }

}
