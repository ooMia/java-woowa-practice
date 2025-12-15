package calculator.delimiter;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IntegerCustomDelimiter extends IntegerDefaultDelimiter {

    private static String getDelimiter(Matcher matcher) throws IllegalArgumentException {
        var delimiter = matcher.group(1);
        if (delimiter.matches(DefaultDelimiter.DEFAULT_REGEX) || delimiter.matches("\\d")) {
            throw new IllegalArgumentException();
        }
        return Pattern.quote(delimiter);
    }

    private static Pattern getPattern() {
        var prefix = "//";
        var suffix = "\\n";
        var regex = String.format("%s(.)%s", Pattern.quote(prefix), Pattern.quote(suffix));
        return Pattern.compile(regex, Pattern.DOTALL);
    }

    @Override
    protected List<String> split(String expressionWithRule) {
        Matcher matcher = getPattern().matcher(expressionWithRule);
        if (!matcher.find()) {
            // fallback to default behavior
            return super.split(expressionWithRule);
        }
        String regex = getDelimiter(matcher);

        String expression = expressionWithRule.substring(matcher.end());
        if (expression.isEmpty()) {
            return Collections.emptyList();
        }
        return List.of(expression.split(regex));
    }
}
