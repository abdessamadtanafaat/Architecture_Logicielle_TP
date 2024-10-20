package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class War {
    private List<KingDom> kingdoms;
    private List<String> map;

    public void prepareAttack() {
        for (KingDom kingdom : kingdoms) {
            // Calculate total soldiers to move to the nearest edges
            int totalSoldiersToMove = kingdom.currentPower() / 2;

            for (Country country : kingdom.getCountries()) {
                List<City> cities = country.getCities();
                for (City city : cities) {
                    // Calculate how many soldiers can be moved from this city
                    int soldiersToMoveFromCity = (int) (city.getSoldiers() * 0.5);

                    // Check to make sure we do not move more soldiers than available
                    if (soldiersToMoveFromCity > city.getSoldiers()) {
                        soldiersToMoveFromCity = city.getSoldiers();
                    }

                    // Deduct soldiers from city
                    city.setSoldiers(city.getSoldiers() - soldiersToMoveFromCity);
                    totalSoldiersToMove -= soldiersToMoveFromCity;

                    // If all soldiers to move have been assigned, break early
                    if (totalSoldiersToMove <= 0) {
                        break;
                    }
                }
            }

            // Ensure we distribute any remaining soldiers to the edges correctly
            kingdom.setSoldiersOnEdges(kingdom.getSoldiersOnEdges() + Math.max(0, totalSoldiersToMove));
        }
    }


}
