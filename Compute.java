import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Compute{
    int I;
    float R = (float) 0.0;
    int D;
    File teamsCSV = new File("hackathon.teams.csv");

    public void compute() {
        ArrayList<String[]> loadedTeams = readCSV(teamsCSV); // Reads the CSV into this variable
        ArrayList<Integer> scoreStatus = new ArrayList<>(); // Each team will have a scoreStatus at the same idx

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

        for (String[] team : loadedTeams)
        {
            // Adds array of cumulative score and pass status for each team
            scoreStatus.add(calcScorePass(Float.parseFloat(team[2]),
                    Float.parseFloat(team[3]), R, reqScore)); // Needs testing
        }

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

    static ArrayList<String[]> readCSV(File teamsCSV)
    {
        /*
         * This method will read the CSV file and output an ArrayList containing an Array
         * of String of its elements. The CSV will be read via Scanner
         *
         * Referenced from Baeldung (https://www.baeldung.com/java-csv-file-array)
         */
        ArrayList<String[]> teams = new ArrayList<>();
        /*
        try (Scanner readCSV = new Scanner(File))
        {
            while(readCSV.hasNextLine())
            {
                Scanner rowScan = new Scanner(file);
                String[] stats = new String[4];

                rowScan.next(); // Skips the first line which has the CSV format
                for(i = 0; i < 4; i++)
                {
                    rowScan.useDelimiter(",");
                    stats[i] = rowScan.next();
                }
                teams.add(stats);
            }
        }
        */
        try (Scanner readCSV = new Scanner(new File("hackathon_teams.csv")))
        {
            while (readCSV.hasNextLine())
            {
                String[] team = readCSV.nextLine().split(",");
                teams.add(team);
            }
        }

        return teams;
    }

    static float[] calcScorePass(float initScore, float growth, int rounds, int qualiCutoff)
    {
        /*
         * This method will take some values read from the CSV and number of rounds
         * and will calculate what the final cumulative score is, as well as whether
         * the team has qualified or not
         */
        float[] scorePass = new float[2];

        float qualiScore = 0;

        for(int i = 0; i < rounds; i++) // Calculates cumulative score
        {
            initScore *= growth;

            if(i == 4) // This shit so ass if someone has a better solution please implement it :sob:
            {
                qualiScore = initScore;
            }
        }

        scorePass[0] = initScore;

        if(qualiScore < qualiCutoff) // Determines qualification
        {
            scorePass[1] = 0;
        }
        else
        {
            scorePass[1] = 1;
        }

        return scorePass;
    }
    
}