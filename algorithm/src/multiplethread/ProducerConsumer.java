package multiplethread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lihua
 * @since 2022/1/21
 */
public class ProducerConsumer {

    private final int[] elements;

    private int count;

    private int putIndex;

    private int takeIndex;

    private final Lock lock;

    private final Condition notFull;

    private final Condition notEmpty;

    public ProducerConsumer(int capacity) {
        elements = new int[capacity];
        count = 0;
        putIndex = 0;
        takeIndex = 0;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void put(int element) {
        // 如果写成synchronized(lock)可是会报IllegalMonitorStateException的噢
        lock.lock();
        try {
            while (count == elements.length) {
                try {
                    System.out.println("尝试放入，但队列满了，等待……");
                    notFull.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("exception: " + e);
                }
            }
            enqueue(element);
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(int element) {
        elements[putIndex++] = element;
        if (putIndex >= elements.length) {
            putIndex = 0;
        }
        count++;
        System.out.println("放入：" + element);
        notEmpty.signal();
    }

    public int take() {
        lock.lock();
        try {
            while (count == 0) {
                try {
                    System.out.println("尝试获取，但队列空了，等待……");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("exception: " + e);
                }
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    private int dequeue() {
        int element = elements[takeIndex++];
        if (takeIndex >= elements.length) {
            takeIndex = 0;
        }
        count--;
        System.out.println("获取： " + element);
        notFull.signal();
        return element;
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer clazz = new ProducerConsumer(1);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                clazz.take();
            }
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                clazz.put(1);
            }
        }).start();
    }
}
