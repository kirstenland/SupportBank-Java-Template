package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Bank bank = new Bank();
        List<String[]> transactions = readFile("Transactions2014.csv");
        bank.addAll(transactions);
        bank.runAllTransactions();
        System.out.println("Done!");
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
