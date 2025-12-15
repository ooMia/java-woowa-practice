package vendingmachine.util;

import java.util.function.Predicate;

/**
 * 자주 사용되는 검증 규칙과 손쉽게 작성할 수 있는 헬퍼 클래스. 던져지는 checked exception을 상황에 맞게 직접 처리해야 한다.
 */
@SuppressWarnings("unused")
public final class ValueRules {

    public static final CanValidate<Integer> POSITIVE = new OfAny<>(value -> value > 0);
    public static final CanValidate<Integer> NON_NEGATIVE = new OfAny<>(value -> value >= 0);
    public static final CanValidate<String> NON_BLANK = new OfAny<>(value -> !value.isBlank());

    private ValueRules() {
        /* no-op */
    }

    public static <T> CanValidate<T> ofAny(Predicate<T> assertion) {
        return new OfAny<>(assertion);
    }

    public static CanValidate<String> ofRegexMatched(String regex) {
        return new RegexMatched(regex);
    }

    public static CanValidate<Integer> ofInRangeInclusive(int minInclusive, int maxInclusive) {
        return new InRangeInclusive(minInclusive, maxInclusive);
    }

    @FunctionalInterface
    public interface CanValidate<T> {
        /**
         * Throws exception if assertion fails.
         *
         * @param value value to test
         * @throws InvalidValueException if {@code value} is fails assertion or null
         */
        T validate(T value) throws InvalidValueException;
    }

    private record OfAny<T>(Predicate<T> assertion) implements CanValidate<T> {
        @Override
        public T validate(T value) throws NullPointerException, InvalidValueException {
            return InvalidValueException.of(value).throwsIf(assertion, this.getClass());
        }
    }

    private record RegexMatched(java.util.regex.Pattern pattern) implements CanValidate<String> {
        private RegexMatched(String pattern) throws java.util.regex.PatternSyntaxException {
            this(java.util.regex.Pattern.compile(pattern));
        }

        @Override
        public String validate(String value) throws InvalidValueException {
            return InvalidValueException.of(value).throwsIf(pattern.matcher(value).matches(), this.getClass());
        }
    }

    private record InRangeInclusive(int min, int max) implements CanValidate<Integer> {
        @Override
        public Integer validate(Integer value) throws NullPointerException, InvalidValueException {
            return InvalidValueException.of(value).throwsIf(min <= value && value <= max, this.getClass());
        }
    }

    public static final class InvalidValueException extends Exception {
        private InvalidValueException(String message) {
            super(message);
        }

        private static <T> Validator<T> of(T value) {
            return new Validator<>(value);
        }

        private static class Validator<T> {
            T value;

            Validator(T value) {
                this.value = value;
            }

            T throwsIf(Predicate<T> assertion, Class<?> invoker) throws InvalidValueException {
                if (value == null || !assertion.test(value)) {
                    throw new InvalidValueException(invoker.toGenericString());
                }
                return value;
            }

            T throwsIf(boolean assertion, Class<?> invoker) throws InvalidValueException {
                if (value == null || !assertion) {
                    throw new InvalidValueException(invoker.toGenericString());
                }
                return value;
            }
        }
    }
}
