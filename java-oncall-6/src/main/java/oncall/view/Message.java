package oncall.view;

enum Message {
    비상_근무_헤더("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
    평일_순번_헤더("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> "),
    휴일_순번_헤더("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}