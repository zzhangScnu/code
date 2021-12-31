package singleton;

import java.util.Objects;

/**
 * @author lihua
 * @since 2021/12/31
 */
public class Tester {

    private String prompt = "I' m a tester!";

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tester tester = (Tester) o;
        return Objects.equals(prompt, tester.prompt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prompt);
    }
}
