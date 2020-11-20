package training.supportbank;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JSONReader implements Reader {
    @Override
    public List<DataRecord> getAllRecords(String fileName) throws IOException {
        Gson gson = new Gson();
        DataRecord[] dataRecords = gson.fromJson(new FileReader(fileName), JSONRecord[].class);
        return Arrays.asList(dataRecords);
    }
}
