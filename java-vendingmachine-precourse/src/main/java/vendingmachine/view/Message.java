package vendingmachine.view;

import java.util.IllegalFormatException;

import vendingmachine.util.ExceptionUtil;

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
