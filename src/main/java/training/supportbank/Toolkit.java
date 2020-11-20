package training.supportbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Toolkit {
    private static final Logger LOGGER = LogManager.getLogger();

    public static double convertPenceToPounds(int pence) {
        return (double)pence/100;
    }

    public static int convertPoundsToPence(double pounds) {
        return (int)(pounds*100);
    }

    public static String displayDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static String displayPenceAsPounds(int pence) {
        return String.format("£%s", String.format("%.2f", convertPenceToPounds(pence)));
    }

    public static void displayFormatter(String date, String from, String to, String narrative, String amount) {
        System.out.printf("%-15s %-15s %-15s %-45s %15s %n", date, from, to, narrative, amount);
    }

    public static List<TransactionRecord> readFile(String fileName) throws FileNotFoundException, ParseException {
        if (fileName.endsWith(".csv")) {
            return readFileCSV(fileName);
        } else if (fileName.endsWith(".json")) {
            return readFileJSON(fileName);
        } else {
            LOGGER.error("Oh no, file type unrecognised.");
            return Collections.emptyList();
        }
    }

    public static List<TransactionRecord> readFileJSON(String fileName) throws FileNotFoundException, ParseException {
        List<TransactionRecord> transactions = new ArrayList<>();
        Gson gson = new Gson();
        TransactionDummy[] transactionDummies = gson.fromJson(new FileReader(fileName), TransactionDummy[].class);
        for (TransactionDummy transaction: transactionDummies) {
            transactions.add(TransactionRecord.fromJSONLine(transaction));
        }
        return transactions;
    }

    public static List<TransactionRecord> readFileCSV(String fileName) {
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
            LOGGER.error("I/O Exception");
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
