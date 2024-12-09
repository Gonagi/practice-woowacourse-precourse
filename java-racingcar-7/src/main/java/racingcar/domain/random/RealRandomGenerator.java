package racingcar.domain.random;

import camp.nextstep.edu.missionutils.Randoms;

public class RealRandomGenerator implements NumberGenerator {
    public RealRandomGenerator() {
    }

    @Override
    public int generate() {
        int number = Randoms.pickNumberInRange(1, 9);
        validateNumber(number);

        return number;
    }
}
