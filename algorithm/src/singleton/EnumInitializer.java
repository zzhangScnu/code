package singleton;

/**
 * @author lihua
 * @since 2021/12/31
 */
public class EnumInitializer {

    public enum Tester {

        INSTANCE;
    }

    public static Tester get() {
        return Tester.INSTANCE;
    }
}
