package Football;

import java.util.ArrayList;

public class Team {
    String name;
    ArrayList<Player> players;
    int points;

    public Team(String name, int points) {
        this.name = name;
        this.players = new ArrayList<>();
        this.points = points;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void removePlayer(Player player){
        this.players.remove(player);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", players=" + players +
                ", points=" + points +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
