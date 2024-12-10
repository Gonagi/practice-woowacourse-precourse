package racingcar.constants;

public enum Messages {
    // message
    INPUT_CAR_MESSAGE("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
    INPUT_ATTEMPT_COUNT_MESSAGE("시도할 횟수는 몇 회인가요?"),
    RESULT_MESSAGE("실행 결과"),
    WINNER_MESSAGE("최종 우승자 : "),

    // error
    INVALID_NUMBER_COUNT("fakeNumber 개수가 맞지 않습니다."),
    INVALID_NUMBER_RANGE("범위를 벗어나는 숫자를 입력했습니다."),
    NON_POSITIVE_ATTEMPT_COUNT("시도 횟수로는 0을 입력해야 합니다."),
    DUPLICATE_CAR_NAMES("중복된 이름이 있습니다."),
    INVALID_CAR_NAME_LENGTH("이름은 5자 이하여야 합니다."),
    INVALID_CAR_NAME_FORM("입력한 자동차 이름들의 형식이 맞지 않습니다."),
    NO_CARS_PARTICIPATED("게임에 참가한 자동차가 없습니다."),
    INPUT_NULL_ERROR("null을 입력하셨습니다."),
    INVALID_NUMBER_FROM("잘못된 형식의 숫자를 입력했습니다.");

    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
