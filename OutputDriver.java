package view;

public class OutputDriver {

    private Object object;

    public void print(Object object) {
        this.object = object;
        System.out.print(object+"\t");
    }

    @Override
    public String toString() {
        return "" + object;
    }
}
