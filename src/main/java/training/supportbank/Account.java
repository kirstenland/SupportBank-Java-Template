package training.supportbank;

public class Account {
    private String name;
    private int balance;

    public Account(String name) {
        this.name = name;
        balance = 0;
    }

    public String getName() {
        return name;
    }

    public void updateBalance(int amount) {
        balance += amount;
    }

    public void display() {
        System.out.println(name + " has £" + Toolkit.convertPenceToPounds(balance) + " in their account.");
    }
}
