import java.util.ArrayList;
import java.util.List;

public class Owner {

    private List<Customer> customerList = new ArrayList<>();

    void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    void checkInVoiceOfCustomer(String customerName) {
        for(Customer customer : customerList) {
            if(customer.getName().equals(customerName)) {
                customer.seeInVoice();
            }
        }
    }
}
