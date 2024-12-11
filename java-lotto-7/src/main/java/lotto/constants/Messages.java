package lotto.constants;

public enum Messages {
    // message
    INPUT_MONEY_MESSAGE("구입금액을 입력해 주세요."),
    INPUT_BASIC_NUMBERS("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    PURCHASED_MESSAGE("%d개를 구매했습니다.\n"),
    PRINT_LOTTO_RESULTS_MESSAGE("당첨 통계\n" + "---"),
    PRINT_FIFTH_MESSAGE("3개 일치 (5,000원) - %d개\n"),
    PRINT_FOURTH_MESSAGE("4개 일치 (50,000원) - %d개\n"),
    PRINT_THIRD_MESSAGE("5개 일치 (1,500,000원) - %d개\n"),
    PRINT_SECOND_MESSAGE("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n"),
    PRINT_FIRST_MESSAGE("6개 일치 (2,000,000,000원) - %d개\n"),
    PRINT_RATE_OF_RANGE_MESSAGE("총 수익률은 %s%%입니다.\n"),

    // error
    INVALID_LOTTO_SIZE("생성한 로또의 번호 개수가 맞지 않습니다."),
    INVALID_LOTTO_NUMBER_RANGE("범위를 벗어나는 숫자를 입력했습니다."),
    DUPLICATE_LOTTO_NUMBER("생성한 랜덤 번호 내 중복이 있습니다."),
    NON_ENOUGH_MONEY("로또는 1,000원 이상 부터 구매 가능 합니다."),
    NON_THOUSAND_UNIT_MONEY("이름은 5자 이하여야 합니다."),
    INPUT_NULL_ERROR("null을 입력하셨습니다."),
    INVALID_NUMBER_FROM("잘못된 형식의 숫자를 입력했습니다.");

    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return "[ERROR] " + message;
    }
}
