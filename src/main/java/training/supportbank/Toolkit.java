package training.supportbank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Toolkit {
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
}
