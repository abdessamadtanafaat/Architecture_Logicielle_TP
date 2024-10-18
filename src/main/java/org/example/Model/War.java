package org.example.Model;

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
            for (Country country : kingdom.getCountries()) {
                for (City city : country.getCities()) {
                    city.setSoldiers((int) (city.getSoldiers() * 0.5)); // Moving 50% soldiers for attack
                }
            }
            kingdom.setSoldiersOnEdges(kingdom.getSoldiersOnEdges() + 50); // Just an example
        }
    }
}
