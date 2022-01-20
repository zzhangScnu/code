package multiplethread.doubleworker;

/**
 * @author lihua
 * @since 2021/12/29
 */
public class Demonstrator {

    public static void main(String[] args) {
        doPrint(new SynchronizedPrinter(1));
        doPrint(new ReentrantLockPrinter(1));
        doPrint(new SemaphorePrinter());
        doPrint(new CountdownLatchPrinter());
        doPrint(new CycleBarrierPrinter(1));
    }

    private static void doPrint(Printer printer) {
        new Thread(new Runner(1, 100, true, printer)).start();
        new Thread(new Runner(1, 100, false, printer)).start();
    }
}
