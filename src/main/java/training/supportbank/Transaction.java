package training.supportbank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transaction {
    protected Date date;
    protected String from;
    protected String to;
    protected String narrative;
    protected Integer amount;

    public Transaction(String[] transaction) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        date = formatter.parse(transaction[0]);
        from = transaction[1];
        to = transaction[2];
        narrative = transaction[3];
        amount = convertPoundsToPence(Double.parseDouble(transaction[4]));
    }

    public int convertPoundsToPence(double pounds) {
        return (int)pounds*10;
    }

    public double convertPenceToPounds(int pence) {
        return (double)pence/10;
    }
}
