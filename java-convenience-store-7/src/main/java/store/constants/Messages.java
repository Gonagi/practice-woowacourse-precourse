package store.constants;

public enum Messages {
    // message
    INPUT_PRODUCT_NAME_AND_QUANTITY("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
    NO_APPLY_PROMOTION_PRODUCT("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n"),
    GET_MORE_PRODUCTS("현재 %s는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n"),
    MEMBERSHIP_MESSAGE("멤버십 할인을 받으시겠습니까? (Y/N)"),
    ADDITIONAL_PURCHASE_MESSAGE("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),

    // error
    NON_EXIST_PRODUCT("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    NO_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_PURCHASE_FORMAT("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    INVALID_INPUT_FORMAT("잘못된 입력입니다. 다시 입력해 주세요.");

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
