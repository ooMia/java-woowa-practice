package christmas.view;

import java.util.Map;

import christmas.Constant;
import christmas.model.Badge;
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

    public void printInitialCost(int cost) {
        println(ViewMessage.ORDER_INITIAL_COST_HEADER.toString());
        println(formatPriceLine(cost));
    }

    // ------------------------------
    //          할인 관련 출력
    // ------------------------------

    public void printFreebies(Map<Menu, Integer> menus) {
        println(ViewMessage.FREEBIE_MENU_HEADER.toString());
        if (menus == null || menus.isEmpty()) {
            printNone();
            return;
        }
        printMenus(menus);
    }

    public void printBenefits(Iterable<Benefit> benefits) {
        println(ViewMessage.BENEFIT_DETAIL_HEADER.toString());
        if (benefits == null || !benefits.iterator().hasNext()) {
            printNone();
            return;
        }

        var sb = new StringBuilder();
        for (Benefit benefit : benefits) {
            sb.append(formatBenefitLine(benefit)).append(System.lineSeparator());
        }
        if (!sb.isEmpty()) println(sb.toString());
    }

    public void printTotalBenefitAmount(int amount) {
        println(ViewMessage.TOTAL_BENEFIT_AMOUNT_HEADER.toString());
        println(formatPriceLine(-amount));
    }

    public void printFinalCost(int cost) {
        println(ViewMessage.FINAL_PAYMENT_AMOUNT_HEADER.toString());
        println(formatPriceLine(cost));
    }

    public void printEventBadge(Badge badge) {
        println(ViewMessage.EVENT_BADGE_HEADER.toString());
        if (badge == null) {
            printNone();
            return;
        }

        println(badge.getName());
    }

    private String formatBenefitLine(Benefit benefit) {
        String description = benefit.event().getDescription();
        int amount = benefit.amount();
        return String.format(ViewMessage.BENEFIT_FORMAT.toString(), description, formatPriceLine(-amount));
    }

    // ------------------------------
    //          공통 출력 메서드
    // ------------------------------

    private void println(String s) {
        try {
            bw.write(s);
            bw.newLine();
            bw.flush();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printNone() {
        println(Constant.STATE_UNDEFINED);
    }

    private void printMenus(Map<Menu, Integer> menus) {
        var sb = new StringBuilder();
        for (var entry : menus.entrySet()) {
            sb.append(formatMenuLine(entry)).append(System.lineSeparator());
        }
        println(sb.toString());
    }

    private String formatMenuLine(Map.Entry<Menu, Integer> entry) {
        return String.format(ViewMessage.ORDER_ITEM_FORMAT.toString(), entry.getKey().getName(), entry.getValue());
    }

    private String formatPriceLine(int price) {
        return String.format(ViewMessage.MONEY_FORMAT.toString(), price);
    }

}
