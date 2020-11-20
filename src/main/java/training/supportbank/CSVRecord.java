package training.supportbank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CSVRecord implements DataRecord{

    private String line;

    public CSVRecord(String line) {
        this.line = line;
    }

    @Override
    public TransactionRecord toTransactionRecord() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String[] transaction = line.split(",");
        Date date = formatter.parse(transaction[0]);
        String fromName = transaction[1];
        String toName = transaction[2];
        String narrative = transaction[3];
        Double amount = Double.parseDouble(transaction[4]);

        return new TransactionRecord(date, fromName, toName, narrative, amount);
    }

    @Override
    public String display() {
        return line;
    }
}
