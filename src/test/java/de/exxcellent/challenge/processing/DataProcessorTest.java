package de.exxcellent.challenge.processing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class DataProcessorTest {

    @Test
    void testCalculateWeather() {
        List<String[]> weatherData = Arrays.asList(
                new String[]{"DAY", "MXT", "MNT"},
                new String[]{"1", "30", "15"},
                new String[]{"2", "25", "14"},
                new String[]{"3", "28", "17"}
        );
        int result = DataProcessor.calculateLowestTemperaturSpread(weatherData);
        assertEquals(2, result, "Day with smallest temperature spread should be 2");
    }

}