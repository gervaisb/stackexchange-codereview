package q186449.business;

public class Team {

    private int id;
    private String teamName;

    /**
     * @param id       the Team consists of an ID & teamname
     * @param teamName the Team consists of an ID & teamname
     */
    public Team(int id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    /**
     * @return the Teamname of the Team.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @return the ID of the Team.
     */
    public int getId() {
        return id;
    }


}
