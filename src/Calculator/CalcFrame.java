package Calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class CalcFrame extends JFrame {


    Container contentPane;
    CalcFrame(String title, int width, int height){
        super(title);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.contentPane = this.getContentPane();
        this.setLayout(null);
        this.setResizable(false);
        initUI();
//        this.pack();
        this.setVisible(true);
    }

    void initUI(){

        TextBar t1 = new TextBar();
        t1.setBounds(0,0,480,128);
        contentPane.add(t1);

        int[] nums = {
            7,8,9,
            4,5,6,
            1,2,3,
            0
        };
        char[] operators = {
            '+','-','*','/','=','C','.','±'
        };

        ButtonsPanel b1 = new ButtonsPanel(t1,nums, operators);
        b1.setBounds(0,128,480,512);
        contentPane.add(b1);


    }




}
