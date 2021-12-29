package multiplethread;

/**
 * @author lihua
 * @since 2021/12/29
 */
public class SynchronizePrinter implements Printer {

    private boolean printOdd;

    public SynchronizePrinter(int start) {
        this.printOdd = isOdd(start);
    }

    @Override
    public void printOdd(int num) {
        synchronized (this) {
            while (!printOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
            printOdd = false;
            // notify and notifyAll both wake up sleeping threads, but notify only rouses one, while notifyAll rouses all of them.
            // Since notify might not wake up the right thread, notifyAll should be used instead.
            notifyAll();
        }
    }

    @Override
    public void printEven(int num) {
        synchronized (this) {
            while (printOdd) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(String.format("%s: %s", Thread.currentThread().getName(), num));
            printOdd = true;
            notifyAll();
        }
    }
}
