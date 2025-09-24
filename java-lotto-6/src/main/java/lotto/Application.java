package lotto;

public class Application {
    public static void main(String[] args) {
        try {
            Controller controller = new Controller(new lotto.view.View());
            controller.run();
        } catch (final Exception ignore) {
            // unhandled exceptions
        }
    }
}
