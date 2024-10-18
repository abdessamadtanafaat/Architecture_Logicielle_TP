package org.example.Builder;

import org.example.Model.KingDom;
import org.example.Model.War;
import java.util.ArrayList;
import java.util.List;

public class WarBuilder {
    private List<KingDom> kingdoms = new ArrayList<>();
    private List<String> map = new ArrayList<>();
    public WarBuilder addKingDom(KingDom kingDom) {
        this.kingdoms.add(kingDom);
        return this;
    }

    public WarBuilder addMap(String mapEntry) {
        this.map.add(mapEntry);
        return this;
    }

    public War build() {
        return new War(kingdoms, map);
    }
}
