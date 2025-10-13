package vendingmachine.model;

public class Item {
    protected final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
