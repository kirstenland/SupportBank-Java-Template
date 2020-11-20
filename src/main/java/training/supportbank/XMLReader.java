package training.supportbank;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XMLReader implements Reader{
    private XStream xstream;

    public XMLReader() {
        xstream = new XStream();
        xstream.alias("TransactionList", XMLTransactionList.class);
        xstream.addImplicitCollection(XMLTransactionList.class, "transactionList");
        xstream.alias("SupportTransaction", XMLRecord.class);
        xstream.aliasField("Description", XMLRecord.class, "narrative");
        xstream.aliasField("Parties", XMLRecord.class, "parties");
        xstream.aliasField("Value", XMLRecord.class, "cost");
        xstream.alias("Parties", XMLParties.class);
        xstream.aliasField("From", XMLParties.class, "fromName");
        xstream.aliasField("To", XMLParties.class, "toName");
        xstream.useAttributeFor(XMLRecord.class, "date");
        xstream.aliasField("Date", XMLRecord.class, "date");
    }

    @Override
    public List<DataRecord> getAllRecords(String fileName) throws IOException {
        String xmlString = Files.readString(Path.of(fileName));
        XMLTransactionList transactions= (XMLTransactionList) xstream.fromXML(xmlString);
        return transactions.transactionList;
    }
}
