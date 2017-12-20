package q183272;

package com.energy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnergyCalculator {

    public static void main(String[] args) throws ParseException {

        List<DailyUsage> dailyUsageList = populateList();
        Map<String, Double> monthlyUsage = calculateMonthlyUsage(dailyUsageList);

        System.out.println("Month-Year \t | \tUsage "
                + "\n--------------------------------");
        for (Map.Entry<String, Double> use : monthlyUsage.entrySet()) {
            System.out.println(use.getKey() + "\t | \t" + use.getValue());
        }
    }

    protected static Map<String, Double> calculateMonthlyUsage(
            List<DailyUsage> usageList) {

        // Map to store month and usage
        Map<String, Double> usageMonthMap = new LinkedHashMap<String, Double>();

        for (DailyUsage usage : usageList) {

            // SB for making unique key of month and year
            StringBuilder combinedKey = new StringBuilder();
            combinedKey.append(usage.getMonthName() + "-" + usage.getYear());

            if (!usageMonthMap.containsKey(combinedKey.toString())) {
                usageMonthMap.put(combinedKey.toString(), usage.getUsage());
            } 
            else {
                double currentUsage = usageMonthMap.get(combinedKey.toString());
                currentUsage += usage.getUsage();
                usageMonthMap.put(combinedKey.toString(), currentUsage);
            }
        }

        return usageMonthMap;
    }

    private static List<DailyUsage> populateList() throws ParseException {

        // Test data
        List<DailyUsage> dailyUsageList = new ArrayList<DailyUsage>();
        dailyUsageList.add(new DailyUsage("2017-01-20", 1200.00));
        dailyUsageList.add(new DailyUsage("2017-01-24", 1200.00));
        dailyUsageList.add(new DailyUsage("2017-02-13", 1200.00));
        dailyUsageList.add(new DailyUsage("2017-02-19", 50.00));
        dailyUsageList.add(new DailyUsage("2017-04-10", 100.45));
        dailyUsageList.add(new DailyUsage("2017-05-19", 225.00));
        dailyUsageList.add(new DailyUsage("2017-12-20", 400.50));
        dailyUsageList.add(new DailyUsage("2017-12-29", 60.40));
        dailyUsageList.add(new DailyUsage("2018-01-05", 700));
        dailyUsageList.add(new DailyUsage("2018-01-07", 150));
        dailyUsageList.add(new DailyUsage("2018-01-15", 700));

        return dailyUsageList;
    }
}
