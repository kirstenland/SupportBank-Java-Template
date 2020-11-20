package training.supportbank;

import java.text.ParseException;

public interface DataRecord {
    TransactionRecord toTransactionRecord() throws ParseException;
    String display();
}
