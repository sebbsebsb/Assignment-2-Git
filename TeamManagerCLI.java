import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamManagerCLI
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        File csvFile = new File("hackathon_teams.csv");
        TeamData store = new TeamData(csvFile);
        Scanner scan = new Scanner(System.in);

        boolean run = true;
        while (run)
        {
            System.out.println();
            System.out.println("1. View teams");
            System.out.println("2. Add team");
            System.out.println("3. Update team");
            System.out.println("4. Save and exit");
            System.out.print("Choose an option: ");

            String choice = scan.nextLine().trim();

            switch (choice)
            {
                case "1":
                    view(store);
                    break;
                case "2":
                    addTeam(store, scan);
                    break;
                case "3":
                    updateTeam(store, scan);
                    break;
                case "4":
                    store.save();
                    System.out.println("Saved. Exiting.");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        scan.close();
    }

    // print the list of teams from the CVS 
    private static void view(TeamData store)
    {
        ArrayList<TeamRecord> records = store.getRecords();
        if (records.isEmpty())
        {
            System.out.println("No teams loaded yet.");
            return;
        }

        System.out.println("university,team_name,initial_score,growth_rate");

        for (TeamRecord r : records)
        {
            System.out.println(r.university + "," + r.teamName + "," + (int)r.initialScore + "," + r.growthRate);
        }
    }

    // trim whitespace with trim() 
    private static void addTeam(TeamData store, Scanner scan)
    {
        System.out.print("University: ");
        String university = scan.nextLine().trim();

        System.out.print("Team name: ");
        String teamName = scan.nextLine().trim();

        // read as float 
        // need to edit so that it can handle invalid input (strings instead of int) without crashing
        System.out.print("Initial score: ");
        // float initialScore = Float.parseFloat(scan.nextLine().trim());

        float initialScore = 0;
        boolean validInput = false;
        
        while (!validInput)
        {
            try
            {
                initialScore = Float.parseFloat(scan.nextLine().trim());
                validInput = true;
            }

            catch (NumberFormatException e)
            {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }

        System.out.print("Growth rate: ");
        // float growthRate = Float.parseFloat(scan.nextLine().trim());

        float growthRate = 0;
        validInput = false;
        while (!validInput)
        {
            try
            {
                growthRate = Float.parseFloat(scan.nextLine().trim());
                validInput = true;
            }

            catch (NumberFormatException e)
            {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }

        if (store.add(university, teamName, initialScore, growthRate))
        {
            System.out.println("Added " + teamName + ".");
        }
        else
        {
            System.out.println("A team named \"" + teamName + "\" already exists.");
        }
    }

    // find the team by name, then update the fields with new values, 
    // or keep the old values if the user leaves them blank 
    private static void updateTeam(TeamData store, Scanner scan)
    {
        System.out.print("Team name to update: ");
        String target = scan.nextLine().trim();

        TeamRecord match = store.find(target);
        
        if (match == null)
        {
            System.out.println("No team found with that name.");
            return; 
        }

        System.out.println("Leave a field blank (press Enter) to keep its current value.");

        System.out.print("University (" + match.university + "): ");
        String university = scan.nextLine().trim();

        if (university.isEmpty()) { university = match.university; }

        System.out.print("Team name (" + match.teamName + "): ");
        String teamName = scan.nextLine().trim();

        if (teamName.isEmpty()) { teamName = match.teamName; }

        System.out.print("Initial score (" + match.initialScore + "): ");
        String initialScoreIn = scan.nextLine().trim();
        float initialScore; 

        if (initialScoreIn.isEmpty()) { initialScore = match.initialScore; } 
        else  { initialScore = Float.parseFloat(initialScoreIn); }

        System.out.print("Growth rate (" + match.growthRate + "): ");
        String growthRateIn = scan.nextLine().trim();
        float growthRate;

        if (growthRateIn.isEmpty()) { growthRate = match.growthRate; } 
        else { growthRate = Float.parseFloat(growthRateIn); }

        store.update(target, university, teamName, initialScore, growthRate);
        System.out.println("Updated " + teamName + ".");
    }

}
