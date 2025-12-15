#!/usr/bin/env bash
set -u

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
SRC_MAIN="$ROOT_DIR/src/main/java"

# 1. 루트 패키지(예: src/main/java/*) 목록 수집. 없으면 종료.
ROOT_PKGS=()
while IFS= read -r -d '' dir; do
  ROOT_PKGS+=("$dir")
done < <(find "$SRC_MAIN" -mindepth 1 -maxdepth 1 -type d -print0 2>/dev/null)

[ ${#ROOT_PKGS[@]} -gt 0 ] || { echo "No root packages found in $SRC_MAIN"; exit 1; }

# 2. 파일이 없을 때만 생성하는 헬퍼
create_if_absent() {
  local path="$1"
  if [ -e "$path" ]; then
    echo "skip: $path"
    return 0
  fi
  mkdir -p "$(dirname "$path")"
  cat > "$path"
  echo "create: $path"
}

# 3. 각 패키지에 대해 스캐폴드 수행
for PKG_PATH in "${ROOT_PKGS[@]}"; do
  [ -d "$PKG_PATH" ] || continue
  PKG_NAME="$(basename "$PKG_PATH")"

  p_root="$PKG_PATH"
  p_model_in="$PKG_PATH/model/in"
  p_model_out="$PKG_PATH/model/out"
  p_view="$PKG_PATH/view"
  p_service="$PKG_PATH/service"
  p_util="$PKG_PATH/util"

  # ---------------- root ----------------
  create_if_absent "$p_root/Application.java" <<EOF
package $PKG_NAME;

public class Application {
    public static void main(String[] args) {
        var api = new Controller();
        var input = api.inputBoo();
        var output = api.process(input);
        api.outputBoo(output);
    }
}
EOF

  create_if_absent "$p_root/Controller.java" <<EOF
package $PKG_NAME;

import $PKG_NAME.model.in.SampleInput;
import $PKG_NAME.model.out.SampleOutput;
import $PKG_NAME.service.MainService;
import $PKG_NAME.util.ExceptionHandler;
import $PKG_NAME.view.InputView;
import $PKG_NAME.view.OutputView;

public class Controller {
    private final MainService mainService = new MainService();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public SampleInput inputBoo() {
        return ExceptionHandler.tryUntilValid(() -> {
            inputView.inputBooHeader();
            return inputView.readBoo();
        });
    }

    public SampleOutput process(SampleInput input) {
        return mainService.process(input);
    }

    public void outputBoo(SampleOutput output) {
        outputView.print(output);
    }
}
EOF

  create_if_absent "$p_root/Constant.java" <<EOF
package $PKG_NAME;

import $PKG_NAME.util.ExceptionHandler;

public final class Constant {
    public static final String ERROR_PREFIX = "[ERROR]";
    static {
        ExceptionHandler.setErrorPrefix(ERROR_PREFIX);
    }

    private Constant() {
    }
}
EOF

  create_if_absent "$p_root/ErrorCode.java" <<EOF
package $PKG_NAME;

import $PKG_NAME.util.ExceptionHandler;

public enum ErrorCode {
    // SCOPE_(REASON)
    INPUT_INVALID("유효하지 않은 입력 값입니다.", IllegalArgumentException.class);

    private final String message;
    private final Class<? extends RuntimeException> exceptionClass;

    ErrorCode(String message, Class<? extends RuntimeException> exceptionClass) {
        this.message = message;
        this.exceptionClass = exceptionClass;
    }

    public RuntimeException exception() {
        return ExceptionHandler.instantiate(exceptionClass, message);
    }
}
EOF

  # ---------------- util ----------------
  create_if_absent "$p_util/ExceptionHandler.java" <<EOF
package $PKG_NAME.util;

public final class ExceptionHandler {
    private static String ERROR_PREFIX = "[ERROR]";

    private ExceptionHandler() {
    }

    public static void setErrorPrefix(String prefix) {
        ERROR_PREFIX = prefix;
    }

    public static RuntimeException exception(String message) {
        return new IllegalArgumentException(ERROR_PREFIX + " " + message);
    }

    public static <T> T tryUntilValid(java.util.function.Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static RuntimeException instantiate(
            Class<? extends RuntimeException> exceptionClass,
            String message
    ) {
        try {
            return exceptionClass
                    .getConstructor(String.class)
                    .newInstance(ERROR_PREFIX + " " + message);
        } catch (Exception e) {
            throw new RuntimeException("ExceptionHandler::instantiate", e);
        }
    }
}
EOF

  # ---------------- view ----------------
  create_if_absent "$p_view/Message.java" <<EOF
package $PKG_NAME.view;

import java.util.IllegalFormatException;

import $PKG_NAME.util.ExceptionHandler;

enum Message {
    // (IN/OUT)_SCOPE_(FORMAT)
    OUT_GREETING("반갑습니다."),
    OUT_QUESTION_FORMAT("%s의 답은?"),
    IN_INSTRUCTION("입력해주세요: ");

    private final String text;

    Message(String text) {
        this.text = text;
    }

    String text() {
        return text;
    }

    String format(Object... args) {
        try {
            return String.format(text, args);
        } catch (IllegalFormatException e) {
            throw ExceptionHandler.exception("Message::format");
        }
    }
}
EOF

  create_if_absent "$p_view/InputView.java" <<EOF
package $PKG_NAME.view;

import $PKG_NAME.model.in.SampleInput;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public void inputBooHeader() {
        System.out.print(Message.IN_INSTRUCTION.text());
    }

    public SampleInput readBoo() {
        var number = Integer.parseInt(Console.readLine());
        return new SampleInput(number);
    }

    public String readLine() {
        return Console.readLine();
    }
}
EOF

  create_if_absent "$p_view/OutputView.java" <<EOF
package $PKG_NAME.view;

import $PKG_NAME.model.out.SampleOutput;

public class OutputView {

    public void print(SampleOutput output) {
        System.out.println(Message.OUT_QUESTION_FORMAT.format(output.foo()));
        System.out.println(output.boo());
    }
}
EOF

  # ---------------- model ----------------
  create_if_absent "$p_model_in/SampleInput.java" <<EOF
package $PKG_NAME.model.in;

public record SampleInput(int a) {
    public SampleInput {
        // TODO: validations
    }
}
EOF

  create_if_absent "$p_model_out/SampleOutput.java" <<EOF
package $PKG_NAME.model.out;

public record SampleOutput(int foo, String boo) { }
EOF

  # ---------------- service ----------------
  create_if_absent "$p_service/MainService.java" <<EOF
package $PKG_NAME.service;

import $PKG_NAME.model.in.SampleInput;
import $PKG_NAME.model.out.SampleOutput;

public class MainService {
    private final SampleSubService service = new SampleSubService();

    public SampleOutput process(SampleInput input) {
        var message = service.doWork("ok");
        return new SampleOutput(input.a(), message);
    }
}
EOF

  create_if_absent "$p_service/SampleSubService.java" <<EOF
package $PKG_NAME.service;

class SampleSubService {
    String doWork(String s) {
        return s.toUpperCase();
    }
}
EOF

done

echo "Scaffold completed."