package training.supportbank.csv;

import training.supportbank.models.Account;
import training.supportbank.services.Bank;
import training.supportbank.models.DataRecord;
import training.supportbank.models.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CSVRecord implements DataRecord {

    private String line;

    public CSVRecord(String line) {
        this.line = line;
    }

    @Override
    public Transaction toTransaction(Bank bank) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String[] transaction = line.split(",");
        Date date = formatter.parse(transaction[0]);
        String fromName = transaction[1];
        Account fromAccount = bank.findOrAdd(fromName);
        String toName = transaction[2];
        Account toAccount = bank.findOrAdd(toName);
        String narrative = transaction[3];
        Double amount = Double.parseDouble(transaction[4]);

        return new Transaction(date, fromAccount, toAccount, narrative, amount);
    }

    @Override
    public String display() {
        return line;
    }
}
