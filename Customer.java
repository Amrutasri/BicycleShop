import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class Customer {

    private String name;
    private Date bicycleHireTime;
    private Date bicycleReturnTime;

    private LinkedHashMap<Bicycle,ArrayList<Date>> hiredAndReturned = new LinkedHashMap<Bicycle,ArrayList<Date>>();
    private LinkedHashMap<Bicycle,Date> hiredButNotReturned = new LinkedHashMap<Bicycle, Date>();

    Customer(String name) {
        this.name = name;
    }

    public void addHiredBicycles(Bicycle bicycle, Date bicycleHireTime) {
        this.bicycleHireTime = bicycleHireTime;
        hiredButNotReturned.put(bicycle,bicycleHireTime);
    }

    public void removeReturnedBicycles(Bicycle bicycle, Date bicycleReturnTime) {
        this.bicycleReturnTime = bicycleReturnTime;
        hiredButNotReturned.remove(bicycle);
        ArrayList<Date> timing = new ArrayList<Date>();
        timing.add(bicycleHireTime); timing.add(this.bicycleReturnTime);
        hiredAndReturned.put(bicycle,timing);
    }

    public void seeInVoice() {
        InVoice inVoice = new InVoice(name,hiredAndReturned);
        inVoice.generate();
    }
}
