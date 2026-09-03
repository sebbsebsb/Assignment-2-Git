import java.util.Scanner;

public class second {
    int I;
    float R;
    int D;
    float third = 0;
    Scanner scanner = new Scanner(System.in);

    public second() {
        ask();
        calculate();
        scanner.close();
    }
    public float getter() {
        return third;
    }
    
    private void ask() {
        System.out.print("Enter how many games: ");
        D = scanner.nextInt();
        System.out.print("Enter rate of increase: ");
        R = scanner.nextFloat();
        System.out.print("Enter score for each game: ");
        I = scanner.nextInt();
    }
    private void calculate() {
        float first = I;
        float second = I * R;

       if(D > 2){ for (int i = 3; i < D; i++) {
            third = second * R;
            first = second;
            second = third;

        }
        //added this comment
        if(D == 2){
            third = second +first;
        }
        else{
            third = first;
        }

    }
    }
}
