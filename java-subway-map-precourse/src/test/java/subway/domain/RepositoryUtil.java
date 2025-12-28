package subway.domain;

class RepositoryUtil {
    static void resetRepositories() {
        IntervalRepository.clear();
        LineRepository.clear();
        StationRepository.clear();
    }
}
