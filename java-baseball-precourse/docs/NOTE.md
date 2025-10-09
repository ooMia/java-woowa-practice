# NOTE

## 코딩 스타일 가이드

### 프로젝트 구조

- (root)
  - `Application.java`
  - `Controller.java`
  - `Constant.java`
  - `ErrorCode.java` (enum)
- model
  - model.in (record)
  - model.out (record)
- view
  - `Message.java` (package-private enum)
  - `InputView.java`
  - `OutputView.java`
- service
  - `MainService.java`
  - (subServices...) (package-private)
- util
  - `ExceptionHandler.java`

### 기본 원칙

- 개발 속도가 중요하다. 코드 내 한글을 적극적으로 활용한다.
- `Application::main`에 컨트롤러 인터페이스를 활용하여 애플리케이션 흐름을 표현한다.
- 요구사항에 명시된 상수/기준/조건 등은 `Constant`에 전역 변수로 정의
  - 주석을 통해 참조(파일명, 줄 번호)를 명확히 한다.
  - 단, 도메인과 관련된 연속성을 가진 데이터는 model enum으로 정의
- 도메인 관련 예외는 `ErrorCode` enum으로 정의
- model.in, model.out에는 `record`를 활용하여 DTO로서의 역할에 충실하게 한다.
- view.Message를 활용하여 출력 메시지를 관리한다.
  - 불변이면 `text()`, 인자가 필요하면 `format(Object...)` 메서드 활용
- service는 일반적으로 다음과 같은 흐름을 기대한다.
  - model.in -> (mainService => subServices) -> model.out
- model 엔티티는 도메인 로직을 처리하기 위한 핵심 객체이다.
  - 가능하다면 외부에 노출하지 않는 것이 가장 좋다.
  - 각 엔티티 별로 subService를 분리하면 좋다.
  - 엔티티 설계 시, 유사성과 차이점을 고려하면 좋다.
    (e.g. 유사성을 가진 객체들 간에 차이가 있는 상황)
    - 단순 나열이라면 컬렉션 또는 enum으로 관리
    - 또는 서비스 수준에서 분기문으로 처리하거나
    - 너무 복잡하면 공통 인터페이스 추상화 후, 개별 구현체 컬렉션으로 관리 (전략/정책 패턴)
- util.ExceptionHandler
  - Constant.ERROR_PREFIX 활용
  - ExceptionHandler::exception(String message)
  - ExceptionHandler::tryUntilValid

## 프로젝트 설정

### VSCode 테스트 설정

VSCode에서 `Java Test Runner` Extension으로 테스트를 실행하려면, build.gradle을 다음과 같이 수정해야 한다. 단, 요구사항에 build.gradle 파일은 수정 불가하다고 명시되어 있으므로 커밋되지 않도록 주의한다. 확장 오류가 난다면 확장 버전의 안정성을 점검해본다.

```groovy
dependencies {
    implementation 'com.github.woowacourse-projects:mission-utils:1.1.0'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

### .vscode/settings.json

두 방법 중 하나를 선택하여 설정에 반영한다.

```json
{
  "java.format.settings.url": "https://gist.githubusercontent.com/ooMia/1a47bdf9ef00c3466d1f506aa99f4acb/raw/9e2a7274831a0c4ef15c0b67685e747f981b1b86/woowa-style.xml",

  "java.format.settings.url": "../.vscode/woowa-style.xml"
}
```

### 보일러 플레이트 코드 생성

프로젝트 루트에서 다음 명령어를 실행한다.

> ```sh
> chmod +x docs/scaffold.sh && docs/scaffold.sh
> ```
