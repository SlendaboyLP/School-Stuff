package TextEditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class TextPanel extends JPanel {
    JTextArea textArea;
    JScrollPane scrollPane;
    TextFrame frame;

    File file;

    JToolBar tbar;

    public TextPanel(TextFrame frame) {
        this.frame = frame;
        this.setLayout(null);
        this.setBackground(Color.GRAY);
        textArea = new JTextArea();

//        this.add(textArea);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(5, 5, frame.getWidth() - 10, frame.getHeight() - 110);
        this.add(scrollPane);

        this.tbar = new JToolBar();

        tbar.setBounds(5, frame.getHeight() - 100, 140, 34);
        JButton open = new JButton(new ImageIcon("./src/TextEditor/open.png"));
        JButton save = new JButton(new ImageIcon("./src/TextEditor/save.png"));
        JButton clear = new JButton(new ImageIcon("./src/TextEditor/clear.png"));
        open.addActionListener(e -> readToTextArea());
        save.addActionListener(e -> saveToFile());
        clear.addActionListener(e -> clearTextArea());
        tbar.add(open);
        tbar.add(save);
        tbar.add(clear);
        tbar.setFloatable(false);

        tbar.setVisible(true);

        this.add(tbar);
    }

    public void readToTextArea(){



        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileFilter(filter);

        int i = chooser.showDialog(null, "select");
        file = chooser.getSelectedFile();
        if(i == JFileChooser.APPROVE_OPTION){
            BufferedReader br;
            FileReader fr;
            try {

                fr = new FileReader(file);
                br = new BufferedReader(fr);
                String line;

                while((line = br.readLine()) != null){
                    textArea.setText(textArea.getText() + line + "\n");
                }

                fr.close();
                br.close();
            }catch (IOException ie){}

        }

        frame.setTitle(file.getName() + " - Text Editor");

    }

    public void clearTextArea(){
        textArea.setText("");
    }

    public void saveToFile(){
        BufferedWriter bw;
        FileWriter fw;

        try{
            fw = new FileWriter(file, false);
            fw.write(textArea.getText());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
