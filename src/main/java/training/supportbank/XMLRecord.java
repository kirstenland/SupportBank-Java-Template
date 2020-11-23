package training.supportbank;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.text.ParseException;
import java.util.Date;

import org.apache.poi.ss.usermodel.DateUtil;

@XStreamAlias("SupportTransaction")
public class XMLRecord implements DataRecord {

    @XStreamAlias("Description")
    private String narrative;

    @XStreamAlias("Value")
    private double cost;

    @XStreamAsAttribute
    @XStreamAlias("Date")
    private String date;

    @XStreamAlias("Parties")
    private XMLParties parties;

    @Override
    public Transaction toTransaction(Bank bank) throws ParseException {
        Date javaDate = DateUtil.getJavaDate(Double.parseDouble(date));
        Account fromAccount = bank.findOrAdd(parties.getFromName());
        Account toAccount = bank.findOrAdd(parties.getToName());
        return new Transaction(javaDate, fromAccount, toAccount, narrative, cost);
    }

    @Override
    public String display() {
        return XMLToolkit.xstream.toXML(this);
    }
}
