package training.supportbank.models;

import training.supportbank.services.Bank;

import java.text.ParseException;

public interface DataRecord {
    Transaction toTransaction(Bank bank) throws ParseException;
    String display();
}
