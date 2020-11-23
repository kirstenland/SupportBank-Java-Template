package training.supportbank.models;

import training.supportbank.models.DataRecord;

import java.io.IOException;
import java.util.List;

public interface Reader {
    List<DataRecord> getAllRecords(String fileName) throws IOException;
}
