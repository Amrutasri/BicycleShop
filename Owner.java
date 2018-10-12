import java.util.ArrayList;
import java.util.List;

public class Owner {

    OutputDriver outputDriver = new OutputDriver();
    InputDriver inputDriver = new InputDriver();
    DisplayMessage displayMessage = new DisplayMessage();

    boolean grantPermission(String customerId) {
        outputDriver.print(displayMessage.grantPermission);
        return inputDriver.inputAsBoolean();
    }
}
