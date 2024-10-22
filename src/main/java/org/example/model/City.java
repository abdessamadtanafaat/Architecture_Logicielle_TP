package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {

    private int soldiers;
    private int citizens;

    @Override
    public String toString() {
        return soldiers+"-"+citizens ;
    }

}
