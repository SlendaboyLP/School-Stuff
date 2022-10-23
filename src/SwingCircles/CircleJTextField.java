package SwingCircles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircleJTextField extends JTextField implements ActionListener {

    private final CirclePanel panel;
    private final char identifier;

    CircleJTextField(String text, CirclePanel panel, char identifier){
        super(text);
        this.panel = panel;
        this.identifier = identifier;
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (identifier){
            case 'r' -> {
                panel.r = Integer.parseInt(this.getText());
                if(panel.r > 255) {
                    panel.r = 255;
                }
                if(panel.r < 0){
                    panel.r = 0;

                }
                this.setText("" + panel.r);
            }
            case 'g' -> {
                panel.g = Integer.parseInt(this.getText());
                if(panel.g > 255) {
                    panel.g = 255;
                }
                if(panel.g < 0){
                    panel.g = 0;

                }
                this.setText("" + panel.g);
            }
            case 'b' -> {
                panel.b = Integer.parseInt(this.getText());
                if(panel.b > 255) {
                    panel.b = 255;
                }
                if(panel.b < 0){
                    panel.b = 0;

                }
                this.setText("" + panel.b);
            }

        }
        panel.requestFocus();
        panel.components.get(panel.components.size() - 1).setBackground(new Color(panel.r,panel.g,panel.b));
    }
}
