package singleton;

/**
 * @author lihua
 * @since 2021/12/31
 */
public class EagerInitializer2 {

    private static Tester tester;

    static {
        tester = new Tester();
    }

    public static Tester getEagerly() {
        return tester;
    }
}
