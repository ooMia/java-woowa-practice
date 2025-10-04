package christmas.model;

import java.util.EnumMap;
import java.util.Map;

import christmas.Constant;

public class Order {
    private final Map<Menu, Integer> menus;
    private final int totalPrice;

    public Order(String[] menuStrings) throws IllegalArgumentException {
        this.menus = new EnumMap<>(Menu.class);
        initMap(menuStrings);
        this.totalPrice = menus.entrySet().stream()
                .reduce(0, (prev, e) -> prev + e.getKey().getPrice() * e.getValue(), Integer::sum);
    }

    private void initMap(String[] menuStrings) {
        for (String menuString : menuStrings) {
            String[] parts = menuString.split("\\s*-\\s*");
            if (parts.length != 2)
                throw ErrorCode.MENU_FORMAT_INVALID.exception();
            Menu menu = parseMenuName(parts[0]);
            int count = parseMenuCount(parts[1]);
            if (count > Constant.MAX_QUANTITY_PER_MENU)
                throw ErrorCode.ORDER_COUNT_EXCEEDS_LIMIT.exception();
            putMenu(menu, count);
        }
        validateOrderContents();
    }

    private void validateOrderContents() {
        boolean hasNonBeverage = false;
        for (var e : menus.entrySet()) {
            if (MenuCategory.BEVERAGE != e.getKey().getCategory()) {
                hasNonBeverage = true;
                break;
            }
        }
        if (!hasNonBeverage)
            throw ErrorCode.ORDER_ONLY_BEVERAGE_NOT_ALLOWED.exception();
    }

    private Menu parseMenuName(String s) {
        try {
            Menu menu = Menu.fromName(s);
            if (menu == null) throw ErrorCode.MENU_NOT_FOUND.exception();
            return menu;
        } catch (NullPointerException | ClassCastException e) {
            throw ErrorCode.MENU_NOT_FOUND.exception();
        }
    }

    private int parseMenuCount(String s) {
        try {
            int count = Integer.parseInt(s);
            if (count < Constant.MIN_QUANTITY_PER_MENU)
                throw ErrorCode.MENU_COUNT_TOO_LOW.exception();
            return count;
        } catch (NumberFormatException e) {
            throw ErrorCode.MENU_COUNT_NOT_NUMBER.exception();
        }
    }

    private void putMenu(Menu menu, int count) {
        if (menus.containsKey(menu))
            throw ErrorCode.ORDER_MENU_DUPLICATE.exception();
        menus.put(menu, count);
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
