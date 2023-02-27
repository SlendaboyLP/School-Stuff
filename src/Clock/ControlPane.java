package Clock;

import javax.swing.*;
import java.awt.*;


public class ControlPane extends JPanel {
    ClockPane pane;
    JTextField hourField;
    JTextField minuteField;
    JTextField secondField;
    JButton submit;
    public ControlPane(ClockPane pane){
        this.pane = pane;
        this.setSize(300,500);
        this.setLayout(null);

        setComponents();


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < pane.times.size(); i++) {
            g.drawString(pane.times.get(i), 20 , 165 + (i+1) * 20);
            JButton delete = new JButton("Entf");
            delete.setBounds(200, 150 + (i+1) * 20, 80, 20);
            int finalI = i;
            delete.addActionListener(e -> {
                pane.times.remove(finalI);
                this.removeAll();
                setComponents();
                this.repaint();
            });
            this.add(delete);
            repaint();
        }

    }

    public void setComponents(){
        this.hourField = new JTextField();
        this.hourField.setBounds(20,20, 260, 20);
        this.add(this.hourField);


        this.minuteField = new JTextField();
        this.minuteField.setBounds(20,50, 260, 20);
        this.add(this.minuteField);

        this.secondField = new JTextField();
        this.secondField.setBounds(20,80, 260, 20);

        this.add(this.secondField);


        this.submit = new JButton("Submit");
        this.submit.setBounds(20, 110, 100, 20);
        this.submit.requestFocus();
        this.submit.addActionListener( e -> {
            if(hourField.getText().equals("")) return;
            if(minuteField.getText().equals("")) return;
            if(secondField.getText().equals("")) return;

            String hour = String.format("%02d", Integer.parseInt(hourField.getText()));
            String min = String.format("%02d", Integer.parseInt(minuteField.getText()));
            String sec = String.format("%02d", Integer.parseInt(secondField.getText()));

            hour = hour.strip();
            min = min.strip();
            sec = sec.strip();

            String time = hour +  min + sec;

            pane.addTime(time);

            hourField.setText("");
            minuteField.setText("");
            secondField.setText("");
            this.repaint();
        });
        this.add(this.submit);

        JLabel alarmText = new JLabel("Active Alarms:");
        alarmText.setBounds(20,140,100,20);

        this.add(alarmText);

    }
}

