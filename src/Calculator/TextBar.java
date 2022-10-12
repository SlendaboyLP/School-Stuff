package Calculator;

import javax.swing.*;
import java.awt.*;

public class TextBar extends JTextField {
    TextBar(){
        super();
        this.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        this.setSize(480, 128);
        this.setEditable(false);
        this.setVisible(true);
    }

}
