package GraphObject;

public class Circle extends GraphObject{

    int r;

    public Circle(int x, int y, int r) {
        super(x, y);
        this.r = r;
    }

    @Override
    public void draw() {

    }

    @Override
    public boolean hit(int x, int y) {
        return false;
    }
}
