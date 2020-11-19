package training.supportbank;

public class Account {
    private String name;
    private int balance;

    public Account(String name) {
        this.name = name;
        balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int amount) {
        balance += amount;
    }
}
