import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BicycleShop {

    private List<Bicycle> totalBicycles;
    private List<Bicycle> availableBicycles;
    private List<Bicycle> hiredBicycles = new ArrayList<>();
    private LocalDateTime bicycleHiringTime;
    private LocalDateTime bicycleReturnTime;
    private List<Customer> customerList = new ArrayList<>();
    private int countOfCustomers = 0;

    private Owner owner;
    private OutputDriver outputDriver;
    private InputDriver inputDriver;
    private DisplayMessage displayMessage = new DisplayMessage();

    BicycleShop(OutputDriver outputDriver, InputDriver inputDriver, Owner owner) {
        this.outputDriver = outputDriver;
        this.inputDriver = inputDriver;
        this.owner = owner;
        totalBicycles = new BicycleStand().getBicycles();
        availableBicycles = new BicycleStand().getBicycles();
    }

    void addCustomer(Customer customer) {
        countOfCustomers++;
        customer.setId("Customer"+countOfCustomers);
        customerList.add(customer);
        outputDriver.print(displayMessage.customerIdGeneration +""+customer.getId());
    }

    public void displayMenu() {
        int user = 0;
        do {
            outputDriver.print(displayMessage.customerOrOwnerLogIn);
            user = inputDriver.inputAsInt();
            switch (user) {
                case 1 :    int customerOption = 0;
                            outputDriver.print(displayMessage.customerLogIn);
                            String customerId = inputDriver.inputAsString();
                            for (Customer customer : customerList) {
                                if (customer.getId().equals(customerId)) {
                                    do {
                                        outputDriver.print(displayMessage.customerMenu);
                                        customerOption = inputDriver.inputAsInt();
                                        switch (customerOption) {

                                            case 1: for (Bicycle bicycle : totalBicycles) {
                                                    outputDriver.print(bicycle.getName());
                                                    outputDriver.print(bicycle.getRentPerSecond());
                                                    outputDriver.print(bicycle.getStatus());
                                                    System.out.println();
                                                    }
                                                    break;

                                            case 2: boolean permissionGranted = owner.grantPermission(customer.getId());
                                                    if (!permissionGranted) {
                                                        outputDriver.print(displayMessage.permissionDenied);
                                                    } else {
                                                        outputDriver.print(displayMessage.bicycleToHire);
                                                        String hireBicycleName = inputDriver.inputAsString();
                                                        boolean hireSuccess = hireBicycle(hireBicycleName, customer);
                                                        if (!hireSuccess) {
                                                            outputDriver.print(displayMessage.hireBicycleUnSuccess);
                                                        } else {
                                                            outputDriver.print(displayMessage.hireBicycleSuccess);
                                                        }
                                                    }
                                                    break;
                                            case 3: outputDriver.print(displayMessage.bicycleToReturn);
                                                    String returnBicycleName = inputDriver.inputAsString();
                                                    boolean returnSuccess = returnBicycle(returnBicycleName, customer);
                                                    if (!returnSuccess) {
                                                        outputDriver.print(displayMessage.returnBicycleUnSuccess);
                                                    } else {
                                                        outputDriver.print(displayMessage.returnBicycleSuccess);
                                                        outputDriver.print(displayMessage.inVoice);
                                                        int choice = inputDriver.inputAsInt();
                                                        if (choice == 1) {
                                                            customer.seeInVoice();
                                                        }
                                                    }
                                                    break;
                                        }
                                    } while (customerOption != 4);
                                }
                            }
                            break;

                case 2 :    int ownerOption = 0;
                            do {
                                outputDriver.print(displayMessage.ownerMenu);
                                ownerOption = inputDriver.inputAsInt();
                                switch (ownerOption) {

                                    case 1: outputDriver.print(displayMessage.bicycleName);
                                            String bicycleName = inputDriver.inputAsString();
                                            for (Customer customer : customerList) {
                                                List<Bicycle> bicycles = customer.getTotalBicyclesTaken();
                                                for (Bicycle bicycle : bicycles) {
                                                    if (bicycle.getName().equals(bicycleName)) {
                                                        customer.seeInVoice();
                                                    }
                                                }
                                            }
                                            break;

                                    case 2: outputDriver.print(displayMessage.customerId);
                                            String customerID = inputDriver.inputAsString();
                                            for (Customer customer : customerList) {
                                                if (customer.getId().equals(customerID)) {
                                                    customer.seeInVoice();
                                                }
                                            }
                                            break;
                                }
                            } while (ownerOption != 3);
                            break;
            }
        }while(user!=3);
    }

    boolean hireBicycle(String hireBicycleName, Customer customer) {
        for(Bicycle bicycle : availableBicycles) {
            if(bicycle.getName().equals(hireBicycleName)) {
                bicycle.setStatus(false);
                hiredBicycles.add(bicycle);
                availableBicycles.remove(bicycle);
                bicycleHiringTime = LocalDateTime.now();
                customer.addHiredBicycles(bicycle,bicycleHiringTime);
                return true;
            }
        }
        return false;
    }

    boolean returnBicycle(String returnBicycleName, Customer customer) {
        for(Bicycle bicycle : hiredBicycles) {
            if(bicycle.getName().equals(returnBicycleName)) {
                bicycle.setStatus(true);
                availableBicycles.add(bicycle);
                hiredBicycles.remove(bicycle);
                bicycleReturnTime = LocalDateTime.now();
                customer.removeReturnedBicycles(bicycle,bicycleReturnTime);
                return true;
            }
        }
        return false;
    }
}
