public class BicycleStoreApplication {

    public static void main(String args[]) {
        OutputDriver outputDriver = new OutputDriver();
        InputDriver inputDriver = new InputDriver();
        Customer customer = new Customer("Sindhu");

        BicycleShop bicycleShop = new BicycleShop(outputDriver,inputDriver,customer);
        bicycleShop.displayMenu();
    }
}
