import java.util.Scanner;
import java.util.ArrayList;

public class Compute{
    int I;
    float R;
    int D;
    
    public void compute() {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Input required score: ");
        int reqScore = scan.nextInt();
        
        System.out.print("Enter how many games: ");
        D = scan.nextInt();
        if (D < 4) {
            D = 4;
        } else if (D > 10) {
            D = 10;
        }
        float[] score = new float[D + 1];
        System.out.print("Enter rate of increase: ");
        R = scan.nextFloat();
        System.out.print("Enter score for first game: ");
        I = scan.nextInt();
        
        score[0] = I;
        
        for (int i = 1; i < D+1; i++) {
            score[i]=score[i-1]*R;
            System.out.println("Iteration " + i + ": " + score[i]);
        }
        
        if (score[4] < reqScore) {
            System.out.println("NOT QUALIFIED");
        } else {
            System.out.println("QUALIFIED");
        }
        
        System.out.println("Final score: " + score[D-1]);
        
        scan.close();
    }

    static ArrayList<String[]> readCSV()
    {
        /*
         * This method will read the CSV file and output an ArrayList containing an Array
         * of String of its elements. The CSV will be read via Scanner
         *
         * Referenced from Baeldung
         */
        ArrayList<String[]> teams = new ArrayList<String[]>();
        Scanner readCSV = new Scanner(new File("hackathon_teams.csv"));
        while(readCSV.hasNextLine())
        {
            Scanner rowScan = new Scaner(line);
            String[] stats = new String[4];
            for(i = 0; i < 4; i++)
            {
                rowScan.useDelimiter(COMMA_DELIMITER);
                stats[i] = rowScan.next();
            }
            teams.add(stats);
        }

        return teams;
    }
    
}