package baseball.model.out;

public record GuessResult(int nBalls, int nStrikes) {
    public GuessResult {
        // 필요하다면 내부 검증 후 로직 오류 발생시키기
        // 각각의 값 in [0, 3]
        // 두 개의 합 in [0, 3]
    }

    public boolean hasNothing() {
        return nBalls == 0 && nStrikes == 0;
    }

    public boolean hasStrike() {
        return nStrikes > 0;
    }

    public boolean hasBall() {
        return nBalls > 0;
    }
}