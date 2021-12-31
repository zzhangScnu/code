package singleton;

/**
 * @author lihua
 * @since 2021/12/31
 */
public class StaticInnerClassInitializer {

    static class SingletonHolder {

        private static Tester tester;
    }

    public static Tester getLazily() {
        return SingletonHolder.tester;
    }
}
