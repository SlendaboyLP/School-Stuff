package Learning_Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyJPanel extends JPanel implements ActionListener{

    JButton b;
    MyJFrame myf;

    public MyJPanel(MyJFrame myf) {

        this.myf = myf;

        this.setLayout(null);

        b = new JButton("Go!");
        b.setBounds(170,100,70,30);
        b.addActionListener(this);
//        b.addActionListener(e -> {
//            System.out.println("Klick");
//            b.setn
//        });
        this.add(b);


        this.setBackground(new Color(150,100,230));
        this.setBounds(0,0,400,200);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        System.out.println("Klick");

        b.setBackground(Color.black);

        Graphics g = this.getGraphics();

        g.drawLine(0,0,100,100);

        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(0,0,200,50);




    }

    public void paint(Graphics g) {
        System.out.println("!");
        super.paint(g);
        g.drawLine(50, 50,50,150);
    }

}