package vendingmachine.vault;

import java.util.List;
import java.util.regex.Pattern;
import vendingmachine.util.GlobalExceptions;
import vendingmachine.util.Parser;

public record Stock(String name, int price, int quantity) {

    @SuppressWarnings("RegExpRedundantEscape")
    private static final Pattern bracketTrimmer = Pattern.compile("\\[(.+)\\]");
    private static final Parser commaParser = new Parser(',');

    public Stock {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(name.isBlank());
        // 상품 가격은 100원 이상이고, 10원으로 나누어 떨어져야 해
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(price < 100 && price % 10 != 0);
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(quantity < 0);
    }

    public Stock(String name, String price, String quantity) throws NumberFormatException {
        this(name, Integer.parseInt(price), Integer.parseInt(quantity));
    }

    // 입력은 `[콜라,1500,20]` 형식
    // 각 상품의 속성(상품명, 가격, 수량)은 대괄호를 제외하고, 쉼표(`,`)로 구분
    public static Stock of(String s) {
        var matcher = bracketTrimmer.matcher(s);
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(!matcher.find());
        String group = matcher.group(1);
        List<String> props = commaParser.parse(group);
        try {
            return new Stock(props.get(0), props.get(1), props.get(2));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw GlobalExceptions.INVALID_ARGUMENTS.exception(e);
        }
    }
}
