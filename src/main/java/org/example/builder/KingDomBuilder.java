package org.example.builder;

import lombok.NoArgsConstructor;
import org.example.model.City;
import org.example.model.Country;
import org.example.model.KingDom;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class KingDomBuilder {
 private String kingName;
 private List<Country> countries  = new ArrayList<>();

 public KingDomBuilder addKing(String kingName) {
     this.kingName = kingName;
     return this;
 }
 public KingDomBuilder addCountry (String country, String...cityData) {
     countries.add(new Country(country).addCities(cityData));
     return this;

 }
 public KingDomBuilder addSoldiersOnEdges(String soldiersOnEdges){
     for(Country  country : countries) {
         country.setSoldiersOnEdges(Integer.parseInt(soldiersOnEdges));
     }
     return this;
 }

 public KingDom build() { return new KingDom(kingName, countries);}



}
