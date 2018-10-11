import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class InVoice {

    private LinkedHashMap<Bicycle, ArrayList<LocalDateTime>> bicycleList;
    private String customerName;
    private LocalDateTime hiredTime;
    private LocalDateTime returnTime;

    private OutputDriver outputDriver = new OutputDriver();
    private DisplayMessage displayMessage = new DisplayMessage();

    InVoice( String customerName, LinkedHashMap<Bicycle,ArrayList<LocalDateTime>> bicycleList) {
        this.customerName = customerName;
        this.bicycleList = bicycleList;
    }

    void generate() {
        for (Map.Entry<Bicycle,ArrayList<LocalDateTime>> bicycle : bicycleList.entrySet()) {
            float totalRent = calculateTotalRent(bicycle.getKey().getName());
            outputDriver.print(displayMessage.customerName+""+customerName+",");
            outputDriver.print(displayMessage.bicycleName+""+bicycle.getKey().getName()+",");
            outputDriver.print(displayMessage.hireTime+""+bicycle.getValue().get(0)+",");
            outputDriver.print(displayMessage.returnTime+""+bicycle.getValue().get(1));
            outputDriver.print(displayMessage.totalRent+""+totalRent);
        }
    }

    private float calculateTotalRent(String bicycleName) {
        float rentPerSecondOfBicycle = getRentPerSecond(bicycleName);
        float totalTime = ChronoUnit.SECONDS.between(hiredTime,returnTime);
        //System.out.println("Time = "+totalTime);
        return rentPerSecondOfBicycle * totalTime;
    }

    private float getRentPerSecond(String bicycleName) {
        for(Map.Entry<Bicycle,ArrayList<LocalDateTime>> bicycle : bicycleList.entrySet()) {
            if(bicycle.getKey().getName().equals(bicycleName)) {
                this.hiredTime = bicycle.getValue().get(0);
                this.returnTime = bicycle.getValue().get(1);
                return bicycle.getKey().getRentPerSecond();
            }
        }
        return Float.parseFloat(null);
    }
}
