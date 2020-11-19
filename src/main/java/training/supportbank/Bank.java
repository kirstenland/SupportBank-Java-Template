package training.supportbank;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    Map<String, Account> users;
    List<Transaction> transactions;

    public Bank() {
        users = new HashMap();
        transactions = new ArrayList<>();
    }

    public void addTransaction(TransactionRecord transaction) {
        String fromName = transaction.from;
        Account fromAccount = findOrAdd(fromName);
        String toName = transaction.to;
        Account toAccount = findOrAdd(toName);

        Transaction newTransaction =
                new Transaction(transaction.date, fromAccount, toAccount, transaction.narrative, transaction.amount);
        newTransaction.process();
        transactions.add(newTransaction);
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

    public void addAll(List<TransactionRecord> transactions) {
        for (TransactionRecord transaction : transactions) {
            addTransaction(transaction);
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
            if (transaction.from.equals(account) || transaction.to.equals(account)) {
                transaction.display();
            }
        }
    }
}
