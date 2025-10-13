# 기본 준비사항

1. `chmod +x docs/scaffold.sh && docs/scaffold.sh`
2. `./gradlew test` 및 VSCode 테스트 확장 동작 확인

# 1차 체크리스트 (공통 정의)

- [x] Constant 초안 작성 (README 줄 번호 주석)
- [x] model.in / out
- [ ] ErrorCode enum
- [ ] model.in 제약 반영
- [ ] view.Message

# 2차 체크리스트 (설계)

- [ ] Application::main -> Controller 흐름
- [ ] Controller -> MainService 흐름
- [ ] Controller -> InputView/OutputView 흐름
- [ ] MainService -> Parser 흐름
- [ ] MainService -> Mapper 흐름
- [ ] SubService 흐름

# 3차 체크리스트 (메인 스트림)

- [ ] SubService 구현 및 단위 테스트
- [ ] MainService 구현
- [ ] InputView/OutputView 구현 (Message 활용)

# 4차 체크리스트 (테스트)

- [ ] 기본 통합 테스트를 '어떻게든' 통과하는가?
- [ ] 기본 예외 테스트를 '어떻게든' 통과하는가?
- [ ] README에 활용 가능한 예제를 테스트로 추가했는가?
- [ ] README.md를 정독하며 1차 체크리스트 재점검

# 5차 체크리스트 (서브 스트림)

- 추가적인 예외 상황이 떠오른다면, ErrorCode에 추가하고 테스트로 검증하자.
- 각 클래스가 속한 패키지의 컨셉에 맞게 구현되었는지 점검하자.
- 디자인 패턴 활용 가능성이 보인다면 리팩토링 해보자.
- 동일한 속성, 상이한 동작을 가지는 객체들이 있다면, 공통 인터페이스로 추상화하고 개별 구현체로 관리하자.
