package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        List<List<String>> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Transactions2014.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(", ");
                transactions.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}
