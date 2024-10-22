package org.example.builder;

import org.example.model.KingDom;
import org.example.model.Map;
import org.example.model.War;
import java.util.ArrayList;
import java.util.List;

public class WarBuilder {
    private List<KingDom> kingdoms = new ArrayList<>();
    private Map map;

    public WarBuilder addKingDom(KingDom kingDom) {
        this.kingdoms.add(kingDom);
        return this;
    }

    public WarBuilder addMap(String mapData) {
        this.map = new Map();
        String[] routes = mapData.split(",");
        for (String route : routes){
            map.addRoute(route);
        }
        return this;
    }

    public War build() {
        return new War(kingdoms, map);
    }
}
