package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class KingDom {
    private String kingName;
    private List<Country> countries;


    public int currentPower() {
        return countries
                .stream()
                .mapToInt(Country::getTotalSoldiers)
                .sum();

    }

    public String report() {
        StringBuilder report = new StringBuilder();

        report.append(kingName).append(":|");
        for (Country country :countries) {
            report.append(country.toString()).append(", ");
        }
        report.setLength(report.length() - 2 );
        report.append("|");

        return report.toString();

    }

    public void moveArmyToNearestCountry(Country nearest) {
        if(nearest==null) return;
        int soldiersToMove = 0;

        for (Country country : countries) {
            if(!country.getCountryName().equals(nearest.getCountryName())){
                soldiersToMove += country.getCities().stream()
                        .mapToInt(
                                city->{
                                    city.setSoldiers(city.getSoldiers()/2);
                                    return city.getSoldiers();
                                }
                        ).sum();
            }
        }
        nearest.setSoldiersOnEdges(
                nearest.getSoldiersOnEdges()+soldiersToMove
        );

    }
}
