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

    @Test
    void testCalculateGoals() {
        List<String[]> footballData = Arrays.asList(
                new String[]{"TEAM", "GOALS", "GOALS ALLOWED"},
                new String[]{"Team A", "29", "24"},
                new String[]{"Team B", "23", "21"},
                new String[]{"Team C", "11", "11"}
        );
        String result = DataProcessor.calculateLowestGoalDifferencePerTeam(footballData);
        assertEquals("Team C", result, "Team with smallest goal difference should be Team C");
    }

}