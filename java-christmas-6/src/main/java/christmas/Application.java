package christmas;

public class Application {
    public static void main(String[] args) {
        var bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out));
        var inputView = new christmas.view.InputView(bw);
        var outputView = new christmas.view.OutputView(bw);
        var api = new christmas.Controller(inputView, outputView);

        try {
            api.greet();
            var visitDate = api.readVisitDate();
            var order = api.readOrder();
            api.printOrder(order);
            api.printDiscountSummary(order, visitDate);
        } catch (final Exception ignore) {
            // fallback
        }
    }
}
