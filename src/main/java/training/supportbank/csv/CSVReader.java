package training.supportbank.csv;

import training.supportbank.models.DataRecord;
import training.supportbank.models.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements Reader {
    @Override
    public List<DataRecord> getAllRecords(String fileName) throws IOException {
        List<DataRecord> transactions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            transactions.add(new CSVRecord(line));
        }
        return transactions;
    }
}
