package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BicycleStand {

    public List<Bicycle> getBicycles() {
        return Stream.iterate(0,i -> i+1)
                .limit(10)
                .map(integer -> new Bicycle("Bicycle"+integer, integer+1))
                .collect(Collectors.toList());
    }
}
