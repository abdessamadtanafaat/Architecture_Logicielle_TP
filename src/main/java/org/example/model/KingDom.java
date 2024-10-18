package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class KingDom {
    private King king;
    private List<Country> countries;
    private int soldiersOnEdges = 0;

    public int currentPower() {
        int totalPower = 0;
        for (Country country : countries) {
            totalPower += country.getTotalSoldiers();
        }
        return totalPower ;
    }


    public String report() {
        StringBuilder report = new StringBuilder();

        // Append the full name of the king
        report.append(king.getName()).append(":|");

        // Iterate over countries to build their report
        for (Country country : countries) {
            report.append(country.getName().charAt(0)).append(":<") // Use the first character of the country name
                    .append(country) // Append the country details using the overridden toString
                    .append(">"); // Closing angle bracket for countries

            // Only append soldiers on edges if there are any
            if (soldiersOnEdges > 0) {
                report.append("-").append(soldiersOnEdges); // Append soldiers on edges if greater than zero
            }

            report.append(", "); // Add a comma to separate countries
        }

        // Remove the trailing comma and space if countries exist
        if (!countries.isEmpty()) {
            report.setLength(report.length() - 2);
        }

        report.append("|"); // Final closing pipe
        return report.toString();
    }

    @Override
    public String toString() {
        return report(); // or return the same logic as in report if you prefer
    }
}
