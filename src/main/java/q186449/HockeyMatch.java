package q186449;

public class HockeyMatch {

    private int id1;
    private int goal1;
    private int id2;
    private int goal2;
    private int playTime;

    /**
     * @param id1      The first Team of the HockeyMatch
     * @param goal1    The amount of goals of the first Team
     * @param id2      The second Team of the HockeyMatch
     * @param goal2    The amount of goals of the second Team
     * @param playTime The playtime of the HockeyMatch
     */
    public HockeyMatch(int id1, int goal1, int id2, int goal2, int playTime) {
        this.id1 = id1;
        this.goal1 = goal1;
        this.id2 = id2;
        this.goal2 = goal2;
        this.playTime = playTime;
    }

}
