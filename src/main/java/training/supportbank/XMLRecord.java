package training.supportbank;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.text.ParseException;
import java.util.Date;

import org.apache.poi.ss.usermodel.DateUtil;

public class XMLRecord implements DataRecord {

    private String narrative;
    private double cost;
    private int date;
    private XMLParties parties;

    @Override
    public Transaction toTransaction(Bank bank) throws ParseException {
        Date javaDate = DateUtil.getJavaDate((double) date);
        Account fromAccount = bank.findOrAdd(parties.getFromName());
        Account toAccount = bank.findOrAdd(parties.getToName());
        return new Transaction(javaDate, fromAccount, toAccount, narrative, cost);
    }

    @Override
    public String display() {
        return "A string";
    }
}
