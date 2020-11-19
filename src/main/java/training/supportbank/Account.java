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

    public String getName() {
        return name;
    }

    public void updateBalance(int amount) {
        balance += amount;
    }

    public double convertPenceToPounds(int pence) {
        return (double)pence/100;
    }

    public void display() {
        System.out.println(name + " has £" + convertPenceToPounds(balance) + " in their account.");
    }
}
