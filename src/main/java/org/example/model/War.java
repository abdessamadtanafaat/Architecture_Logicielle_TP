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
        // Logic to prepare the attack based on the map and redistribute soldiers
        for (KingDom kingdom : kingdoms) {
            // Calculate total soldiers to move to the nearest edges
            int totalSoldiersToMove = kingdom.currentPower() / 2;

            for (Country country : kingdom.getCountries()) {
                for (City city : country.getCities()) {
                    // Calculate soldiers to move from each city
                    int soldiersToMoveFromCity = (int) (city.getSoldiers() * 0.5);
                    city.setSoldiers(city.getSoldiers() - soldiersToMoveFromCity);
                    totalSoldiersToMove -= soldiersToMoveFromCity;
                }
            }

            // Update soldiers on edges after movement
            kingdom.setSoldiersOnEdges(kingdom.getSoldiersOnEdges() + totalSoldiersToMove);
        }
    }
}
