import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maximumPopulation(int[][] logs) {
        HashMap<Integer, Integer> populationLogs = new HashMap<>();
        for (int[] log : logs) {
            for (int year = log[0]; year < log[1]; year++) {
                populationLogs.put(year, populationLogs.getOrDefault(year, 0) + 1);
            }
        }
        int maxPopulation = 0;
        int earliestYear = Integer.MAX_VALUE;

        // Find year with max and in case of tie, use earliest
        for (Map.Entry<Integer, Integer> entry : populationLogs.entrySet()) {
            int year = entry.getKey();
            int population = entry.getValue();

            if(population > maxPopulation) {
                maxPopulation = population;
                earliestYear = year;
            } else if (population == maxPopulation && year < earliestYear) {
                earliestYear = year;
            }
        }

        return earliestYear;
    }
}