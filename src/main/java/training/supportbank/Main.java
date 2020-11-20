package training.supportbank;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Bank bank = new Bank();
        boolean quit = false;
        while (!quit) {
            System.out.println("What would you like to do?");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("List All")) {
                bank.listAll();
            }
            else if (input.startsWith("List ")){
                bank.listAccount(input.substring(5));
            }
            else if (input.startsWith("Import File ")) {
                String fileName = input.substring(12);
                bank.readFile(fileName);
            }
            else if (input.equalsIgnoreCase("Quit")) {
                quit = true;
            }
            else {
                System.out.println(
                        "Type \"List All\", \"List USER\" or \"Import File FILENAME\".");
            }
        }
    }


}
