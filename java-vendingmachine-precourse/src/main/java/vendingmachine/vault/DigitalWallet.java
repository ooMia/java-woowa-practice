package vendingmachine.vault;

import vendingmachine.util.GlobalExceptions;

public final class DigitalWallet implements Wallet<Integer> {

    private int balance = 0;

    @Override
    public void deposit(Integer balance) throws ArithmeticException {
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(balance < 0);
        this.balance = Math.addExact(this.balance, balance);
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public Integer withdraw(Integer request){
        GlobalExceptions.INVALID_ARGUMENTS.throwsIf(request < 0);
        int affordable = Math.min(request, balance);
        balance -= affordable;
        return affordable;
    }
}
