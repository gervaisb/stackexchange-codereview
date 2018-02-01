package q186449;

public class HockeySystem {

            private static boolean var = true;
            private Team[] teams = new Team[4];
            private HockeyMatch[] hockeyMatches = new HockeyMatch[4];
            private Standings[] standings = new Standings[4];

            /**
             *
             * @param args for command-line arguments
             */
            public static void main(String[] args) {
                HockeySystem m = new HockeySystem();

                while (var == true) {

                    m.method();

                }
            }

            /**
             * This method contains multiple IF-Operations. Depending on the Input in
             * the Terminal, different operations will be executed.
             *
             * "add-team" Input : "team" will be added to the Array "teams" if "team"
             * passes multiple tests, which if it doesn't will lead to Error messages.
             * In case "teams" is full, the Array will be doubled in size.
             *
             * "list-team" Input : every "team" in the Array "teams" will be printed ,
             * each one in a new row.
             *
             * "add-ice-hockey-match" Input : "hockeyMatch" will be added to the Array
             * "hockeyMatches" if "hockeyMatch" matches the requirements. If it doesn't
             * match those , an Error message will be printed. In case "hockeyMatch" is
             * full, the Array will be doubled in size.
             *
             * "quit" Input : The program will be
             *
             */
            public void method() {
                String[] parts = Terminal.readLine().split(" ");
                if (parts[0].startsWith("add-team")) {
                    String[] parts2 = parts[1].split(";"); // parts2[0] will be the "ID" & parts 2[1] will be the "Teamname"
                    boolean hasBeenAdded = false;
                    boolean hasNameError = false;
                    boolean hasSameId = false;
                    Team team = new Team(Integer.valueOf(parts2[0]), parts2[1]);
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
                if (parts[0].equals("list-team")) {
                    for (int i = 0; i < teams.length; i++) {
                        if (teams[i] != null) {
                            Terminal.printLine(teams[i].getId() + " " + teams[i].getTeamName());
                        }
                    }
                }
                if (parts[0].equals("add-ice-hockey-match")) {
                    String[] parts2 = parts[1].split(";");
                    if (Integer.valueOf(parts2[4]) >= 60 && Integer.valueOf(parts2[4]) <= 120
                            && Integer.valueOf(parts2[1]) != Integer.valueOf(parts2[3])) {
                        HockeyMatch hockeyMatch;
                        hockeyMatch = new HockeyMatch(
                                Integer.valueOf(parts2[0]),
                                Integer.valueOf(parts2[1]),
                                Integer.valueOf(parts2[2]),
                                Integer.valueOf(parts2[3]),
                                Integer.valueOf(parts2[4]));
                        boolean hasBeenAdded = false;
                        for (int i = 0; i < hockeyMatches.length; i++) {
                            if (hockeyMatches[i] != null) {
                                hockeyMatches[i] = hockeyMatch;
                                hasBeenAdded = true;
                            }
                            break;
                        }
                        if (hasBeenAdded == false) {
                            HockeyMatch[] moreHockeyMatches = new HockeyMatch[hockeyMatches.length * 2];
                            moreHockeyMatches[hockeyMatches.length] = hockeyMatch;
                            for (int i = 0; i < hockeyMatches.length; i++) {
                                moreHockeyMatches[i] = hockeyMatches[i];
                            }
                            hockeyMatches = moreHockeyMatches;
                            hasBeenAdded = true;
                        }
                        if (hasBeenAdded) {
                            Terminal.printLine("OK");
                        }
                    } else if (Integer.valueOf(parts2[4]) <= 60) {
                        Terminal.printError("The minimum playtime of 60minutes has not been reached.");
                    } else if (Integer.valueOf(parts2[4]) >= 120) {
                        Terminal.printError("The maximum playtime of 120minutes has been exceeded.");
                    } else if (Integer.valueOf(parts2[1]) == Integer.valueOf(parts2[3])) {
                        Terminal.printError("Tie not allowed.");
                    }
                }
                if (parts[0].equals("print-del-standings")) {
                }
            }


            public void quit(){
                var = false;
            }
        }
