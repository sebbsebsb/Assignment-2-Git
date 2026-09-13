public class TeamRecord
{
    String university;
    String teamName;
    float initialScore;
    float growthRate;

    public TeamRecord(String university, String teamName, float initialScore, float growthRate)
    {
        this.university = university;
        this.teamName = teamName;
        this.initialScore = initialScore;
        this.growthRate = growthRate;
    }

    public String toCSVLine()
    {
        return university + "," + teamName + "," + initialScore + "," + growthRate;
    }
}
