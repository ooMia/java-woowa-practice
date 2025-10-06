package christmas.view;

enum ViewMessage {
    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASK_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ASK_ORDER_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),

    SHOW_BENEFIT_PREVIEW_FORMAT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_ITEM_FORMAT("%s %d개"),
    MONEY_FORMAT("%,d원"),
    BENEFIT_FORMAT("%s: %s"),

    ORDER_MENU_HEADER("<주문 메뉴>"),
    ORDER_INITIAL_COST_HEADER("<할인 전 총주문 금액>"),
    FREEBIE_MENU_HEADER("<증정 메뉴>"),
    BENEFIT_DETAIL_HEADER("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT_HEADER("<총혜택 금액>"),
    FINAL_PAYMENT_AMOUNT_HEADER("<할인 후 예상 결제 금액>"),
    EVENT_BADGE_HEADER("<12월 이벤트 배지>");

    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
