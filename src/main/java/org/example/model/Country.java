package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {
    private String countryName;
    private List<City> cities  = new ArrayList<>();
    private int soldiersOnEdges ;


    public Country(String countryName) {
        this.countryName = countryName;
    }


    @Override
    public String toString() {
        StringBuilder report = new StringBuilder();
        report.append(countryName.charAt(0)).append(":<");

        int count = 1;

        for (City city : cities) {
            report.append(countryName.charAt(0)).append("c").append(count).append(":");
            report.append(city.toString()).append(",");
            count++;
        }
        report.setLength(report.length() - 1);
        report.append(">");
        if (soldiersOnEdges != 0 ) report.append("-").append(soldiersOnEdges);
        return report.toString();
    }

    public Country addCities (String... cityData) {
        for (int i=0; i < cityData.length; i +=2){
            int soldiers = Integer.parseInt(cityData[i]);
            int citizens = Integer.parseInt(cityData[i + 1]);
            cities.add(new City(soldiers ,citizens));

        }
        return this ;
    }

    public int getTotalSoldiers() {
        return cities.stream().mapToInt(City::getSoldiers).sum();
    }

}
