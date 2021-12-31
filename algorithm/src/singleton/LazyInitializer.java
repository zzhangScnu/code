package singleton;

import java.util.Objects;

/**
 * @author lihua
 * @since 2021/12/31
 */
public class LazyInitializer {

    private static Tester tester;

    public static Tester getLazilyNotThreadSafe() {
        if (Objects.nonNull(tester)) {
            return tester;
        }
        return new Tester();
    }

    public static synchronized Tester getLazilyThreadSafe() {
        if (Objects.nonNull(tester)) {
            return tester;
        }
        return new Tester();
    }
}
