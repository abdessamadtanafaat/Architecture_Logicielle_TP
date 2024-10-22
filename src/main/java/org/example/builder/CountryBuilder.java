package org.example.builder;

import org.example.model.City;
import org.example.model.Country;
import java.util.List;
public class CountryBuilder {
    private String country;
    private List<City> cities;
     public Country build() {
         return new Country(country , cities, 0);
     }
}
