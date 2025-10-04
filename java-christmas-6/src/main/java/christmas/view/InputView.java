package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.ErrorCode;
import christmas.model.Order;
import christmas.model.dto.VisitDate;

public class InputView {
    private final java.io.BufferedWriter bw;

    public InputView(java.io.BufferedWriter bw) {
        this.bw = bw;
    }

    public VisitDate readDate() {
        try {
            println(ViewMessage.ASK_VISIT_DATE);
            String input = Console.readLine();
            int day = Integer.parseInt(input);
            return new VisitDate(day);
        } catch (NumberFormatException e) {
            throw ErrorCode.DATE_NOT_NUMBER.exception();
        }
    }

    public Order readOrder() {
        println(ViewMessage.ASK_ORDER_MENU);
        String input = Console.readLine();
        String[] parts = input.split("\\s*,\\s*");
        return new Order(parts);
    }

    private void println(ViewMessage message) {
        try {
            bw.write(message.toString());
            bw.newLine();
            bw.flush();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}