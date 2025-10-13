package vendingmachine.model;

// 콜라 1500
// 사이다 1000
public class TradableItem extends Item implements Tradable {
    private final int price;

    public TradableItem(String name, int price) {
        super(name);
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
