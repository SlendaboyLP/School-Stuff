package Menu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class OwnJMenuBar extends JMenuBar {
    public OwnJMenuBar() {
        JMenuItem menu1 = new JMenu("Menu1");
        JMenuItem menu2 = new JMenu("Menu2");
        JMenuItem text1 = new JMenuItem("text menu item 1");
        text1.setMnemonic(KeyEvent.VK_1);
        text1.setIcon(new ImageIcon("./src/Menu/icon.jpg"));
        text1.addActionListener(e -> System.out.println("text menu item 1"));

        JMenuItem text2 = new JMenuItem("text menu item 2");
        text2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK));
        text2.addActionListener(e -> System.out.println("text menu item 2"));

        menu1.add(text1);
        menu1.add(text2);

        menu1.add(new JSeparator());

        JRadioButtonMenuItem rad1 = new JRadioButtonMenuItem("radio button 1");
        JRadioButtonMenuItem rad2 = new JRadioButtonMenuItem("radio button 2");
        rad1.addActionListener(e -> System.out.println("radio button 1"));
        rad2.addActionListener(e -> System.out.println("radio button 2"));
        ButtonGroup group = new ButtonGroup();
        group.add(rad1);
        group.add(rad2);

        menu1.add(rad1);
        menu1.add(rad2);

        menu1.add(new JSeparator());

        JCheckBoxMenuItem check1 = new JCheckBoxMenuItem("check box 1");
        JCheckBoxMenuItem check2 = new JCheckBoxMenuItem("check box 2");
        check1.addActionListener(e -> System.out.println("check box 1"));
        check2.addActionListener(e -> System.out.println("check box 2"));
        menu1.add(check1);
        menu1.add(check2);


        JMenu submenu = new JMenu("submenu");
        JMenuItem text3 = new JMenuItem("text menu item 3");
        JMenuItem text4 = new JMenuItem("text menu item 4");

        text3.addActionListener(e -> System.out.println("text menu item 3"));
        text4.addActionListener(e -> System.out.println("text menu item 4"));

        submenu.add(text3);
        submenu.add(text4);

        menu1.add(submenu);

        add(menu1);
        add(menu2);

    }
}
