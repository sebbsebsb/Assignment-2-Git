import java.util.Scanner;

public class Compute{
    int I;
    float R;
    int D;
    
    public void compute() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Input required score: ");
        int reqScore = scanner.nextInt();
        
        System.out.print("Enter how many games: ");
        D = scanner.nextInt();
        float[] score = new float[D];
        System.out.print("Enter rate of increase: ");
        R = scanner.nextFloat();
        System.out.print("Enter score for first game: ");
        I = scanner.nextInt();
        
        score[0] = I;
        
        for (int i = 1; i < D; i++) {
            score[i]=score[i-1]*R;
            System.out.println("Iteration " + i + ": " + score[i]);
        }
        
        if (score[D-1] < reqScore) {
            System.out.println("NOT QUALIFIED");
        } else {
            System.out.println("QUALIFIED");
        }
        
        System.out.println("Final score: " + score[D-1]);
        
        scanner.close();
    }
    
}