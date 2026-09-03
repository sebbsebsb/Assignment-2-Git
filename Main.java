import java.util.Scanner;

public class Main {
    int I;
    float R;
    int D;
    Scanner scanner = new Scanner(System.in);

    public void main(String[] args) {
        invokingMethod();
    }
    public void invokingMethod() {
        
        System.out.print("Input required score: ");
        int reqScore = scanner.nextInt();
        second secondClass = new compute();
        // if(secondClass.getter() > reqScore) {
        //     System.out.println("QUALIFIED");
        // }
        // else {
        //     System.out.println("NOT QUALIFIED");
        // }
        scanner.close();
    }
    
    
    
    
    public float compute() {
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
        }
        return score[D-1];
    }


}