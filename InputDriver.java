package view;

import java.util.Scanner;

public class InputDriver {

    Scanner intScanner = new Scanner(System.in);
    Scanner stringScanner = new Scanner(System.in);

    public int inputAsInt() {
        return intScanner.nextInt();
    }

    public String inputAsScanner() {
        return stringScanner.nextLine();
    }
}
