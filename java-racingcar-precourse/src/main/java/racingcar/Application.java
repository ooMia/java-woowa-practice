package racingcar;

public class Application {
    public static void main(String[] args) {
        try {
            // 이렇게 하면 List<Cars>가 외부로 노출되지 않도록
            // 임시로 id를 부여하고 내부 StorageService를 사용하자
            var gameId = Constant.GAME_ID;

            var api = new Controller();

            var carNames = api.inputCarNames();
            api.generateCars(gameId, carNames); // id를 활용해서 초기화

            // id를 기반으로 돌리고 그 결과를 바로 반환
            var nTrials = api.inputRaceTrial();

            // 현재의 직접 int를 받아 반복하는 부분은
            // dto의 필드에 의존적인 형태가 되어서 별로인듯 싶어서
            // 이렇게 바꾸고 바로 출력할까 싶었는데 일단 그냥 진행함
            // var winners = api.runRaces(gameId, nTrials);

            api.printRacingHeader();
            for (int i = 0; i < nTrials; ++i) {
                var result = api.runRaceOnce(gameId);
                api.printStatus(result);
            }

            // id 기반으로 승자 정보 추적
            var winners = api.getWinners(gameId);
            api.printWinners(winners);
        } catch (final Exception ignore) {
            // fallback
        }
    }
}
