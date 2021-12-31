package singleton;

import java.util.Objects;

/**
 * @author lihua
 * @since 2021/12/31
 */
public class DoubleCheckInitializer {

    private volatile static Tester tester;

    public static Tester getLazilyDoubleCheck() {
        if (Objects.isNull(tester)) {
            synchronized (LazyInitializer.class) {
                if (Objects.isNull(tester)) {
                    return new Tester();
                }
            }
        }
        return tester;
    }
}
