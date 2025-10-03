# 요구사항 읽으면서 필요해보이는 작업 나열하기

- 최대 주문 개수가 20개로 제한되어 있으니, 전체 주문 금액은 1_200_000원을 넘지 않는다.

- [x] 전역 상수 정의하기
- [x] Menu enum 만들기 (범주, 이름, 가격)
- [x] Badge enum 만들기 (이름, 부여 기준)
- [x] 오류 코드 작성

- 주문 처리 (제약 존재)
- 기간별 조건부 혜택 (할인/증정)

- 총 혜택 금액에 따른 이벤트 배지 부여

- 사용자로부터의 입력 (성공할 때까지 반복 시도)
  1. 12월 중 식당 예상 방문 날짜
  2. 주문할 메뉴와 개수
- 출력 (방문 당일 이벤트 혜택)
  1. 주문 메뉴
  2. 할인 전 총주문 금액
  3. 증정 메뉴
  4. 혜택 내역
  5. 총 혜택 금액
  6. 할인 후 예상 결제 금액
  7. 12월 이벤트 배지

# 참고 사항

### 용어 정의

- 혜택: Benefit

### VSCode 테스트 설정

VSCode에서 `Java Test Runner` Extension으로 테스트를 실행하려면, build.gradle을 다음과 같이 수정해야 한다.
단, 요구사항에 build.gradle 파일은 수정 불가하다고 명시되어 있으므로, 테스트를 실행한 후에는 반드시 원래대로 복구해야 한다.

```groovy
dependencies {
    implementation 'com.github.woowacourse-projects:mission-utils:1.1.0'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```
