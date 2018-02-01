package q186449;

import java.util.ArrayList;
import java.util.List;

public class HockeySystem {

    private Team[] teams = new Team[4];
    private HockeyMatch[] hockeyMatches = new HockeyMatch[4];

    public void add(Team team) {
        boolean hasBeenAdded = false;
        boolean hasNameError = false;
        boolean hasSameId = false;
        for (int i = 0; i < teams.length; i++) {
            if (teams[i] != null) {
                if (teams[i].getTeamName().equals(team.getTeamName())) {
                    hasNameError = true;
                }
                if (teams[i].getId() == team.getId() || team.getId() == 0) {
                    hasSameId = true;
                }
            }
            if (teams[i] == null) {
                if (team.getId() == 0) {
                    hasSameId = true;
                }
                if (hasNameError == false && hasSameId == false) {
                    teams[i] = team;
                    hasBeenAdded = true;
                }
                break;
            }
        }
        if (hasBeenAdded == false && hasNameError == false && hasSameId == false) {
            Team[] moreTeams = new Team[teams.length * 2];
            moreTeams[teams.length] = team;
            for (int i = 0; i < teams.length; i++) {
                moreTeams[i] = teams[i];
            }
            teams = moreTeams;
            hasBeenAdded = true;
        }
        if (hasNameError) {
            Terminal.printError("A team with that Name already exists.");
        } else if (hasSameId) {
            Terminal.printError("A Team with that ID already exists or the ID equals 0.");
        } else {
            Terminal.printLine("OK");
        }
    }


    public List<Team> list() {
        List<Team> list = new ArrayList<>(teams.length);
        for (Team team : teams) {
            if ( team!=null ) {
                list.add(team);
            }
        }
        return list;
    }

    public void add(HockeyMatch match) {
        if ( match.getPlayTime() <= 60) {
            Terminal.printError("The minimum playtime of 60minutes has not been reached.");
        } else if ( match.getPlayTime() >= 120) {
            Terminal.printError("The maximum playtime of 120minutes has been exceeded.");
        } else if ( match.getGoal1()==match.getGoal2() ) {
            Terminal.printError("Tie not allowed.");
        } else {
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
                hasBeenAdded = true;
            }
            if (hasBeenAdded) {
                Terminal.printLine("OK");
            }
        }
    }
}
