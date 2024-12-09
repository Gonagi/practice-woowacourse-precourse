package racingcar.domain;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {
    Car car;
    @BeforeEach
    void setUp() {
        car = Car.of("pobi");
    }

    @Test
    void 랜덤값이_4_이상이면_움직인다() {
        car.move(4);
        assertThat(car.getLocation())
                .isEqualTo("-");
    }
    @Test
    void 랜덤값이_4_미만이면_움직인다() {
        car.move(3);
        assertThat(car.getLocation())
                .isEqualTo("");
    }

}
