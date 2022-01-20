package multiplethread.tripleworker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lihua
 * @since 2022/1/20
 */
public class LockPrinter {

    private final Lock lock = new ReentrantLock();

    private int state = 0;

    public class PrinterA implements Runnable {

        @Override
        public void run() {
            int i = 10;
            // while永远要放在加锁逻辑的外层
            while (i > 0) {
                lock.lock();
                try {
                    if (state % 3 == 0) {
                        System.out.println("A");
                        i--;
                        state++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public class PrinterB implements Runnable {

        @Override
        public void run() {
            int i = 10;
            while (i > 0) {
                lock.lock();
                try {
                    if (state % 3 == 1) {
                        System.out.println("B");
                        i--;
                        state++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public class PrinterC implements Runnable {

        @Override
        public void run() {
            int i = 10;
            while (i > 0) {
                lock.lock();
                try {
                    if (state % 3 == 2) {
                        System.out.println("C");
                        i--;
                        state++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockPrinter lockPrinter = new LockPrinter();
        Runnable aPrinter = lockPrinter.new PrinterA();
        Runnable bPrinter = lockPrinter.new PrinterB();
        Runnable cPrinter = lockPrinter.new PrinterC();
        new Thread(aPrinter).start();
        // 保证执行先后顺序
        Thread.sleep(1000);
        new Thread(bPrinter).start();
        new Thread(cPrinter).start();
    }
}
