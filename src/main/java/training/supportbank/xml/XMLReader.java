package training.supportbank.xml;

import training.supportbank.models.DataRecord;
import training.supportbank.models.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XMLReader implements Reader {

    @Override
    public List<DataRecord> getAllRecords(String fileName) throws IOException {
        String xmlString = Files.readString(Path.of(fileName));
        XMLTransactionList transactions= (XMLTransactionList) XMLToolkit.xstream.fromXML(xmlString);
        return transactions.transactionList;
    }
}
