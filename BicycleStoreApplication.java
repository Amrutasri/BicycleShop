public class BicycleStoreApplication {

    public static void main(String args[]) {

        OutputDriver outputDriver = new OutputDriver();
        InputDriver inputDriver = new InputDriver();
        Owner owner = new Owner();

        String customerName = "Sindhu";
        Customer customer = new Customer(customerName);

        BicycleShop bicycleShop = new BicycleShop(outputDriver,inputDriver,customer);
        bicycleShop.displayMenu();

        owner.addCustomer(customer);
        owner.checkInVoiceOfCustomer(customerName);
    }
}
