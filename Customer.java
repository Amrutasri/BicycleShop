import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class Customer {

    private String name;
    private String Id;
    private List<Bicycle> totalBicyclesTaken = new ArrayList<>();
    private LocalDateTime bicycleHireTime;
    private LocalDateTime bicycleReturnTime;

    private LinkedHashMap<Bicycle,ArrayList<LocalDateTime>> hiredAndReturned = new LinkedHashMap<Bicycle,ArrayList<LocalDateTime>>();
    private LinkedHashMap<Bicycle,LocalDateTime> hiredButNotReturned = new LinkedHashMap<Bicycle, LocalDateTime>();

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    public List<Bicycle> getTotalBicyclesTaken() {
        return totalBicyclesTaken;
    }

    public void addHiredBicycles(Bicycle bicycle, LocalDateTime bicycleHireTime) {
        this.totalBicyclesTaken.add(bicycle);
        this.bicycleHireTime = bicycleHireTime;
        hiredButNotReturned.put(bicycle,bicycleHireTime);
    }

    public void removeReturnedBicycles(Bicycle bicycle, LocalDateTime bicycleReturnTime) {
        this.bicycleReturnTime = bicycleReturnTime;
        hiredButNotReturned.remove(bicycle);
        ArrayList<LocalDateTime> timing = new ArrayList<LocalDateTime>();
        timing.add(bicycleHireTime); timing.add(this.bicycleReturnTime);
        hiredAndReturned.put(bicycle,timing);
    }

    public void seeInVoice() {
        InVoice inVoice = new InVoice(name,hiredAndReturned);
        inVoice.generate();
    }
}
