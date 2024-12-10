package lotto.constants;

public enum Messages {
    // message

    // error
    INVALID_LOTTO_SIZE("생성한 로또의 번호 개수가 맞지 않습니다."),
    INVALID_LOTTO_NUMBER_RANGE("범위를 벗어나는 숫자를 입력했습니다."),
    DUPLICATE_LOTTO_NUMBER("생성한 랜덤 번호 내 중복이 있습니다."),
    NON_ENOUGH_MONEY("로또는 1,000원 이상 부터 구매 가능 합니다."),
    NON_THOUSAND_UNIT_MONEY("이름은 5자 이하여야 합니다.");

    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
