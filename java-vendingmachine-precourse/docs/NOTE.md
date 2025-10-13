# NOTE

## 코딩 스타일 가이드

### 프로젝트 구조

- (root)
  - `Application`
  - `Constant`
  - `ErrorCode` (enum)
  - `Controller` (package-private)
- model (public)
  - in (record)
  - out (record)
- view
  - `Message` (package-private enum)
  - `InputView`
  - `OutputView`
- service
  - `MainService` (public)
    - `Parser` (package-private)
    - `Mapper` (package-private)
  - (subServices...)
- util
  - `ExceptionUtil`

### 기본 원칙

- 개발 속도가 중요하다. 코드 내 한글을 적극적으로 활용한다.
- `Application::main`에 컨트롤러 인터페이스를 활용하여 애플리케이션 흐름을 표현한다.
  - Controller는 가능하면 상태 없이 동작하도록 설계한다.
- 요구사항에 명시된 상수/기준/조건 등은 `Constant`에 전역 변수로 정의
  - 주석을 통해 참조(파일명, 줄 번호)를 명확히 한다.
  - 단, 도메인과 관련된 연속성을 가진 데이터는 model enum으로 정의
- 도메인 관련 예외는 `ErrorCode` enum으로 정의
- view.Message를 활용하여 출력 메시지를 관리한다.
  - 불변이면 `toString()`, 인자가 필요하면 `format(Object...)` 메서드 활용
- service는 일반적으로 다음과 같은 흐름을 기대한다.
  - model.in -> (mainService => subServices) -> model.out
- model.in, model.out에는 `record`를 활용하여 DTO로서의 역할에 충실하게 한다.
- model은 도메인 로직을 처리하기 위한 핵심 객체를 포함한다.
  - 나누기 전에 이 객체가 반드시 여러 개 필요한 이유를 찾는다.
  - 하나 밖에 안 보이면, 더 작은 단위로 나누거나, 통쨰로 서비스로 만든다.
  - 가능하면 서비스 내부에서만 다루는 것이 좋다.
  - toString 오버라이딩해서 DTO 변환에 활용해도 좋다.
  - 설계 시, 유사성과 차이점을 고려하자.
    (e.g. 유사성을 가진 객체들 간에 차이가 있는 상황)
    - 단순 나열이라면 컬렉션 또는 enum으로 관리
    - 또는 서비스 수준에서 분기문으로 처리하거나
    - 너무 복잡하면 공통 인터페이스 추상화 후, 개별 구현체 컬렉션으로 관리 (전략/정책 패턴)

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

- `java.format.settings.url`은 다음 링크를 통해 웹에서 로드해도 된다.
`https://gist.githubusercontent.com/ooMia/1a47bdf9ef00c3466d1f506aa99f4acb/raw/d59ed8e847a582970c3f62e1e9883e3b1c62e908/woowa-style.xml`

```json
{
  "java.format.settings.url": "../.vscode/woowa-style.xml",
  "java.configuration.updateBuildConfiguration": "interactive",
  "[java]": {
    "editor.detectIndentation": false,
    "editor.renderWhitespace": "boundary",
    "editor.inlayHints.enabled": "on",
    "editor.parameterHints.enabled": true,
    "editor.suggest.snippetsPreventQuickSuggestions": false,
    "editor.suggest.showMethods": true,
    "editor.suggest.showFields": true
  },
  "java.saveActions.organizeImports": true,
  "java.codeGeneration.toString.codeStyle": "STRING_FORMAT",
  "java.completion.favoriteStaticMembers": [
    "org.assertj.core.api.Assertions.*",
    "org.junit.Assert.*",
    "org.junit.jupiter.api.Assertions.*"
  ]
}
```

### 보일러 플레이트 코드 생성

프로젝트 루트에서 다음 명령어를 실행한다.

> ```sh
> chmod +x docs/scaffold.sh && docs/scaffold.sh
> ```

### 레거시 프로젝트 빌드 시 참고

- `./gradlew wrapper --gradle-version=8.7 --distribution-type=bin`
- `java.clean.workspace`
- .gitignore `/out/`
