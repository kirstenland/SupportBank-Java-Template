package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String args[]) {
        Bank bank = new Bank();
        List<String[]> transactions = readFile("Transactions2014.csv");
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

    private static List<String[]> readFile(String fileName) {
        List<String[]> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                transactions.add(values);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

}
