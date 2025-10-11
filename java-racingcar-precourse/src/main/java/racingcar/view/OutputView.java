package racingcar.view;

import java.util.List;

import racingcar.Constant;
import racingcar.model.out.RacingStatus;
import racingcar.model.out.RacingWinners;
import racingcar.model.out.SampleOutput;

public class OutputView {

    public void print(SampleOutput output) {
        System.out.println(Message.OUT_QUESTION_FORMAT.format(output.foo()));
        System.out.println(output.boo());
    }

    public String racingHeader() {
        return Message.OUT_레이싱_실행_결과_헤더.text();
    }

    // pobi : --
    // woni : ----
    // jun : ---
    public String status(RacingStatus status) {
        var map = status.status(); // Map<String, Integer> status
        var sb = new StringBuilder();

        // TODO map 특성 때문에 순서 어긋난다
        for (var e : map.entrySet()) {
            var name = e.getKey();
            var pos = e.getValue();
            sb.append(formatStatus(name, pos)).append(System.lineSeparator());
        }
        return sb.toString();
    }

    String formatStatus(String name, int pos) {
        return Message.OUT_상태_출력_FORMAT.format(name, posToString(pos));
    }

    String posToString(int pos) {
        return Constant.상태_거리_표시자.repeat(pos);
    }

    // 최종 우승자 : pobi, jun
    public String winners(RacingWinners winners) {
        var names = winners.winners();
        var res = concatNames(names);
        return Message.OUT_최종_우승자_FORMAT.format(res);
    }

    String concatNames(List<String> names) {
        if (names.size() < 1) return "";

        var sb = new StringBuilder(names.get(0));
        for (var name : names.subList(1, names.size())) {
            sb.append(", ").append(name);
        }
        return sb.toString();
    }
}
