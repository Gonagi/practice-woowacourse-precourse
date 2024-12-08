package calculator.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DividerFactoryTest {
    @Test
    void 문자열이_더블_슬래시로_시작하면_커스텀_구분자_객체를_리턴한다() {
        String input = "//abc";
        Divider divider = DividerFactory.of(input);

        Assertions.assertThat(divider)
                .isInstanceOf(CustomDivider.class);
    }

    @Test
    void 문자열이_더블_슬래시로_시작하지_않으면_기본_구분자_객체를_리턴한다() {
        String input = "abc";
        Divider divider = DividerFactory.of(input);

        Assertions.assertThat(divider)
                .isInstanceOf(BasicDivider.class);

    }

}
