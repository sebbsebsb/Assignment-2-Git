import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;

public class Compute{
    // int I; // initial score
    // float R = 0; // Rate of increase
    int D; // # of rounds/iterations
    File teamsCSV = new File("hackathon_teams.csv");

    public ArrayList<TeamResult> compute() {
        ArrayList<String[]> loadedTeams = readCSV(teamsCSV); // Reads the CSV into this variable (all in string format)
        ArrayList<float[]> scoreStatus = new ArrayList<>(); // Each team will have a scoreStatus at the same idx

        Scanner scan = new Scanner(System.in);

//        System.out.print("Input required score: ");
        float reqScore = scan.nextFloat();
//
//        System.out.print("Enter how many games: ");
        D = scan.nextInt();
        if (D < 4) {
            D = 4;
        } else if (D > 10) {
            D = 10;
        }
        float[] score = new float[D + 1];
        // System.out.print("Enter rate of increase: ");
        // R = scan.nextFloat();

//        for (String[] team : loadedTeams) {
//            System.out.println(loadedTeams);
//        }
//        System.out.println(Arrays.toString(loadedTeams.get(0)));

        for (int i = 1; i < loadedTeams.size(); ++i)
        // loadedTeams is the arrayList
        // this loops through each team once
        {
            // for each loop, team is one row (one team), an array
            String[] team = loadedTeams.get(i);

            float initScore = Float.parseFloat(team[2]);
            float growth = Float.parseFloat(team[3]);

            // Adds array of cumulative score and pass status for each team
            scoreStatus.add(calcScorePass(initScore, growth, D, reqScore)); // Needs testing

//          System.out.println(team[0] + " " + team[1] + " " + team[2] + " " + team[3]);

        }

        ArrayList<TeamResult> finalResults = fillFinalResults(loadedTeams, scoreStatus); //final results here
        displayTeams(finalResults);

        // System.out.print("Enter score for first game: ");
        // I = scan.nextInt();
        // score[0] = I;

        // for (int i = 1; i < D+1; i++) {
        //     score[i]=score[i-1]*R;
        //     System.out.println("Iteration " + i + ": " + score[i]);
        // }
//
//        if (score[4] < reqScore) {
//            System.out.println("NOT QUALIFIED");
//        } else {
//            System.out.println("QUALIFIED");
//        }

//        System.out.println("Final score: " + score[D-1]);

        scan.close();
        return finalResults;
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
        try (Scanner readCSV = new Scanner(teamsCSV)) // changed to use the above referenced file path
        {
            while (readCSV.hasNextLine())
            {
                String[] team = readCSV.nextLine().split(",");
                teams.add(team);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Error: The requested file could not be found.");
        }

        return teams;
    }

    static float[] calcScorePass(float initScore, float growth, int rounds, float qualiCutoff)
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

            if(i == 3) // This shit so ass if someone has a better solution please implement it :sob:
                // changed i to 3 since it starts at round 0
            {
                qualiScore = initScore;
            }
        }

        scorePass[0] = initScore;

//        System.out.println("qualiscore: " + qualiScore + "\tinitScore: " + initScore + "\tqualiCutoff: " + qualiCutoff + "\tgrowth: "  + growth);
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


    static void displayTeams(ArrayList<TeamResult> finalResults) {
        int numTeams = finalResults.size();
        // get order from best to worst
        Collections.sort(finalResults, (TeamResult2, TeamResult1) -> Float.compare(TeamResult1.score, TeamResult2.score));


        for (int i = 0; i < numTeams; ++i)
        {
            System.out.print("Team: " + finalResults.get(i).name + "\t|\t");
            System.out.printf("Score: %.2f", finalResults.get(i).score);
            if (finalResults.get(i).qualified) {
                System.out.println("\tQUALIFIED");
            } else {
                System.out.println("\tNOT QUALIFIED");
            }
        }
    }

    static ArrayList<TeamResult> fillFinalResults (ArrayList<String[]> loadedTeams,  ArrayList<float[]> scoreStatus)
    {
        ArrayList<TeamResult> finalResults = new ArrayList<>();

        for (int i = 0; i < loadedTeams.size() - 1; ++i)
        // adds the names for every team
        {
            boolean passed = (scoreStatus.get(i)[1] == 1.0f);
            finalResults.add(new TeamResult(loadedTeams.get(i+1)[0], scoreStatus.get(i)[0], passed));
        }

        return finalResults;
    }

}

