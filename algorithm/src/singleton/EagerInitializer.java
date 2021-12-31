package singleton;

/**
 * @author lihua
 * @since 2021/12/31
 */
public class EagerInitializer {

    private static Tester tester = new Tester();

    public static Tester getEagerly() {
        return tester;
    }
}
