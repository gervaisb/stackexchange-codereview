package q186449.cli;

import q186449.business.HockeyMatch;
import q186449.business.HockeySystem;
import q186449.business.Team;
import q186449.business.ValidationException;

import java.util.concurrent.atomic.AtomicBoolean;

class CommandLineInterface {

    public static void main(String[] args) {
        CommandLineInterface adapter = new CommandLineInterface(new HockeySystem());
        CommandHandler handler = new CommandHandler(adapter);
        while (adapter.isRunning()) {
            handler.handle(Terminal.readLine());
        }
    }

    private final HockeySystem system;
    private final AtomicBoolean running = new AtomicBoolean(true);

    public CommandLineInterface(HockeySystem system) {
        this.system = system;
    }

    private boolean isRunning() {
        return running.get();
    }

    public void addTeam(String[] parameters) {
        try {
            String[] parts2 = parameters[0].split(";"); // parts2[0] will be the "ID" & parts 2[1] will be the "Teamname"
            system.add(new Team(Integer.valueOf(parts2[0]), parts2[1]));
            Terminal.printLine("Ok");
        } catch (ValidationException ve) {
            print(ve);
        }
    }

    public void listTeams(String[] none) {
        for (Team team : system.list()) {
            Terminal.printLine(team.getId() + " " + team.getTeamName());
        }
    }

    public void addMatch(String[] parameters) {
        String[] parts2 = parameters[0].split(";");
        try {
            HockeyMatch hockeyMatch = new HockeyMatch(
                    Integer.valueOf(parts2[0]),
                    Integer.valueOf(parts2[1]),
                    Integer.valueOf(parts2[2]),
                    Integer.valueOf(parts2[3]),
                    Integer.valueOf(parts2[4]));
            system.add(hockeyMatch);
            Terminal.printLine("Ok");
        } catch (ValidationException ve) {
            print(ve);
        }
    }

    public void quit(String[] none) {
        running.set(false);
    }

    private void print(ValidationException ve) {
        for (String error : ve.getErrors()) {
            Terminal.printError(error);
        }
    }

}
