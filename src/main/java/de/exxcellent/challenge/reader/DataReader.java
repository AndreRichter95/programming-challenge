package de.exxcellent.challenge.reader;

import java.io.FileNotFoundException;
import java.util.List;

public interface DataReader {

    // Read a CSV-File
    List<String[]> readFile(String filename,  List<String> requiredHeaders) throws FileNotFoundException;

}
