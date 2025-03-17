package de.exxcellent.challenge;

import de.exxcellent.challenge.reader.CSVDataReader;

import java.util.Arrays;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     * @throws IllegalArgumentException
     */
    public static void main(String... args) {

        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong or no args used. Start app with  --weather / --football <path to CSV-File>");
        }

        String processorType = args[0];
        String fileLocation = args[1];


        CSVDataReader csvDataReader = new CSVDataReader();

        if (processorType.equals("--weather"))
        {
            // Reading and processing the provided weather.csv

            List<String> requiredHeaders = Arrays.asList(Constants.DAY, Constants.MXT, Constants.MNT);
            List<String[]> allWeatherData = csvDataReader.readFile(fileLocation, requiredHeaders);
            System.out.printf("Day with smallest temperature spread : %s%n", "2");
        }
        else if (processorType.equals("--football"))
        {
            // Reading and processing the provided football.csv

            List<String> requiredHeaders = Arrays.asList(Constants.TEAM,Constants.GOALS,Constants.GOALS_ALLOWED);
            List<String[]> allFootballData = csvDataReader.readFile(fileLocation, requiredHeaders);
            System.out.printf("Team with smallest goal spread       : %s%n", "Team C");
        }
        else
        {
            throw new IllegalArgumentException("Invalid type selection. Use --weather or --football <path to CSV>");
        }

    }
}
