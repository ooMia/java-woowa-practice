package christmas.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Menu {

    // APPETIZER
    양송이수프(MenuCategory.APPETIZER, 6_000),
    타파스(MenuCategory.APPETIZER, 5_500),
    시저샐러드(MenuCategory.APPETIZER, 8_000),

    // MAIN
    티본스테이크(MenuCategory.MAIN, 55_000),
    바비큐립(MenuCategory.MAIN, 54_000),
    해산물파스타(MenuCategory.MAIN, 35_000),
    크리스마스파스타(MenuCategory.MAIN, 25_000),

    // DESSERT
    초코케이크(MenuCategory.DESSERT, 15_000),
    아이스크림(MenuCategory.DESSERT, 5_000),

    // BEVERAGE
    제로콜라(MenuCategory.BEVERAGE, 3_000),
    레드와인(MenuCategory.BEVERAGE, 60_000),
    샴페인(MenuCategory.BEVERAGE, 25_000);

    private final MenuCategory category;
    private final String name;
    private final int price;

    Menu(MenuCategory category, int price) {
        this.category = category;
        this.name = this.name();
        this.price = price;
    }

    private static final Map<String, Menu> enumMap = Arrays.stream(Menu.values())
            .collect(Collectors.toMap(Menu::name, Function.identity()));

    public static Menu fromName(String name) {
        // enumMap을 활용한 String name -> Menu 변환
        return enumMap.get(name);
    }

    public MenuCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
