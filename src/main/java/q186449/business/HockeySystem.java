package q186449.business;


import java.util.ArrayList;
import java.util.List;

public class HockeySystem {

    private final Validator validator = new Validator(this);
    private Team[] teams = new Team[4];
    private HockeyMatch[] hockeyMatches = new HockeyMatch[4];

    public void add(Team team) throws ValidationException {
        validator.validate(team);
        boolean hasBeenAdded = false;

        for (int i = 0; i < teams.length; i++) {
            if (teams[i] == null) {

                    teams[i] = team;
                    hasBeenAdded = true;

                break;
            }
        }
        if (hasBeenAdded == false) {
            Team[] moreTeams = new Team[teams.length * 2];
            moreTeams[teams.length] = team;
            for (int i = 0; i < teams.length; i++) {
                moreTeams[i] = teams[i];
            }
            teams = moreTeams;
        }
    }


    public List<Team> list() {
        List<Team> list = new ArrayList<>(teams.length);
        for (Team team : teams) {
            if (team != null) {
                list.add(team);
            }
        }
        return list;
    }

    public void add(HockeyMatch match) throws ValidationException {
        validator.validate(match);
        boolean hasBeenAdded = false;
        for (int i = 0; i < hockeyMatches.length; i++) {
            if (hockeyMatches[i] != null) {
                hockeyMatches[i] = match;
                hasBeenAdded = true;
            }
            break;
        }
        if (hasBeenAdded == false) {
            HockeyMatch[] moreHockeyMatches = new HockeyMatch[hockeyMatches.length * 2];
            moreHockeyMatches[hockeyMatches.length] = match;
            for (int i = 0; i < hockeyMatches.length; i++) {
                moreHockeyMatches[i] = hockeyMatches[i];
            }
            hockeyMatches = moreHockeyMatches;
        }

    }
}
