package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.junit.jupiter.api.Test;

import christmas.model.Badge;
import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.dto.BenefitSummary;
import christmas.model.dto.VisitDate;

class BenefitServiceTest {

    @Test
    void 예제1_결과_검증() {
        Order order = new Order(new String[]{"타파스-1", "제로콜라-1"});
        VisitDate visitDate = new VisitDate(26);

        BenefitSummary summary = BenefitService.toSummary(order, visitDate);

        assertThat(summary.finalCost()).isEqualTo(8500);
        assertThat(summary.totalBenefit()).isEqualTo(0);
        assertThat(summary.benefits()).isEmpty();
        assertThat(summary.freebies()).isEmpty();
        assertThat(summary.badge()).isEqualTo(null);
    }

    @Test
    void 예제2_결과_검증() {
        Order order = new Order(new String[]{"티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"});
        VisitDate visitDate = new VisitDate(3);

        BenefitSummary summary = BenefitService.toSummary(order, visitDate);

        assertThat(summary.finalCost()).isEqualTo(135_754);
        assertThat(summary.totalBenefit()).isEqualTo(31_246);
        assertThat(summary.freebies()).containsKey(Menu.샴페인);
        assertThat(summary.badge()).isEqualTo(Badge.산타);

        // 각 혜택별 금액 검증
        assertThat(summary.benefits())
                .extracting(b -> b.event(), b -> b.amount())
                .containsExactlyInAnyOrder(
                        tuple(Event.CHRISTMAS_DDAY_DISCOUNT, 1_200),
                        tuple(Event.WEEKDAY_DISCOUNT, 4_046),
                        tuple(Event.SPECIAL_DISCOUNT, 1_000),
                        tuple(Event.FREEBIE_EVENT, 25_000)
                );
    }

    @Test
    void 이벤트_최소_주문_금액_검증() {
        Order order = new Order(new String[]{"시저샐러드-1"});
        VisitDate visitDate = new VisitDate(25);

        BenefitSummary summary = BenefitService.toSummary(order, visitDate);

        assertThat(summary.finalCost()).isEqualTo(8_000);
        assertThat(summary.totalBenefit()).isEqualTo(0);
        assertThat(summary.benefits()).isEmpty();
        assertThat(summary.freebies()).isEmpty();
        assertThat(summary.badge()).isEqualTo(null);
    }
}