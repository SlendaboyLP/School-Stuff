package Football;

public class Main {
    public static void main(String[] args) {
        Team t1 = new Team("Team 1", 0);
        Team t2 = new Team("Team 2", 0);
        Team t3 = new Team("Team 3", 0);
        
        Player p1 = new Player("Player 1", 0, t1);
        Player p2 = new Player("Player 2", 0, t1);
        Player p3 = new Player("Player 3", 0, t2);
        Player p4 = new Player("Player 4", 0, t2);
        Player p5 = new Player("Player 5", 0, t3);
        Player p6 = new Player("Player 6", 0, t3);

        t1.addPlayer(p1);
        t1.addPlayer(p2);
        t2.addPlayer(p3);
        t2.addPlayer(p4);
        t3.addPlayer(p5);
        t3.addPlayer(p6);

        t1.getPlayers().forEach(System.out::println);
        t2.getPlayers().forEach(System.out::println);
        t3.getPlayers().forEach(System.out::println);




    }
}
