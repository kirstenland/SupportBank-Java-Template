package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String args[]) {
        Bank bank = new Bank();
        List<TransactionRecord> transactions = readFile("Transactions2015.csv");
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

    private static List<TransactionRecord> readFile(String fileName) {
        List<TransactionRecord> transactions = new ArrayList<>();
        int errorCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    transactions.add(TransactionRecord.fromCSVLine(line));
                } catch (Exception e) {
                    LOGGER.error("Oh no, an error has occurred on the following line");
                    LOGGER.error(line);
                    LOGGER.error(e.getMessage());
                    errorCount += 1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("File Not Found Exception");
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("File Not Found Exception");
            LOGGER.error(e.getMessage());
        }

        if (errorCount > 0) {
            System.out.println(errorCount + " errors were found while importing data");
            System.out.println("Please check the log for more details");
        } else {
            System.out.println("All transactions successfully loaded");
        }

        return transactions;
    }

}
