package vendingmachine.vault;

import java.util.Comparator;
import java.util.Map;
import vendingmachine.util.GlobalExceptions;

public class Inventory {

    private final Map<String, Item> dictionary = new java.util.HashMap<>();
    private final Map<Item, Integer> warehouse = new java.util.TreeMap<>(Comparator.comparingInt(Item::price));

    public void addStock(Stock stock) {
        Item newItem = new Item(stock.name(), stock.price());
        dictionary.putIfAbsent(newItem.name, newItem);

        // 기존 상품이랑 가격이 다르면 오류
        int previousPrice = dictionary.get(newItem.name).price();
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(previousPrice != newItem.price());

        warehouse.putIfAbsent(newItem, 0);
        warehouse.merge(newItem, stock.quantity(), Integer::sum);
    }

    public Stock findStockByName(String itemName) throws IllegalArgumentException {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(!dictionary.containsKey(itemName));
        Item item = dictionary.get(itemName);
        int quantity = warehouse.get(item);
        return new Stock(item.name, item.price, quantity);
    }

    public void popSingleStockByName(String itemName) {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(!dictionary.containsKey(itemName));
        Item item = dictionary.get(itemName);
        warehouse.merge(item, -1, Integer::sum);
        if (warehouse.get(item) == 0) {
            warehouse.remove(item);
            dictionary.remove(itemName);
        }
    }

    public boolean isStockAvailable() {
        return !warehouse.isEmpty();
    }

    public Stock findCheapestStock() throws NullPointerException {
        var cheapestEntry = warehouse.entrySet().stream().findFirst().orElseThrow();
        return cheapestEntry.getKey().ofQuantity(cheapestEntry.getValue());
    }

    private record Item(String name, int price) {
        Stock ofQuantity(int quantity) {
            return new Stock(name, price, quantity);
        }
    }
}
