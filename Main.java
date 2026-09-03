import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        invokingMethod();
    }
    public static void invokingMethod() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Input required score: ");
        int reqScore = scanner.nextInt();
        second secondClass = new second();
        if(secondClass.getter() > reqScore) {
            System.out.println("QUALIFIED");
        }
        else {
            System.out.println("NOT QUALIFIED");
        }
        scanner.close();
    }
}