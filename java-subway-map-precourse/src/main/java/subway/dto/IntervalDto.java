package subway.dto;

public record IntervalDto(String line, Iterable<String> stations) {
}
