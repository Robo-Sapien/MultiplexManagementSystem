package org.machinecoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Multiplex {

    private String buildingName;
    private List<Screen> screens;

    public void addScreenToMultiplex(Screen screen) {
        screens.add(screen);
    }
}
