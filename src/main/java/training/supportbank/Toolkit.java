package training.supportbank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.crypto.Data;
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
        return "£" + convertPenceToPounds(pence);
    }

    public static void displayFormatter(String date, String from, String to, String narrative, String amount) {
        System.out.printf("%-15s %-15s %-15s %-45s %15s %n", date, from, to, narrative, amount);
    }

    public static List<TransactionRecord> readFile(String fileName) throws FileNotFoundException, ParseException {
        Reader reader;
        if (fileName.endsWith(".csv")) {
            reader = new CSVReader();
        } else if (fileName.endsWith(".json")) {
            reader = new JSONReader();
        } else {
            LOGGER.error("Oh no, file type unrecognised.");
            return Collections.emptyList();
        }

        List<DataRecord> dataRecords;
        try {
            dataRecords = reader.getAllRecords(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("I/O Exception");
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }

        int errorCount = 0;
        List<TransactionRecord> transactions = new ArrayList<>();
        for (DataRecord record : dataRecords) {
            try {
                transactions.add(record.toTransactionRecord());
            } catch (Exception e) {
                LOGGER.error("Oh no, an error has occurred on the following item");
                LOGGER.error(record.display());
                LOGGER.error(e.getMessage());
                errorCount += 1;
            }
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
