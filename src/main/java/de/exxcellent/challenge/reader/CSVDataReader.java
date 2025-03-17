package de.exxcellent.challenge.reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVDataReader implements DataReader {

    /**
     * Reads a specific CSV file and stores it in a list.
     *
     * @param filename - Name of the provided CSV file
     * @param requiredHeaders - List of required headers for validation
     * @return The individual rows as a list
     * @throws IllegalArgumentException
     */
    public List<String[]> readFile(String filename, List<String> requiredHeaders) {
        List<String[]> records = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {

            String[] headerLine = reader.readNext();
            checkMissingHeaders(requiredHeaders, headerLine);
            records.add(headerLine);

            String[] valuesLine;
            while ((valuesLine = reader.readNext()) != null) {
                records.add(valuesLine);
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found: " + filename);
        } catch (IOException | CsvValidationException e) {
            throw new IllegalArgumentException("Error on processing CSV-File.");
        }

        return records;
    }

    /**
     * Checks the header row against the required headers for processing.
     *
     * @param requiredHeaders - List of required headers
     * @param headerLine - List of headers from the CSV file
     * @throws IllegalArgumentException
     */
    private static void checkMissingHeaders(List<String> requiredHeaders, String[] headerLine) {
        if (headerLine == null) {
            throw new IllegalArgumentException("CSV-File is empty.");
        }

        ArrayList<String> headerIndexMap = new ArrayList<>();
        for (String header : headerLine) {
            headerIndexMap.add(header.toUpperCase());
        }

        List<String> missingHeaders = new ArrayList<>();
        for (String required : requiredHeaders) {
            if (!headerIndexMap.contains(required)) {
                missingHeaders.add(required);
            }
        }

        if (!missingHeaders.isEmpty()) {
            throw new IllegalArgumentException("Following headers are missing in the CSV-File: " + String.join(", ", missingHeaders));
        }
    }
}
