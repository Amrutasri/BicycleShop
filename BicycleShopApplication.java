public class BicycleShopApplication {

    public static void main(String args[]) {

        OutputDriver outputDriver = new OutputDriver();
        InputDriver inputDriver = new InputDriver();
        Owner owner = new Owner();

        BicycleShop bicycleShop = new BicycleShop(outputDriver,inputDriver,owner);

        String customerName1 = "Sindhu";
        Customer customer1 = new Customer(customerName1);
        bicycleShop.addCustomer(customer1);

        String customerName2 = "Bhavana";
        Customer customer2 = new Customer(customerName2);
        bicycleShop.addCustomer(customer2);

        String customerName3 = "Revati";
        Customer customer3 = new Customer(customerName3);
        bicycleShop.addCustomer(customer3);

        bicycleShop.displayMenu();

    }
}
