package SwingCircles;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;


public class CirclePanel extends JPanel  {

    private final CircleMouseListener mouseListener;
    private final CircleKeyListener keyListener;
    private final ArrayList<Circle> circles = new ArrayList<>();


    ArrayList<JComponent> components = new ArrayList<>();
    int r = 0,
        b = 0,
        g = 0;


    CirclePanel(){

        initComponents(components);
        addComponents(components);
        this.mouseListener = new CircleMouseListener(this);
        this.keyListener = new CircleKeyListener(this);
        this.setLayout(null);
        this.setSize(1280,720);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.addKeyListener(keyListener);
        this.setFocusable(true);
        this.requestFocus();
    }

    private void initComponents(ArrayList<JComponent> components){
        JTextField[] larr = {
            new JTextField(),
            new JTextField(),
            new JTextField()
        };

        CircleJTextField[] tarr = {
            new CircleJTextField("0",this, 'r'),
            new CircleJTextField("0",this, 'g'),
            new CircleJTextField("0",this, 'b')
        };
        for (int i = 0; i < 3; i++) {

            switch (i){
                case 0 -> larr[i].setBackground(new Color(255,0,0));
                case 1 -> larr[i].setBackground(new Color(0,255,0));
                case 2 -> larr[i].setBackground(new Color(0,0,255));
            }
            larr[i].setEditable(false);
            larr[i].setBounds(
                1140,
                i * 35 + 10,
                30,
                30
            );

            tarr[i].setBounds(
                1170,
                i * 35 + 10,
                100,
                30
            );

            components.add(larr[i]);
            components.add(tarr[i]);
        }

        JTextField t = new JTextField();
        t.setBounds(1140,115,130,30);
        t.setEditable(false);
        t.setBackground(new Color(0,0,0));
        components.add(t);

    }

    private void addComponents(ArrayList<JComponent> comps) {
        for (JComponent comp : comps) {
            this.add(comp);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.keyListener.isEscape()) {
            circles.clear();
            return;
        }
        int x = this.mouseListener.getOriX();
        int y = this.mouseListener.getOriY();
        int curX = this.mouseListener.getCurX();
        int curY = this.mouseListener.getCurY();
        int rad = (int) this.mouseListener.getRad();
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(new Color(this.r,this.g, this.b));
        graphics2D.setStroke(new BasicStroke(3));

        circles.forEach(elem -> Circle.drawCircle(graphics2D, elem));
        Circle c1 = new Circle(x, y, rad, this.r, this.g, this.b);

        /*
         * if the mouse is draggin it will draw the line from the origin to the cur mouse position
         * */
        if (this.mouseListener.isDragging()) {
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
