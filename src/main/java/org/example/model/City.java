package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    private int soldiers;
    private int citizens;

    public int getSoldiers() {
        return soldiers; // Ensure this returns the right value
    }

}
