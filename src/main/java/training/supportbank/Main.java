package training.supportbank;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws FileNotFoundException, ParseException {
        Bank bank = new Bank();
        while (true) {
            System.out.println("What would you like to do?");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equals("List All")) {
                bank.listAll();
            }
            else if (input.startsWith("List ")){
                bank.listAccount(input.substring(5));
            }
            else if (input.startsWith("Import File ")) {
                String fileName = input.substring(12);
                List<TransactionRecord> transactions = Toolkit.readFile(fileName);
                bank.addAll(transactions);
            }
            else {
                System.out.println(
                        "Type \"List All\", \"List USER\" or \"Import File FILENAME\".");
            }
        }
    }


}
