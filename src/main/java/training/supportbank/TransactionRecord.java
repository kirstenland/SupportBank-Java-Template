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
}
