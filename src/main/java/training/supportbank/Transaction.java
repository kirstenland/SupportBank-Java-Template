package training.supportbank;

import java.util.Date;

public class Transaction {
    protected Date date;
    protected Account from;
    protected Account to;
    protected String narrative;
    protected Integer amount;

    public Transaction(Date date, Account from, Account to, String narrative, double pounds) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.narrative = narrative;
        this.amount = Toolkit.convertPoundsToPence(pounds);
    }

    public void process() {
        to.updateBalance(amount);
        from.updateBalance(-amount);
    }

    public void display() {
        Toolkit.displayFormatter(Toolkit.displayDate(date), from.getName(), to.getName(), narrative, Toolkit.displayPenceAsPounds(amount));
    }
}
