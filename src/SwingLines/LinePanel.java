package SwingLines;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


class LinePanel extends JPanel {
    LineFrame frame;
    LineMouseHandler handler;
    ArrayList<Line> lines = new ArrayList<>();
    Color currColor;
    JButton colorChooserBtn;
    JTextField widthField;

    LinePanel(LineFrame frame){
        this.setSize(1280,720);
        this.frame = frame;
        this.handler = new LineMouseHandler(this);
        this.setBackground(new Color(255,255,255));

        this.widthField = new JTextField("2");
        this.widthField.setBounds(1160, 50, 100, 30);
//        this.widthField.setSize(100,30);
        this.add(widthField);

        this.colorChooserBtn = new JButton("Color");
        this.colorChooserBtn.setBounds(1160,10,100,30);
//        this.colorChooserBtn.setSize(100,30);
        this.colorChooserBtn.addActionListener((e) -> this.currColor = JColorChooser.showDialog(
            null,
            "Choose Color",
            null
        ));
        this.add(colorChooserBtn);
//        this.repaint();


        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
//        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        lines.forEach(elem -> elem.draw(g));

        lines.add(new Line(
            handler.prevX,
            handler.prevY,
            handler.x,
            handler.y,
            currColor,
            Integer.parseInt(widthField.getText())

        ));

    }
}
