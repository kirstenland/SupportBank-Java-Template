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

    public void addTransaction(String[] transaction) throws ParseException {
        String date = transaction[0];

        String fromName = transaction[1];
        Account fromAccount = findOrAdd(fromName);

        String toName = transaction[2];
        Account toAccount = findOrAdd(toName);

        String narrative = transaction[3];
        String amount = transaction[4];

        Transaction newTransaction = new Transaction(date, fromAccount, toAccount, narrative, amount);
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

    public void addAll(List<String[]> transactions) {
        for (int i = 0; i < transactions.size(); i++) {
            try {
                addTransaction(transactions.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void runAllTransactions() {
        for (Transaction transaction : transactions) {
            transaction.process();
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
