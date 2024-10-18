package org.example.Builder;

import org.example.Model.City;
import org.example.Model.Country;
import org.example.Model.King;
import org.example.Model.KingDom;

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

    public KingDomBuilder addCountry(String name, String... cityData) {
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < cityData.length; i += 2) {
            int soldiers = Integer.parseInt(cityData[i]);
            int citizens = Integer.parseInt(cityData[i + 1]);
            cities.add(new City(soldiers, citizens));
        }
        countries.add(new Country(name, cities));
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
