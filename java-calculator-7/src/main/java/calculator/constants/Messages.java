package calculator.constants;

public enum Messages {
    INPUT_MESSAGE("덧셈할 문자열을 입력해 주세요."),
    RESULT_MESSAGE("결과 : "),

    // error
    INPUT_NULL_ERROR("null을 입력하셨습니다."),
    INVALID_AT_BACK_INPUT("잘못된 계산식 문자열을 입력했습니다."),
    NUMBER_IN_FRONT_INPUT("구분자 문자열 내 숫자를 입력했습니다."),
    INVALID_AT_FRONT_INPUT("잘못된 구분자 문자열을 입력했습니다."),
    OUT_OF_NUMERIC_FORM("숫자 형식이 아닙니다."),
    NON_POSITIVE_NUMBER("양수가 아닙니다.");

    private final String message;

    Messages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
