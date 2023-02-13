package Clock;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ClockPane extends JPanel implements Runnable {

    BufferedImage[] clockImages = new BufferedImage[10];
    ArrayList<String> times = new ArrayList<>();
    String time;
    ClockFrame frame;
    ControlPane controlPane;
    ControlFrame controlFrame;
    Clip clip;

    ClockPane(ClockFrame frame){
        this.setSize(700,240);
        this.frame = frame;

        controlFrame = new ControlFrame(this);
        readImages();


        Thread thread = new Thread(this);
        thread.start();
    }

    private void readImages(){
        for (int i = 0; i < this.clockImages.length; i++) {
            try {
                this.clockImages[i] = ImageIO.read(new File("src/Clock/images/" + i + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
        while (true){
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(GregorianCalendar.HOUR);
            int minute = calendar.get(GregorianCalendar.MINUTE);
            int second = calendar.get(GregorianCalendar.SECOND);

            String hourString = String.format("%02d", hour);
            String minuteString = String.format("%02d", minute);
            String secondString = String.format("%02d", second);

            this.time = hourString + minuteString + secondString;

            if(times.contains(time)){
                playAudio("src/Clock/wecker.wav");
                times.remove(time);
                controlFrame.getContentPane().removeAll();
                ((ControlPane)controlFrame.getContentPane()).setComponents();
                controlFrame.getContentPane().repaint();

                JDialog dialog = new JDialog();
                dialog.setSize(300, 300);
                dialog.setLayout(null);
                dialog.setLocationRelativeTo(null);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                JLabel label = new JLabel("Alarm stoppen");
                label.setBounds(90, 100, 120, 50);
                dialog.add(label);
                JButton ok = new JButton("OK");
                ok.setBounds(100, 200, 100, 50);
                ok.addActionListener(e -> {
                    dialog.dispose();
                    clip.stop();
                });
                dialog.add(ok);
                dialog.setVisible(true);

            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }

    public void paint(Graphics g){
        super.paint(g);

        for (int i = 0; i < time.length() / 3; i++) {
            int number = Integer.parseInt(time.substring(i, i+1));
            g.drawImage(this.clockImages[number], 20 + i * 100, 0, null);
        }
        for (int i = 2; i < time.length() * 2 / 3; i++) {
            int number = Integer.parseInt(time.substring(i, i+1));
            g.drawImage(this.clockImages[number], 40 + i * 100, 0, null);
        }
        for (int i = 4; i < time.length() ; i++) {
            int number = Integer.parseInt(time.substring(i, i+1));
            g.drawImage(this.clockImages[number], 60 + i * 100, 0, null);
        }
    }

    public void addTime(String time){
        this.times.add(time);
    }

    private void playAudio (String soundName){

        Thread t1 = new Thread(() -> {
            AudioInputStream audioInputStream;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            } catch (UnsupportedAudioFileException | IOException e) {
                throw new RuntimeException(e);
            }
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            try {
                clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException e) {
                throw new RuntimeException(e);
            }

            FloatControl volumeGainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeGainControl.setValue(-5.0f);

            clip.start();

        });

        t1.start();

    }
}

