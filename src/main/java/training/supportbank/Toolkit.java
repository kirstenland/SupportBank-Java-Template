package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.json.JSONReader;
import training.supportbank.csv.CSVReader;
import training.supportbank.models.Reader;
import training.supportbank.xml.XMLReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Toolkit {
    public static final Logger LOGGER = LogManager.getLogger();

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

    public static Reader getReader(String fileName) {
        Reader reader;
        if (fileName.endsWith(".csv")) {
            return new CSVReader();
        } else if (fileName.endsWith(".json")) {
            return new JSONReader();
        } else if (fileName.endsWith(".xml")) {
            return new XMLReader();
        } else {
            LOGGER.error("Oh no, file type unrecognised.");
            return null;
        }
    }
}

