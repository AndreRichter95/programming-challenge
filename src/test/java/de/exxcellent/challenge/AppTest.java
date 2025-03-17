package de.exxcellent.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example JUnit 5 test case.
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testInvalidArguments() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> App.main("--invalid", "data.csv"));
        assertTrue(exception.getMessage().contains("Invalid type selection"), "Should throw an error for invalid argument");
    }

    @Test
    void testMissingArguments() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> App.main("--weather"));
        assertTrue(exception.getMessage().contains("Wrong or no args used"), "Should throw an error for missing arguments");
    }

}