package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class CSVDataReaderTest {

    private CSVDataReader csvDataReader;
    private final List<String> requiredWeatherHeaders = Arrays.asList(Constants.DAY, Constants.MXT, Constants.MNT);
    private final List<String> requiredFootballHeaders = Arrays.asList(Constants.TEAM, Constants.GOALS, Constants.GOALS_ALLOWED);

    @BeforeEach
    void setUp() {
        csvDataReader = new CSVDataReader();
    }

    @Test
    void testReadMissingFile() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> csvDataReader.readFile("missing.csv", requiredWeatherHeaders));
        assertTrue(exception.getMessage().contains("File not found"), "Should throw an error when file is missing");
    }

    @Test
    void testReadWeatherFile() {
        List<String[]> data = csvDataReader.readFile("src/main/resources/de/exxcellent/challenge/weather.csv", requiredWeatherHeaders);
        assertNotNull(data, "Weather data should not be null");
        assertFalse(data.isEmpty(), "Weather data should not be empty");
    }

    @Test
    void testReadFootballFile() {
        List<String[]> data = csvDataReader.readFile("src/main/resources/de/exxcellent/challenge/football.csv", requiredFootballHeaders);
        assertNotNull(data, "Football data should not be null");
        assertFalse(data.isEmpty(), "Football data should not be empty");
    }

}