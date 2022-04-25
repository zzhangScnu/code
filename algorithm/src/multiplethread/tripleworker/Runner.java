package multiplethread.tripleworker;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lihua
 * @since 2022/1/20
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        runByCondition();
    }

    private static void runBySynchronized() throws InterruptedException {
        Object aLock = new Object();
        Object bLock = new Object();
        Object cLock = new Object();
        Runnable aPrinter = new SynchronizedPrinter("A", cLock, aLock);
        Runnable bPrinter = new SynchronizedPrinter("B", aLock, bLock);
        Runnable cPrinter = new SynchronizedPrinter("C", bLock, cLock);
        new Thread(aPrinter).start();
        // 保证执行先后顺序 todo: 为什么必须有
        Thread.sleep(1000);
        new Thread(bPrinter).start();
        new Thread(cPrinter).start();
    }

    private static void runByCondition() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition aCondition = lock.newCondition();
        Condition bCondition = lock.newCondition();
        Condition cCondition = lock.newCondition();
        Thread aPrinter = new ConditionPrinter("A", lock, aCondition, bCondition);
        Thread bPrinter = new ConditionPrinter("B", lock, bCondition, cCondition);
        Thread cPrinter = new ConditionPrinter("C", lock, cCondition, aCondition);
        aPrinter.start();
        // 保证执行先后顺序 todo: 为什么必须有
        Thread.sleep(1000);
        bPrinter.start();
        cPrinter.start();
    }

    private static void runBySemaphore() {
        Semaphore aSemaphore = new Semaphore(1);
        Semaphore bSemaphore = new Semaphore(0);
        Semaphore cSemaphore = new Semaphore(0);
        Thread aPrinter = new SemaphorePrinter("A", aSemaphore, bSemaphore);
        Thread bPrinter = new SemaphorePrinter("B", bSemaphore, cSemaphore);
        Thread cPrinter = new SemaphorePrinter("C", cSemaphore, aSemaphore);
        aPrinter.start();
        bPrinter.start();
        cPrinter.start();
    }
}
