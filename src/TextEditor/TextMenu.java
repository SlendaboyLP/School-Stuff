package TextEditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextMenu extends JMenuBar {
    TextPanel panel;

    public TextMenu(TextPanel panel) {
        this.panel = panel;
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem clearItem = new JMenuItem("Clear");



        openItem.addActionListener(e -> {
            panel.readToTextArea();
        });

        saveItem.addActionListener(e -> panel.saveToFile());

        clearItem.addActionListener(e -> panel.clearTextArea());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(clearItem);
        this.add(fileMenu);
    }
}
