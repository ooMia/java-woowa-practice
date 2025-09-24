- [x] 일단 기능 테스트 통과
- [x] 주어진 예외 테스트 통과

- [x] View 클래스로 UI 로직 분리

- 상수 클래스

  - [x] 메세지 템플릿 메서드
  - [x] 상수들 enum으로 대체
    - [x] 등수와 상금 액수

- [x] 로또 당첨 결과를 저장하는 Prize enum 구현
- [x] `List<Lotto>` 및 필요 인수를 전달받아, 당첨 요약과 통계를 출력하는 `PrizeSummary` 클래스 구현
- [x] `Lotto`를 인수로 받는 메서드를 `Lotto`의 메서드로 이동

- [x] 보너스 번호 입력 시, 당첨 번호와 중복되는지 검사하는 기능 추가

- [x] Exception 발생을 관리하는 별도의 클래스 활용

- [x] Lotto 번호 생성
- [x] Lotto 수익률 계산
- [x] View는 순수하게 출력만 처리하고, 사용자 입력 및 tryUntilValid 로직은 Controller에서 처리한다

# 요구 사항

- [x] else 예약어 및 switch/case 예약어를 사용하지 않고 구현
- [x] 3항 연산자를 쓰지 않는다.
- [x] indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
- [x] 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- [x] Java Enum을 적용한다.

- 도메인 로직에 단위 테스트를 구현한다.

# 참고 사항

VSCode에서 `Java Test Runner` Extension으로 테스트를 실행하려면, build.gradle을 다음과 같이 수정해야 한다.
단, 요구사항에 build.gradle 파일은 수정 불가하다고 명시되어 있으므로, 테스트를 실행한 후에는 반드시 원래대로 복구해야 한다.

```groovy
dependencies {
    implementation 'com.github.woowacourse-projects:mission-utils:1.1.0'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```
