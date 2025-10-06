- 애플리케이션 흐름은 Application::main에 표현
- 요구사항에 명시된 상수/기준/조건 등은 Constant에 전역 변수로 정의하고 점진적으로 캡슐화
  - 변수 명명 방식이 고민된다면 prefix + id 형태로 임시 정의 후 리팩토링
  - 연속적인 데이터 유형은 model에 enum으로 정의
- 예외 발생 시 반환할 메세지는 ErrorCode enum에 정의
  - `(SCOPE)_(REASON)` 형태로 명명
  - ErrorCode::exception은 util.ExceptionHandler::exception 호출
- controller는 main에 표현한 기능을 함수로 구현
- mvc 각 요소는 다음과 같은 의존성을 가집니다
  - model -> 독립적
  - view -> util
  - controller -> view & service
  - (mainService => subService) -> model
  - mapper -> model & model.out (& model.in)
- view는 util, view.Message 활용하여 구현
  - 출력 방식은 view.Message에 정의
    - package-private
    - 불변이면 상수화, 인자가 필요하면 함수화
    - model.out 객체 출력 방식 정의 (ex. `void printA(OutADto aDto)`)
- util.Console
  - util.Console::readLine (camp.nextstep 라이브러리 래핑)
  - util.Console::println (내부적으로 static BufferedWriter 사용)
- util.ExceptionHandler
  - Constant.ERROR_PREFIX 활용
  - ExceptionHandler::exception(String message)
  - ExceptionHandler::tryUntilValid
- model은 크게 세 가지로 나눈다

  - model.in: 사용자 입력 가공 (입력 검증 담당; `static of(String) -> record`)

    - 입력 검증을 위한 형변환 수행
    - 단순 자료형이 아니라면 Collection 활용
    - ```java
      package oncall.model.in;

      record InputDataA(int a) {
          InputDataA(int a) {
              this.a = a;
              // do validations
          }

          public static InputDataA of(String arg) {
              return new InputDataA(Integer.parseInt(arg));
          }
      }
      ```

- model.out: 출력 단위 객체
  - 동일한 형태가 나열된 형태라면 Iterable
  - dict처럼 매핑 관계라면 Map
  - 서로 다른 유형의 결합은 차례대로 나열
- model: 그 외 핵심 도메인 객체
- service: 복잡한 연산 또는 비즈니스 로직 처리
  - 일반적으로 다음과 같은 흐름을 기대합니다.
    1. model.in -> (mainService => subServices) -> model
    2. model -> mapper -> model.out
  - 동일 인자 -> 동일 결과는 공통 인터페이스 추상화 후, 구현체 목록으로 관리 (전략/정책 패턴)

---

# 기본 준비사항

1. [x] model.in 타입 결정
2. [ ] README.md 정리
   - [ ] Constant 작성; 주석 `// 줄_번호`
   - [ ] model.in `static record::of(String)` 제약조건 작성
   - [ ] 예제
3. [ ] 예제 -> 코드화
   - [ ] ApplicationTest 추가 (naive form)
   - [ ] Application::main 흐름
   - [ ] view.Message 정리
   - [ ] model.out 타입 결정

# 1차 체크리스트 (설계)

- [ ] Application::main에서 전체 흐름이 명확히 표현되어 있는가?
- [ ] controller에 `model.out foo(model.in)` 래퍼 함수를 호출하고 있는가?
- [ ] 연속적인 데이터가 model에 enum 자료으로 관리되고 있는가?
- [ ] 출력 관련 메세지가 view.Message에 모두 명시되었는가?

# 2차 체크리스트 (메인 스트림)

- [ ] 기본 테스트를 '어떻게든' 통과하는가?
- [ ] 기본 예외 테스트를 '어떻게든' 통과하는가?

# 3차 체크리스트 (서브 스트림)

- [ ] 입력이 실패해야 하는 상황이 model.in에 대한 단위 테스트로 모두 작성되었는가?
- [ ] 기본 예제 상황들이 model.in -> model 단위 테스트로 작성되었는가?

---

# model.in 타입 결정

비상 근무를 배정할 월과 시작 요일을 입력하세요> 5,월
평일 비상 근무 순번대로 사원 닉네임을 입력하세요> 준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리
휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> 수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니

csv 스타일
입력 1: int month, String weekDay
입력 2: String[] 평일 비상 근무 순번
입력 3: String[] 휴일 비상 근무 순번