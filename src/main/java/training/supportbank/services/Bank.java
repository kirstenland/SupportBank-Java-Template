package training.supportbank.services;

import training.supportbank.Toolkit;
import training.supportbank.models.Account;
import training.supportbank.models.DataRecord;
import training.supportbank.models.Reader;
import training.supportbank.models.Transaction;

import java.io.IOException;
import java.util.*;

public class Bank {
    Map<String, Account> users;
    List<Transaction> transactions;

    public Bank() {
        users = new HashMap();
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transaction.process();
        transactions.add(transaction);
    }

    public Account findOrAdd(String name) {
        if (users.containsKey(name)) {
            return users.get(name);
        } else {
            Account newAccount = new Account(name);
            users.put(name, newAccount);
            return newAccount;
        }
    }

    public void listAll() {
        for (Account user : users.values()) {
            user.display();
        }
    }

    public void listAccount(String name) {
        Account account = users.get(name);
        Toolkit.displayFormatter("Date", "From", "To", "Narrative", "Amount");
        for (Transaction transaction: transactions) {
            if (transaction.getFrom().equals(account) || transaction.getTo().equals(account)) {
                transaction.display();
            }
        }
    }

    public void readFile(String fileName) {
        Reader reader = Toolkit.getReader(fileName);

        if (reader == null) {
            return;
        }

        List<DataRecord> dataRecords;
        try {
            dataRecords = reader.getAllRecords(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toolkit.LOGGER.error("I/O Exception");
            Toolkit.LOGGER.error(e.getMessage());
            return;
        }

        int errorCount = 0;
        for (DataRecord record : dataRecords) {
            try {
                Transaction transaction = record.toTransaction(this);
                addTransaction(transaction);
            } catch (Exception e) {
                Toolkit.LOGGER.error("Oh no, an error has occurred on the following item");
                Toolkit.LOGGER.error(record.display());
                Toolkit.LOGGER.error(e.getMessage());
                errorCount += 1;
            }
        }

        if (errorCount > 0) {
            System.out.println(errorCount + " errors were found while importing data");
            System.out.println("Please check the log for more details");
        } else {
            System.out.println("All transactions successfully loaded");
        }
    }
}
