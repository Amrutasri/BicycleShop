import java.util.Scanner;

public class InputDriver {

    private Scanner intScanner = new Scanner(System.in);
    private Scanner stringScanner = new Scanner(System.in);
    private Scanner booleanScanner = new Scanner(System.in);

    public int inputAsInt() {
        return intScanner.nextInt();
    }

    public String inputAsString() {
        return stringScanner.nextLine();
    }

    public boolean inputAsBoolean() {
        return booleanScanner.nextBoolean();
    }
 }
