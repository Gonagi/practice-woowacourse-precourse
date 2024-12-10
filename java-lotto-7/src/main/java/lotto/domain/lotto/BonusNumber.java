package lotto.domain.lotto;

public class BonusNumber {
    private final int number;

    private BonusNumber(int number) {
        this.number = number;
    }

    public static BonusNumber from(final int number) {
        return new BonusNumber(number);
    }

    public int getNumber() {
        return number;
    }
}
