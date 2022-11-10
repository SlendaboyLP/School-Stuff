package SwingFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class FilterPane extends JPanel {
    FilterFrame frame;
    JFileChooser fileChooser;
    JButton fileChooserBtn;
    FilterableImage image;
    JButton currentZoomStatus;
    JButton negateButton;

    FilterPane(FilterFrame frame) {

        this.frame = frame;
        setSize(frame.getWidth(), frame.getHeight());
        setBackground(new Color(255, 255, 255));
        setLayout(null);

        this.negateButton = new JButton("Negate Picture");
        negateButton.setBounds(this.getWidth() - 140, 10, 120, 30);
        negateButton.addActionListener(e -> {
            image.negativeFilter();
            this.repaint();
        });
        add(negateButton);

        //zoom button which shows current zoom, if clicked sets zoom back too 100%
        currentZoomStatus = new JButton("Zoom: 100%");
        currentZoomStatus.setBounds(10,this.getHeight() - 75,120,30);
        currentZoomStatus.addActionListener(e -> {
            image.setZoom(1.0);
            this.repaint();
        });
        add(currentZoomStatus);



        //sets the default look and feel depending on operating system
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //adds the filechooser with its button, duh
        addFileChooser();

        //allows user to zoom in and out
        addMouseWheelListener((e -> {

            if(image == null) return;

            if(e.getWheelRotation() == -1){
                if(image.getZoom() < 2.0 ){
                    image.setZoom(
                            image.getZoom() + 0.1
                    );
                }
            }

            if(e.getWheelRotation() == 1){
                if(image.getZoom() > 0.2 ){
                    image.setZoom(
                            image.getZoom() - 0.1
                    );
                }
            }
            this.repaint();
        }));


    }

    private void addFileChooser() {
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileFilter(
                new FileNameExtensionFilter("JPG & PNG Images", "jpg", "gif", "jpeg", "png")
        );
        this.fileChooserBtn = new JButton("Choose Image for Ball (1x1)");
        fileChooserBtn.setBounds(10, 10, 160, 30);
        this.fileChooserBtn.addActionListener(e -> {
            if (this.fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = ImageIO.read(new File(this.fileChooser.getSelectedFile().toURI()));
                    this.image = new FilterableImage(
                            img,
                            img.getWidth(),
                            img.getHeight()
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                this.repaint();
                this.requestFocus();
            }
        });

        add(fileChooserBtn);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (image != null) {
            g.drawImage(
                image.getImage(),
                    this.getWidth() / 2 - image.getCurrWidth() / 2,
                    this.getHeight() / 2 - image.getCurrHeight() / 2,
                    image.getCurrWidth(),
                    image.getCurrHeight(),
                    null
            );

            currentZoomStatus.setText(
                    "Zoom: " + Math.round(image.getZoom() * 100) + "%"
            );
        }
    }
}