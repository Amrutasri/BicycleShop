import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class InVoice {

    private LinkedHashMap<Bicycle, ArrayList<Date>> bicycleList;
    private String customerName;
    private Date hiredTime;
    private Date returnTime;

    private OutputDriver outputDriver = new OutputDriver();

    InVoice( String customerName, LinkedHashMap<Bicycle,ArrayList<Date>> bicycleList) {
        this.customerName = customerName;
        this.bicycleList = bicycleList;
    }

    void generate() {
        for (Map.Entry<Bicycle,ArrayList<Date>> bicycle : bicycleList.entrySet()) {
            float totalRent = calculateTotalRent(bicycle.getKey().getName());
            outputDriver.print("Customer Name: "+customerName);
            outputDriver.print("Bicycle Name: "+bicycle.getKey().getName());
            outputDriver.print("Hired Time: "+bicycle.getValue().get(0));
            outputDriver.print("ReturnTime: "+bicycle.getValue().get(1));
            outputDriver.print("Total Rent = "+totalRent);
        }
    }

    private float calculateTotalRent(String bicycleName) {
        float rentPerSecondOfBicycle = getRentPerMinute(bicycleName);
        float totalTime = returnTime.getTime() - hiredTime.getTime();
        System.out.println("Time = "+totalTime);
        return rentPerSecondOfBicycle * totalTime;
    }

    private float getRentPerMinute(String bicycleName) {
        for(Map.Entry<Bicycle,ArrayList<Date>> bicycle : bicycleList.entrySet()) {
            if(bicycle.getKey().getName().equals(bicycleName)) {
                this.hiredTime = bicycle.getValue().get(0);
                this.returnTime = bicycle.getValue().get(1);
                return bicycle.getKey().getRentPerSecond();
            }
        }
        return Float.parseFloat(null);
    }
}
