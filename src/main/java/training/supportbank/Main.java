package training.supportbank;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws FileNotFoundException, ParseException {
        Bank bank = new Bank();
        Boolean running = true;
        while (running) {
            System.out.println("What would you like to do?");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("LIST ALL")) {
                bank.listAll();
            }
            else if (input.toUpperCase().startsWith("LIST ")){
                bank.listAccount(input.substring(5));
            }
            else if (input.toUpperCase().startsWith("IMPORT FILE ")) {
                String fileName = input.substring(12);
                List<TransactionRecord> transactions = Toolkit.readFile(fileName);
                bank.addAll(transactions);
            }
            else if (input.equalsIgnoreCase("EXIT")) {
                running = false;
            }
            else {
                System.out.println(
                        "Type \"List All\", \"List USER\" or \"Import File FILENAME\" or \"exit\".");
            }
        }
    }


}
