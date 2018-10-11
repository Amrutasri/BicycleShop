package model;

import java.util.ArrayList;
import java.util.List;

public class Owner {

    List<Customer> customerList = new ArrayList<>();

    Owner(Customer customer) {
        customerList.add(customer);
    }
}
