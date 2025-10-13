package vendingmachine.model.in;

import java.util.Map;

public record Boo(Map<String, Integer> map) {
    public Boo {
        // TODO: validations
    }
}
