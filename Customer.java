package model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Bicycle> hiredButNotReturned = new ArrayList<>();
    private List<Bicycle> hiredAndReturned = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    public void addHiredBicycles(Bicycle bicycle) {
        hiredButNotReturned.add(bicycle);
    }

    public void removeReturnedBicycles(Bicycle bicycle) {
        hiredButNotReturned.remove(bicycle);
        hiredAndReturned.add(bicycle);
    }
}
