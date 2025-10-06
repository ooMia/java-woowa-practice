package oncall;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import oncall.util.Console;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {
    public static void main(String[] args) {
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var console = new Console(bw);
        var iv = new InputView(console);
        var ov = new OutputView(console);
        var api = new Controller(iv, ov);
        
        try {
            // 1. WorkDate 비상 근무를 배정할 월과 시작 요일 입력 #L106
            var date = api.일자_입력();

            // 2. WorkOrder 순번(평일/휴일) 입력 #L112
            var orders = api.순번_입력();

            // 3. 결과 생성
            var result = api.결과_생성(date, orders[0], orders[1]);

            // 4. 출력 #L124
            api.결과_출력(result);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
