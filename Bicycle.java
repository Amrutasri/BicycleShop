package model;

public class Bicycle {

    private String name;
    private float rentPerMinute;
    private boolean isAvailable = true;

    Bicycle(String name, float rentPerMinute) {
        this.name = name;
        this.rentPerMinute = rentPerMinute;
    }

    public String getName() {
        return name;
    }

    public float getRentPerMinute() {
        return rentPerMinute;
    }

    public void setStatus(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean getStatus() {
        return isAvailable;
    }
}
