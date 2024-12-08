package calculator.domain;

public class DividerFactory {
    public static final String DOUBLE_SLASH = "//";

    public static Divider of(final String input) {
        if (input.startsWith(DOUBLE_SLASH)) {
            return new CustomDivider();
        }
        return new BasicDivider();
    }
}
