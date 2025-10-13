package vendingmachine.model;

// #L128 프로그래밍 요구사항에서 별도로 변경 불가 안내가 없는 경우 파일 수정과 패키지 이동을 자유롭게 할 수 있다.
public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin of(int v) {
        var name = "COIN_" + v;
        return Coin.valueOf(name);
    }

    // TODO ? getter를 만들지 않고 처리할 수 없을까?
    public int getAmount() {
        return amount;
    }

}
