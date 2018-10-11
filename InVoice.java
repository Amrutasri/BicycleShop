import java.util.Date;
import java.util.List;

public class InVoice {

    private List<Bicycle> bicycleList;
    private Date bicycleHiringTime;
    private Date bicycleReturnTime;
    private String customerName;

    private OutputDriver outputDriver = new OutputDriver();

    InVoice( String customerName, List<Bicycle> bicycleList, Date bicycleHiringTime, Date bicycleReturnTime) {
        this.customerName = customerName;
        this.bicycleList = bicycleList;
        this.bicycleHiringTime = bicycleHiringTime;
        this.bicycleReturnTime = bicycleReturnTime;
    }

    void generate() {
        for(Bicycle bicycle : bicycleList) {
            float totalRent = calculateTotalRent(bicycle.getName());
            outputDriver.print("Customer Name: "+customerName);
            outputDriver.print("Bicycle Name: "+bicycle.getName());
            outputDriver.print("Hired Time: "+bicycleHiringTime);
            outputDriver.print("ReturnTime: "+bicycleReturnTime);
            outputDriver.print("Total Rent = "+totalRent);
        }
    }

    private float calculateTotalRent(String bicycleName) {
        float rentPerSecondOfBicycle = getRentPerMinute(bicycleName);
        float totalTime = bicycleReturnTime.getTime() - bicycleHiringTime.getTime();
        System.out.println("Time = "+totalTime);
        return rentPerSecondOfBicycle * totalTime;
    }

    private float getRentPerMinute(String bicycleName) {
        for(Bicycle bicycle : bicycleList) {
            if(bicycle.getName().equals(bicycleName)) {
                return bicycle.getRentPerSecond();
            }
        }
        return Float.parseFloat(null);
    }
}
