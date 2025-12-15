package baseball.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class AnswerStorageServiceTest {
    @Test
    void testCreate_1에서_9사이_고유한_숫자_3개() {
        // 외부 라이브러리를 활용하여 입력되는 무작위한 값에 대해서는
        // 통합 테스트 수준에서 검증하기
        var instance = new AnswerStorageService().create();
        var res = new HashSet<Integer>(instance);

        assertTrue(res.size() == 3);
        for (var e : res) assertTrue(isValid(e));
    }

    private boolean isValid(Integer n) {
        return 1 <= n && n <= 9;
    }
}
