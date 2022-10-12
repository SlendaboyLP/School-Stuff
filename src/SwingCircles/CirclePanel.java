package SwingCircles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CirclePanel extends JPanel  {

    private final CircleMouseListener mouseListener;
    private final CircleKeyListener keyListener;
    private final ArrayList<Circle> circles = new ArrayList<>();
       CirclePanel(){
            this.mouseListener = new CircleMouseListener(this);
            this.keyListener = new CircleKeyListener(this);
            this.setSize(1280,720);
            this.addMouseListener(mouseListener);
            this.addMouseMotionListener(mouseListener);
            this.addKeyListener(keyListener);
            this.setFocusable(true);
            this.requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(this.keyListener.isEscape()){
            circles.clear();
            return;
        }
        int x = this.mouseListener.getOriX();
        int y = this.mouseListener.getOriY();
        int curX = this.mouseListener.getCurX();
        int curY = this.mouseListener.getCurY();
        int rad = (int) this.mouseListener.getRad();
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(3));



        circles.forEach(elem -> Circle.drawCircle(graphics2D, elem));
        Circle c1 = new Circle(x,y,rad);


        /*
         * if the mouse is draggin it will draw the line from the origin to the cur mouse position
         * */


        if(this.mouseListener.isDragging()){
            graphics2D.drawLine(
                x,
                y,
                curX,
                curY
            );
            return;
        }

        /*
          when stopping the dragging the circle will be drawn
          */
        Circle.drawCircle(graphics2D,c1);
        circles.add(c1);

    }
}
