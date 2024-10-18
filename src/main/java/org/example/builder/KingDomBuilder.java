package org.example.builder;

import org.example.model.City;
import org.example.model.Country;
import org.example.model.King;
import org.example.model.KingDom;

import java.util.ArrayList;
import java.util.List;

public class KingDomBuilder {
    private King king;
    private List<Country> countries = new ArrayList<>();
    private int soldiersOnEdges = 0;

    public KingDomBuilder addKing(String name) {
        this.king = new King(name);
        return this;
    }

    public KingDomBuilder addCountry(String name, String... details) {
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < details.length; i += 2) {
            int soldiers = Integer.parseInt(details[i]);
            int citizens = Integer.parseInt(details[i + 1]);
            cities.add(new City(soldiers, citizens));
        }
        this.countries.add(new Country(name, cities));
        return this;
    }


    public KingDomBuilder addSoldiersOnEdges(String soldiers) {
        this.soldiersOnEdges = Integer.parseInt(soldiers);
        return this;
    }

    public KingDom build() {
        KingDom kingDom = new KingDom(king, countries, soldiersOnEdges);
        kingDom.setSoldiersOnEdges(soldiersOnEdges);
        return kingDom;
    }
}
