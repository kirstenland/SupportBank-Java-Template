package training.supportbank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transaction {
    protected Date date;
    protected Account from;
    protected Account to;
    protected String narrative;
    protected Integer amount;

    public Transaction(String date, Account from, Account to, String narrative, String amount) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        this.date = formatter.parse(date);
        this.from = from;
        this.to = to;
        this.narrative = narrative;
        this.amount = convertPoundsToPence(Double.parseDouble(amount));
    }

    public int convertPoundsToPence(double pounds) {
        return (int)(pounds*100);
    }

    public void process() {
        to.updateBalance(amount);
        from.updateBalance(-amount);
    }

    public void display() {
        System.out.println(date + from.getName() + to.getName() + narrative + amount);
    }
}
