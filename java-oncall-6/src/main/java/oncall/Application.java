package oncall;

public class Application {
    public static void main(String[] args) {
        // controller
        try {
            // 1. WorkDate 비상 근무를 배정할 월과 시작 요일 입력 #L106
            var date = Controller.일자_입력();

            // 2. WorkOrder 순번(평일/휴일) 입력 #L112
            var orders = Controller.순번_입력();

            // 3. 결과 생성
            var result = Controller.결과_생성(date, orders[0], orders[1]);

            // 4. 출력 #L124
            Controller.결과_출력(result);
        } catch (final Exception e) {
            System.err.println();
            e.printStackTrace();
        }
    }
}
