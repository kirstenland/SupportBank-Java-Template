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
        return "£" + convertPenceToPounds(pence);
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


