package training.supportbank;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JSONRecord implements DataRecord{
    protected String date;
    protected String fromAccount;
    protected String toAccount;
    protected String narrative;
    protected Double amount;

    @Override
    public Transaction toTransaction(Bank bank) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date realDate = formatter.parse(date);
        return new Transaction(realDate, bank.findOrAdd(fromAccount), bank.findOrAdd(toAccount), narrative, amount);
    }

    @Override
    public String display() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
