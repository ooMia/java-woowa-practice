package christmas.view;

import java.util.Map;

import christmas.model.Menu;
import christmas.model.dto.Benefit;

public class OutputView {

    private final java.io.BufferedWriter bw;

    public OutputView(java.io.BufferedWriter bw) {
        this.bw = bw;
    }

    public void printWelcome() {
        println(ViewMessage.WELCOME.toString());
    }

    public void printOrder(Map<Menu, Integer> menus) {
        println(ViewMessage.ORDER_MENU_HEADER.toString());
        printMenus(menus);
    }

    public void printFreebies(Map<Menu, Integer> menus) {
        println(ViewMessage.FREEBIE_MENU_HEADER.toString());
        printMenus(menus);
    }

    public void printInitialCost(int cost) {
        println(ViewMessage.ORDER_INITIAL_COST_HEADER.toString());
        println(formatPriceLine(cost));
    }

    public void printBenefits(Iterable<Benefit> benefits) {
        println(ViewMessage.BENEFIT_DETAIL_HEADER.toString());
        var sb = new StringBuilder();
        for (Benefit benefit : benefits) {
            var name = benefit.event().toString();
            var amount = benefit.amount();
            sb.append(formatBenefitLine(name, amount)).append(System.lineSeparator());
        }
        println(sb.toString());
    }

    public void printTotalBenefitAmount(int amount) {
        println(ViewMessage.TOTAL_BENEFIT_AMOUNT_HEADER.toString());
        println(formatPriceLine(amount));
    }

    public void printFinalCost(int cost) {
        println(ViewMessage.FINAL_PAYMENT_AMOUNT_HEADER.toString());
        println(formatPriceLine(cost));
    }

    private void println(String s) {
        try {
            bw.write(s);
            bw.newLine();
            bw.flush();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMenus(Map<Menu, Integer> menus) {
        var sb = new StringBuilder();
        for (Menu menu : menus.keySet()) {
            var name = menu.getName();
            var count = menus.get(menu);
            sb.append(formatMenuLine(name, count)).append(System.lineSeparator());
        }
        println(sb.toString());
    }

    private String formatMenuLine(String name, int count) {
        return String.format(ViewMessage.ORDER_ITEM_FORMAT.toString(), name, count);
    }

    private String formatPriceLine(int price) {
        return String.format(ViewMessage.MONEY_FORMAT.toString(), price);
    }

    private String formatBenefitLine(String name, int amount) {
        return String.format(ViewMessage.BENEFIT_FORMAT.toString(), name, formatPriceLine(amount));
    }
}
