package training.supportbank;

import java.text.ParseException;

public interface DataRecord {
    Transaction toTransaction(Bank bank) throws ParseException;
    String display();
}
