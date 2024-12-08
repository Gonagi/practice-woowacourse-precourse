package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못된_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,\\n2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못된_경우2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_형식이_잘못된_경우3() {
        // 커스텀 구분자 정의 형식이 잘못된 경우 예외 처리
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\n1;2;"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_정상_흐름() {
        assertSimpleTest(() -> {
            run("//abc\\n1abc2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자만_있고_숫자가_없는_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
