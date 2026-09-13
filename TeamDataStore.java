import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamDataStore
{
    static final String HEADER = "university,team_name,initial_score,growth_rate";

    File csvFile;
    ArrayList<TeamRecord> records;

    public TeamDataStore(File csvFile) throws FileNotFoundException
    {
        this.csvFile = csvFile;
        this.records = load();
    }

    // check if the file exists and load the records from the CSV file 
    private ArrayList<TeamRecord> load() throws FileNotFoundException
    {
        ArrayList<TeamRecord> loaded = new ArrayList<>();

        if (!csvFile.exists())
        {
            System.err.println("Error: Could not find file.");
            return loaded; 
        }

        Scanner readCSV = new Scanner(csvFile);

        if (readCSV.hasNextLine())
        {
            readCSV.nextLine();
        }

        while (readCSV.hasNextLine())
        {
            String line = readCSV.nextLine();

            if (line.trim().isEmpty())
            {
                continue;
            }

            String[] parts = line.split(",");
            String university = parts[0].trim();
            String teamName = parts[1].trim();
            float initialScore = Float.parseFloat(parts[2].trim());
            float growthRate = Float.parseFloat(parts[3].trim());

            loaded.add(new TeamRecord(university, teamName, initialScore, growthRate));
        }

        readCSV.close(); 
        return loaded;
    }

    // IOSException is thrown if the file cannot be written to 
    public void save() throws IOException
    {
        FileWriter write = new FileWriter(csvFile);

        write.write(HEADER + "\n");

        for (TeamRecord r : records)
        {
            write.write(r.toCSVLine() + "\n");
        }

        write.close();
    }

    public ArrayList<TeamRecord> getRecords()
    {
        return records;
    }

    public TeamRecord find(String teamName)
    {
        for (TeamRecord r : records)
        {
            if (r.teamName.equalsIgnoreCase(teamName))
            {
                return r;
            }
        }
        return null;
    }

    public boolean add(String university, String teamName, float initialScore, float growthRate)
    {
        if (find(teamName) != null)
        {
            return false;
        }

        records.add(new TeamRecord(university, teamName, initialScore, growthRate));
        return true;
    }

    public boolean update(String targetTeamName, String newUniversity, String newTeamName, 
        float newInitialScore, float newGrowthRate)
    {
        TeamRecord match = find(targetTeamName);
        
        if (match == null)
        {
            return false;
        }

        match.university = newUniversity;
        match.teamName = newTeamName;
        match.initialScore = newInitialScore;
        match.growthRate = newGrowthRate;
        return true;
    }
}