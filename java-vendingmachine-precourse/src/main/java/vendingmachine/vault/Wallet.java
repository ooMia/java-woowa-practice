package vendingmachine.vault;

public interface Wallet<T> {
    void deposit(T balance);

    T getBalance();

    @SuppressWarnings("UnusedReturnValue")
    T withdraw(T balance);
}
