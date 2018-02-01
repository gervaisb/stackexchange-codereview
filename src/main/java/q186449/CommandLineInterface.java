package q186449;

class CommandLineInterface {

    public static void main(String[] args) {
        CommandLineInterface iface = new CommandLineInterface(new HockeySystem());
        CommandHandler handler = new CommandHandler(iface);
        handler.handle(Terminal.readLine());
    }

    private final HockeySystem system;

    public CommandLineInterface(HockeySystem system) {
        this.system = system;
    }

    public void addTeam(String[] parameters) {
        String[] parts2 = parameters[0].split(";"); // parts2[0] will be the "ID" & parts 2[1] will be the "Teamname"
        system.add(new Team(Integer.valueOf(parts2[0]), parts2[1]));
    }

    public void listTeams(String[] parameters) {
        for (Team team : system.list()) {
            Terminal.printLine(team.getId() + " " + team.getTeamName());
        }
    }

    public void addMatch(String[] parameters) {
        String[] parts2 = parameters[0].split(";");
        if (Integer.valueOf(parts2[4]) >= 60 && Integer.valueOf(parts2[4]) <= 120
                && Integer.valueOf(parts2[1]) != Integer.valueOf(parts2[3])) {
            HockeyMatch hockeyMatch = new HockeyMatch(
                    Integer.valueOf(parts2[0]),
                    Integer.valueOf(parts2[1]),
                    Integer.valueOf(parts2[2]),
                    Integer.valueOf(parts2[3]),
                    Integer.valueOf(parts2[4]));
            system.add(hockeyMatch);
        } else if (Integer.valueOf(parts2[4]) <= 60) {
            Terminal.printError("The minimum playtime of 60minutes has not been reached.");
        } else if (Integer.valueOf(parts2[4]) >= 120) {
            Terminal.printError("The maximum playtime of 120minutes has been exceeded.");
        } else if (Integer.valueOf(parts2[1]) == Integer.valueOf(parts2[3])) {
            Terminal.printError("Tie not allowed.");
        }
    }

}
