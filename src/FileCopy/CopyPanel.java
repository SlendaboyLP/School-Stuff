package FileCopy;

import javax.swing.*;

public class CopyPanel extends JPanel {
    CopyFrame frame;
    public CopyPanel(CopyFrame frame) {
        this.frame = frame;
        this.setSize(frame.getWidth(), frame.getHeight());

        JToolBar toolBar = new JToolBar();
        JButton openButton = new JButton("Open");
        JButton copyButton = new JButton("Copy");
    }
}
