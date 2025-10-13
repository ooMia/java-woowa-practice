package vendingmachine.model.out;

// TODO CoinBalance 스펙은 List<Coins> 같은 걸로 추상화하면 좋음
// Coins는 Coin, Integer 가지고 있고
public record CoinBalance(int n10, int n50, int n100, int n500) {
}
