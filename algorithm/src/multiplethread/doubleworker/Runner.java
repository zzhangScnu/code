package multiplethread.doubleworker;

/**
 * @author lihua
 * @since 2021/12/29
 */
public class Runner implements Runnable {

    private int start;

    private int end;

    private boolean oddRunner;

    private Printer printer;

    public Runner(int start, int end, boolean oddRunner, Printer printer) {
        this.start = start;
        this.end = end;
        this.oddRunner = oddRunner;
        this.printer = printer;
    }

    private boolean isOdd(int num) {
        return num % 2 != 0;
    }

    @Override
    public void run() {
        int offset = isOdd(start) && oddRunner ? 0 : 1;
        int start = this.start + offset;
        for (int i = start; i <= end; i += 2) {
            if (isOdd(i)) {
                printer.printOdd(i);
            } else {
                printer.printEven(i);
            }
        }
    }
}
