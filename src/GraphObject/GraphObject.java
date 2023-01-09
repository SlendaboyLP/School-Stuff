package GraphObject;

public class GraphObject {
    int x,y;

    public GraphObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public  void draw(){
        System.out.println("x: " + x + " y: " + y);
    }
    public boolean hit(int x, int y){
        return (this.x == x && this.y == y);
    }
}
