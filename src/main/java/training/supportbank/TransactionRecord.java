package training.supportbank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransactionRecord {
    protected Date date;
    protected String from;
    protected String to;
    protected String narrative;
    protected Integer amount;

    public TransactionRecord(Date date, String from, String to, String narrative, Double amount) throws ParseException {
        this.date = date;
        this.from = from;
        this.to = to;
        this.narrative = narrative;
        this.amount = Toolkit.convertPoundsToPence(amount);
    }

    public static TransactionRecord fromCSVLine(String line) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String[] transaction = line.split(",");
        Date date = formatter.parse(transaction[0]);
        String fromName = transaction[1];
        String toName = transaction[2];
        String narrative = transaction[3];
        Double amount = Double.parseDouble(transaction[4]);

        return new TransactionRecord(date, fromName, toName, narrative, amount);
    }

    public static TransactionRecord fromJSONLine(TransactionDummy transaction) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse(transaction.date);
        String fromName = transaction.fromAccount;
        String toName = transaction.toAccount;
        String narrative = transaction.narrative;
        Double amount = transaction.amount;

        return new TransactionRecord(date, fromName, toName, narrative, amount);
    }

}
