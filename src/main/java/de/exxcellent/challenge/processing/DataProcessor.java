package de.exxcellent.challenge.processing;

import de.exxcellent.challenge.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProcessor {

    /**
     * Calculates the day with the smallest temperature spread from the provided data.
     * The data is filtered and compared using predefined headers.
     *
     * @param data - The processed data from the CSVReader
     * @return lowestSpreadDay - The day with the smallest temperature spread
     * @throws IllegalArgumentException
     */
    public static int calculateLowestTemperaturSpread(List<String[]> data) {
        int indexDay, indexMxt, indexMnt;
        double lowestSpread = Double.MAX_VALUE;
        int lowestSpreadDay = 0;
        Map<String, Integer> headerIndexMap = new HashMap<>();
        String[] header = data.get(0);

        for (int a = 0; a < header.length; a++) {
            headerIndexMap.put(header[a].toUpperCase(), a);
        }

        indexDay = headerIndexMap.get(Constants.DAY);
        indexMxt = headerIndexMap.get(Constants.MXT);
        indexMnt = headerIndexMap.get(Constants.MNT);

        for (int i = 1; i < data.size(); i++) {
            String[] dataLine = data.get(i);

            try {
                int day = Integer.parseInt(dataLine[indexDay]);
                double maxTemp = Double.parseDouble(dataLine[indexMxt]);
                double minTemp = Double.parseDouble(dataLine[indexMnt]);

                double tempSpread = maxTemp - minTemp;

                if (lowestSpread > tempSpread) {
                    lowestSpread = tempSpread;
                    lowestSpreadDay = day;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in line " + i);
            }
        }

        return lowestSpreadDay;
    }

    /**
     * Calculates the team with the smallest goal difference from the provided data.
     * The data is filtered and compared using predefined headers.
     *
     * @param data - The processed data from the CSVReader
     * @return lowestDifferenceTeam - The team with the smallest goal difference
     * @throws IllegalArgumentException
     */
    public static String calculateLowestGoalDifferencePerTeam(List<String[]> data) {
        int indexTeam, indexGoals, indexGoalsAllowed;
        int lowestDifference = Integer.MAX_VALUE;
        String lowestDifferenceTeam = "";

        Map<String, Integer> headerIndexMap = new HashMap<>();
        String[] header = data.get(0);

        for (int a = 0; a < header.length; a++) {
            headerIndexMap.put(header[a].toUpperCase(), a);
        }

        indexTeam = headerIndexMap.get(Constants.TEAM);
        indexGoals = headerIndexMap.get(Constants.GOALS);
        indexGoalsAllowed = headerIndexMap.get(Constants.GOALS_ALLOWED);

        for (int i = 1; i < data.size(); i++) {
            String[] dataLine = data.get(i);

            try {
                String team = dataLine[indexTeam];
                int goals = Integer.parseInt(dataLine[indexGoals]);
                int goalsAllowed = Integer.parseInt(dataLine[indexGoalsAllowed]);

                int goalDifference = Math.abs(goals - goalsAllowed);

                if (lowestDifference > goalDifference) {
                    lowestDifference = goalDifference;
                    lowestDifferenceTeam = team;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in line " + i);
            }
        }

        return lowestDifferenceTeam;
    }

}
