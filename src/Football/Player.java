package Football;

public class Player {
    String name;
    int goals;
    Team team;

    public Player(String name, int goals, Team team) {
        this.name = name;
        this.goals = goals;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", goals=" + goals +
                ", team=" + team.getName() +
                '}';
    }
}
