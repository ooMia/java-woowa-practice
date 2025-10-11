package racingcar;

public class Application {
    public static void main(String[] args) {
        // 이렇게 하면 List<Cars>가 외부로 노출되지 않도록
        // 임시로 id를 부여하고 내부 StorageService를 사용하자
        var gameId = Constant.GAME_ID;

        var api = new Controller();

        var carNames = api.inputCarNames();
        api.generateCars(gameId, carNames); // id를 활용해서 초기화

        // id를 기반으로 돌리고 그 결과를 바로 반환
        var nTrials = api.inputRaceTrial();
        for (int i = 0; i < nTrials; ++i) {
            var result = api.runRaceOnce(gameId);
            api.printResult(result);
        }

        // id 기반으로 승자 정보 추적
        var winners = api.getWinners(gameId);
        api.printWinners(winners);
    }
}
