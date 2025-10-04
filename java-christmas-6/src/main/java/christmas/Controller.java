package christmas;

import christmas.model.Order;
import christmas.model.dto.VisitDate;
import christmas.service.BenefitService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    private final InputView in;
    private final OutputView out;

    public Controller(InputView inputView, OutputView outputView) {
        this.in = inputView;
        this.out = outputView;
    }

    public void greet() {
        out.printWelcome();
    }

    public VisitDate readVisitDate() {
        return tryUntilValid(in::readDate);
    }

    public Order readOrder() {
        return tryUntilValid(in::readOrder);
    }

    public void printOrder(Order order) {
        out.printOrder(order.getMenus()); // 주문 메뉴
        out.printInitialCost(order.getTotalPrice()); // 할인 전 총주문 금액
    }

    public void printDiscountSummary(Order order, VisitDate visitDate) {
        var orderInfo = BenefitService.of(order, visitDate).toSummary();
        out.printFreebies(orderInfo.freebies()); // 증정 메뉴
        out.printBenefits(orderInfo.benefits()); // 혜택 내역
        out.printTotalBenefitAmount(orderInfo.totalBenefitAmount()); // 총혜택 금액
        out.printFinalCost(orderInfo.finalCost()); // 할인 후 예상 결제 금액
        out.printEventBadge(orderInfo.badge()); // 이벤트 뱃지
    }

    private <T> T tryUntilValid(java.util.function.Supplier<T> func) {
        while (true) {
            try {
                return func.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
