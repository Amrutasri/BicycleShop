import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BicycleShop {

    private List<Bicycle> totalBicycles;
    private List<Bicycle> availableBicycles;
    private List<Bicycle> hiredBicycles = new ArrayList<>();
    private LocalDateTime bicycleHiringTime;
    private LocalDateTime bicycleReturnTime;

    private OutputDriver outputDriver;
    private InputDriver inputDriver;
    private Customer customer;
    private DisplayMessage displayMessage = new DisplayMessage();

    BicycleShop(OutputDriver outputDriver, InputDriver inputDriver, Customer customer) {
        this.outputDriver = outputDriver;
        this.inputDriver = inputDriver;
        this.customer = customer;
        totalBicycles = new BicycleStand().getBicycles();
        availableBicycles = new BicycleStand().getBicycles();
    }

    public void displayMenu() {
        int option = 0;
        do{
            outputDriver.print(displayMessage.menu);
            option = inputDriver.inputAsInt();
            switch (option) {

                case 1 :    for(Bicycle bicycle : totalBicycles) {
                                outputDriver.print(bicycle.getName());
                                outputDriver.print(bicycle.getRentPerSecond());
                                outputDriver.print(bicycle.getStatus());
                                System.out.println();
                            }
                            break;

                case 2 :    outputDriver.print(displayMessage.bicycleToHire);
                            String hireBicycleName = inputDriver.inputAsString();
                            boolean hireSuccess = hireBicycle(hireBicycleName);
                            if(!hireSuccess) {
                                outputDriver.print(displayMessage.hireBicycleUnSuccess);
                            } else {
                                outputDriver.print(displayMessage.hireBicycleSuccess);
                            }
                            break;

                case 3 :    outputDriver.print(displayMessage.bicycleToReturn);
                            String returnBicycleName = inputDriver.inputAsString();
                            boolean returnSuccess = returnBicycle(returnBicycleName);
                            if(!returnSuccess) {
                                outputDriver.print(displayMessage.returnBicycleUnSuccess);
                            } else {
                                outputDriver.print(displayMessage.returnBicycleSuccess);
                                outputDriver.print(displayMessage.inVoice);
                                int choice = inputDriver.inputAsInt();
                                if(choice==1) {
                                    customer.seeInVoice();
                                }
                            }
                            break;
            }
        }while(option!=4);
    }

    boolean hireBicycle(String hireBicycleName) {
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

    boolean returnBicycle(String returnBicycleName) {
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
