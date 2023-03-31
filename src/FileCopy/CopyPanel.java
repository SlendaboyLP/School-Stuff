package FileCopy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;

public class CopyPanel extends JPanel {
    CopyFrame frame;
    File file;
    JTextField nameField;
    JProgressBar progressBar;
    public CopyPanel(CopyFrame frame) {
        this.frame = frame;
        this.setSize(frame.getWidth(), frame.getHeight());

        setLayout(null);

        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(5, 5, frame.getWidth()- 10, 68);
        toolBar.setFloatable(false);


        JButton openButton = new JButton(new ImageIcon("./src/FileCopy/open.png"));
        openButton.addActionListener(e -> openFile());


        nameField = new JTextField("Enter new file name");
        nameField.setSize(200, 20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 24));
        nameField.setForeground(Color.GRAY);

        nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                nameField.setText("");
                nameField.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(nameField.getText().equals("")){
                    nameField.setText("Enter new file name");
                    nameField.setForeground(Color.GRAY);
                }
            }
        });


        JButton copyButton = new JButton(new ImageIcon("./src/FileCopy/copy.png"));
        copyButton.addActionListener(e -> copyFile());

        toolBar.add(openButton);
        toolBar.add(nameField);
        toolBar.add(copyButton);
        this.add(toolBar);
        progressBar = new JProgressBar();
        progressBar.setBounds(5,frame.getHeight()-70, frame.getWidth()- 10, 30);
        this.add(progressBar);

    }


    public void openFile(){
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\alexz\\IdeaProjects\\School-Stuff\\src\\FileCopy");
        int i = fileChooser.showOpenDialog(frame);
        if(i == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            frame.setTitle(file.getName() + " - File Copy");
            progressBar.setValue(0);
        }
    }


    public void copyFile(){
        if(nameField.getText().equals("")) return;
        if(file == null) return;

        progressBar.setMaximum(100);
        int one = (int) (file.length()/100) != 0 ? (int) (file.length()/100) : 1;

        progressBar.setValue(0);

        CopyPanel that = this;


        Thread copy = new Thread(() -> {
            int bytes_read;
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\alexz\\IdeaProjects\\School-Stuff\\src\\FileCopy\\"+nameField.getText());
                byte[] buffer = new byte[8192];

                long progressBarValue = 0;

                while ((bytes_read = fileInputStream.read(buffer)) != -1){

                    fileOutputStream.write(buffer, 0, bytes_read);
                    progressBarValue += bytes_read;

                    progressBar.setValue((int) (progressBarValue/one));
                    that.repaint();
                }

                progressBar.setValue(100);

                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        copy.start();
//new FileOutputStream("moge.txt",true);
    }
}
