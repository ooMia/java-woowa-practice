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

# 2. 파일 생성 헬퍼
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

create_and_overwrite() {
  local path="$1"
  mkdir -p "$(dirname "$path")"
  cat > "$path"
  echo "overwrite: $path"
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
  create_and_overwrite "$p_root/Application.java" <<EOF
package $PKG_NAME;

import $PKG_NAME.model.in.Boo;
import $PKG_NAME.model.in.Foo;
import $PKG_NAME.model.out.SampleOutput;

public class Application {
    public static void main(String[] args) {
        Controller api = new Controller();
        try {
            Foo foo = api.inputFoo();
            Boo boo = api.inputBoo();
            SampleOutput result = api.process(foo, boo);
            api.printSampleOutput(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
EOF

  create_if_absent "$p_root/Controller.java" <<EOF
package $PKG_NAME;

import camp.nextstep.edu.missionutils.Console;
import $PKG_NAME.model.in.Boo;
import $PKG_NAME.model.in.Foo;
import $PKG_NAME.model.out.SampleOutput;
import $PKG_NAME.service.MainService;
import $PKG_NAME.util.ExceptionUtil;
import $PKG_NAME.view.InputView;
import $PKG_NAME.view.OutputView;

class Controller {

    private static final MainService serviceEntry = new MainService();
    private static final InputView in = new InputView();
    private static final OutputView out = new OutputView();

    private static <T> T _parse(Class<T> target) throws RuntimeException {
        return serviceEntry.parse(Console.readLine(), target);
    }

    private static <T> T _tryParseUntilValid(Class<T> target) {
        return ExceptionUtil.tryUntilValid(() -> _parse(target));
    }

    // TODO read String from std.in return as model.in object

    public Foo inputFoo() {
        var target = Foo.class;
        System.out.print(in.instruction(target));
        return _tryParseUntilValid(target);
    }

    public Boo inputBoo() {
        var target = Boo.class;
        System.out.print(in.instruction(target));
        return _parse(target);
    }

    // TODO process model.in objects and return model.out object

    public SampleOutput process(Foo foo, Boo boo) {
        return serviceEntry.process(foo, boo);
    }

    // TODO print model.out object to std.out

    public void printSampleOutput(SampleOutput result) {
        System.out.println(out.stringify(result));
    }

}
EOF

  create_if_absent "$p_root/Constant.java" <<EOF
package $PKG_NAME;

import $PKG_NAME.util.ExceptionUtil;

public final class Constant {
    public static final String ERROR_PREFIX = "[ERROR]";
    static {
        ExceptionUtil.setErrorPrefix(ERROR_PREFIX);
    }

    private Constant() {
    }
}
EOF

  # Coin.java는 생성하지 않음 (요구사항)

  create_if_absent "$p_root/ErrorCode.java" <<EOF
package $PKG_NAME;

import java.util.IllegalFormatException;

import $PKG_NAME.util.ExceptionUtil;

public enum ErrorCode {
    // SCOPE_(REASON)
    INPUT_INVALID("유효하지 않은 입력 값입니다.", IllegalArgumentException.class),
    ;

    private final String message;
    private final Class<? extends RuntimeException> exceptionClass;

    ErrorCode(String message, Class<? extends RuntimeException> exceptionClass) {
        this.message = message;
        this.exceptionClass = exceptionClass;
    }

    public RuntimeException exception() {
        return ExceptionUtil.instantiate(exceptionClass, message);
    }

    public RuntimeException exception(String detail) {
        return ExceptionUtil.instantiate(exceptionClass, message + ": " + detail);
    }
}
EOF

  # ---------------- util ----------------
  create_if_absent "$p_util/ExceptionUtil.java" <<EOF
package $PKG_NAME.util;

public final class ExceptionUtil {
    private static String ERROR_PREFIX = "[ERROR]";

    private ExceptionUtil() {
    }

    public static void setErrorPrefix(String prefix) {
        ERROR_PREFIX = prefix;
    }

    public static IllegalArgumentException exception(String message) {
        return new IllegalArgumentException(ERROR_PREFIX + " " + message);
    }

    public static UnsupportedOperationException unsupported(String scope, String message) {
        return new UnsupportedOperationException(ERROR_PREFIX + " " + scope + ": " + message);
    }

    public static <T> T tryUntilValid(java.util.function.Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
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

import $PKG_NAME.util.ExceptionUtil;

enum Message {
    // (IN/OUT)_SCOPE_(FORMAT)
    IN_("input foo: "),
    ;

    private final String text;

    Message(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    String format(Object... args) {
        try {
            return String.format(text, args);
        } catch (IllegalFormatException e) {
            throw ExceptionUtil.unsupported("Message::format", this.name());
        }
    }
}
EOF

  create_if_absent "$p_view/InputView.java" <<EOF
package $PKG_NAME.view;

import $PKG_NAME.model.in.Boo;
import $PKG_NAME.model.in.Foo;
import $PKG_NAME.util.ExceptionUtil;

public class InputView {

    private static RuntimeException unsupported(Class<?> cls) {
        return ExceptionUtil.unsupported("InputView::instruction", cls.getSimpleName());
    }

    // TODO map instruction message for each model.in class

    public <T> String instruction(Class<T> target) {
        if (target == Foo.class) return Message.IN_.toString();
        if (target == Boo.class) return "input boo: ";
        throw unsupported(target);
    }

}
EOF

  create_if_absent "$p_view/OutputView.java" <<EOF
package $PKG_NAME.view;

import $PKG_NAME.model.out.SampleOutput;
import $PKG_NAME.util.ExceptionUtil;

public class OutputView {

    private static RuntimeException unsupported(Object obj) {
        return ExceptionUtil.unsupported("OutputView::stringify", obj.getClass().getSimpleName());
    }

    public <T> String stringify(Object target) {
        return switch (target) {
            case SampleOutput sampleOutput -> stringify(sampleOutput);
            default -> throw unsupported(target);
        };
    }

    // TODO 각 model.out 객체별로 stringify 메서드 추가

    String stringify(SampleOutput result) {
        return result.toString();
    }
}
EOF

  # ---------------- model ----------------
  create_if_absent "$p_model_in/Foo.java" <<EOF
package $PKG_NAME.model.in;

public record Foo(int a) {
    public Foo {
        // TODO: validations
    }
}
EOF

  create_if_absent "$p_model_in/Boo.java" <<EOF
package $PKG_NAME.model.in;

import java.util.Map;

public record Boo(Map<String, Integer> map) {
    public Boo {
        // TODO: validations
    }
}
EOF

  create_if_absent "$p_model_out/SampleOutput.java" <<EOF
package $PKG_NAME.model.out;

import java.util.Map;

public record SampleOutput(Map<String, Integer> map) { }
EOF

  # ---------------- service ----------------
  create_if_absent "$p_service/MainService.java" <<EOF
package $PKG_NAME.service;

import $PKG_NAME.model.in.Boo;
import $PKG_NAME.model.in.Foo;
import $PKG_NAME.model.out.SampleOutput;

public class MainService {
    private final Mapper mapper = new Mapper();
    private final Parser parser = new Parser();

    public <T> T parse(String line, Class<T> target) {
        return parser.parse(line, target);
    }

    public SampleOutput process(Foo foo, Boo boo) {
        return mapper.map(foo, boo);
    }

}
EOF

  create_if_absent "$p_service/Parser.java" <<EOF
package $PKG_NAME.service;

import java.util.Map;

import $PKG_NAME.model.in.Boo;
import $PKG_NAME.model.in.Foo;
import $PKG_NAME.util.ExceptionUtil;

// responsible for parsing inputs
// String -> model.in
class Parser {

    private static RuntimeException unsupported(Class<?> cls) {
        return ExceptionUtil.unsupported("Parser::parse", cls.getSimpleName());
    }

    private static String[] _split(String delimiter, String line) {
        return line.split(String.format("\\\\s*%s\\\\s*", delimiter));
    }

    public <T> T parse(String line, Class<T> target) {
        if (target == Foo.class) return target.cast(toFoo(line));
        if (target == Boo.class) return target.cast(toBoo(line));
        throw unsupported(target);
    }

    // TODO package-private helper methods (String -> model.in)

    Foo toFoo(String line) {
        return new Foo(Integer.parseInt(line));
    }

    Boo toBoo(String line) {
        return new Boo(Map.of(
                "a", 1,
                "b", 2
        ));
    }
}
EOF

  create_if_absent "$p_service/Mapper.java" <<EOF
package $PKG_NAME.service;

import $PKG_NAME.model.in.Boo;
import $PKG_NAME.model.in.Foo;
import $PKG_NAME.model.out.SampleOutput;

// responsible for mapping models
// model.in -> (model) -> model.out
class Mapper {

    // TODO package-private helper methods

    SampleOutput map(Foo foo, Boo boo) {
        return new SampleOutput(boo.map());
    }

}
EOF

done

echo "Scaffold completed."