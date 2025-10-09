package baseball;

public class Application {
    public static void main(String[] args) {
        var api = new Controller();
        var input = api.inputBoo();
        var output = api.process(input);
        api.outputBoo(output);
    }
}
