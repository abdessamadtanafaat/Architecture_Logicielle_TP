package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Country {
    private String name;
    private List<City> cities;

    public int getTotalSoldiers() {
        return cities.stream().mapToInt(City::getSoldiers).sum();
    }

    @Override
    public String toString() {
        StringBuilder report = new StringBuilder();
        char countryPrefix = name.charAt(0); // Get the first character of the country name (e.g., 'F' for France)

        for (int i = 0; i < cities.size(); i++) {
            report.append(countryPrefix) // Append country prefix (e.g., 'F')
                    .append("c").append(i + 1) // Append city index (e.g., 'c1')
                    .append(":").append(cities.get(i).getSoldiers()) // Append soldiers
                    .append("-").append(cities.get(i).getCitizens()); // Append citizens

            if (i < cities.size() - 1) {
                report.append(","); // No space after comma for formatting
            }
        }
        return report.toString();
    }
}
