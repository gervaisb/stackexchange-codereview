package q186449.business;


import java.util.HashSet;
import java.util.Set;

class Validator {

    private final HockeySystem system;

    public Validator(HockeySystem system) {
        this.system = system;
    }

    public void validate(Team team) throws ValidationException {
        Set<String> errors = new HashSet<>(3);
        for (Team existing : system.list()) {
            if ( existing!=null ) {
                if ( existing.getTeamName().equals(team.getTeamName()) ) {
                    errors.add("A team with that Name already exists.");
                }
                if ( existing.getId() == team.getId() || team.getId() == 0) {
                    errors.add("A Team with that ID already exists or the ID equals 0");
                }
            }
        }

        ValidationException.maybeThrow(errors);
    }

    public void validate(HockeyMatch match) throws ValidationException {
        Set<String> errors = new HashSet<>(3);
        if ( match.getPlayTime() <= 60) {
            errors.add("The minimum playtime of 60minutes has not been reached.");
        } else if ( match.getPlayTime() >= 120) {
            errors.add("The maximum playtime of 120minutes has been exceeded.");
        } else if ( match.getGoal1()==match.getGoal2() ) {
            errors.add("Tie not allowed.");
        }

        ValidationException.maybeThrow(errors);
    }

}
