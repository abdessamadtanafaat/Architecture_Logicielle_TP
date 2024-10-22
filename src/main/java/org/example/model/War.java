package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
public class War {
    private List<KingDom> kingdoms;
    private Map map;

    public void prepareAttack() {
        KingDom strongest = kingdoms.stream()
                .max(Comparator.comparing(KingDom::currentPower))
                .orElse(null);
        if (strongest != null) {
            Country nearest = map.findNearestToKingdom(strongest);
            strongest.moveArmyToNearestCountry(nearest);
        }
    }


}
