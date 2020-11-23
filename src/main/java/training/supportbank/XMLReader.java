package training.supportbank;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XMLReader implements Reader{
    private XStream xstream;

    public XMLReader() {
        xstream = new XStream();
        xstream.processAnnotations(new Class[] {XMLParties.class, XMLTransactionList.class, XMLRecord.class});
        xstream.addPermission(NoTypePermission.NONE);
        xstream.allowTypes(new Class[]{XMLTransactionList.class, XMLRecord.class});
    }

    @Override
    public List<DataRecord> getAllRecords(String fileName) throws IOException {
        String xmlString = Files.readString(Path.of(fileName));
        XMLTransactionList transactions= (XMLTransactionList) xstream.fromXML(xmlString);
        return transactions.transactionList;
    }
}
