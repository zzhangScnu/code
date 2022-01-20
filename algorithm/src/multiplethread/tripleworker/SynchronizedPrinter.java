package multiplethread.tripleworker;

/**
 * @author lihua
 * @since 2022/1/20
 */
public class SynchronizedPrinter implements Runnable {

    private final String content;

    /**
     * 做锁的对象最好是final修饰的
     */
    private final Object previous;

    private final Object current;

    public SynchronizedPrinter(String content, Object previous, Object current) {
        this.content = content;
        this.previous = previous;
        this.current = current;
    }

    @Override
    public void run() {
        int i = 10;
        while (i >= 0) {
            synchronized (previous) {
                synchronized (current) {
                    if (i == 0) {
                        // 唤醒等待线程并退出
                        current.notifyAll();
                        break;
                    }
                    System.out.println(content);
                    i--;
                    // 唤醒等待当前锁的线程们。此时只是唤醒线程，并不释放锁。要到同步代码块结束后，才会释放锁。
                    current.notifyAll();
                }
                try {
                    // 当前线程阻塞，等待下次轮替时被唤醒
                    previous.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("exception: " + e);
                }
            }
        }
    }
}
