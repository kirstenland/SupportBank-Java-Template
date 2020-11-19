package training.supportbank;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws FileNotFoundException, ParseException {
        Bank bank = new Bank();
        List<TransactionRecord> transactions = Toolkit.readFile("Transactions2013.json");
        bank.addAll(transactions);
        bank.runAllTransactions();

        while (true) {
            System.out.println("What would you like to see?");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equals("List All")) {
                bank.listAll();
            }
            else if (input.startsWith("List ")){
                bank.listAccount(input.substring(5));
            }
            else {
                System.out.println("Type \"List All\" for all accounts or \"List USER\" for the transactions of an account.");
            }
        }
    }


}
