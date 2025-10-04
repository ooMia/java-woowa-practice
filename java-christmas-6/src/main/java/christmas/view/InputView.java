package christmas.view;

import java.io.BufferedWriter;
import java.io.IOException;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Order;
import christmas.model.dto.VisitDate;

public class InputView {
    private final BufferedWriter bw;

    public InputView(BufferedWriter bw) {
        this.bw = bw;
    }

    public VisitDate readDate() {
        println(ViewMessage.ASK_VISIT_DATE);
        String input = Console.readLine();
        return new VisitDate(Integer.parseInt(input));
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}