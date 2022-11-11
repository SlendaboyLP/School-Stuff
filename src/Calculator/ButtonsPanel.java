package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel implements ActionListener{
    private final TextBar t1;

    ButtonsPanel(TextBar t1, int[] nums, char[] ops){
        this.t1 = t1;
        this.setSize(480,512);
        this.setLayout(new GridLayout(5,4));
        addButtons(nums, ops);
        this.setVisible(true);

    }


    private void addButtons(int[] nums, char[] ops) {

        /**
         * Adding all depending buttons of the calculator in the correct order that it fits in the gridlayout
         * */
        for (int i = 0; i < 2; i++) this.add(makeBtn("", this));

        this.add(makeBtn("" + ops[5], this));

        this.add(makeBtn("" + ops[3], this));

        for (int i = 0; i < 3; i++) this.add(makeBtn("" + nums[i], this));

        this.add(makeBtn("" + ops[2], this));

        for (int i = 3; i <6; i++) this.add(makeBtn("" + nums[i], this));

        this.add(makeBtn("" + ops[1], this));

        for (int i = 6; i <9; i++) this.add(makeBtn("" + nums[i], this));

        this.add(makeBtn("" + ops[0], this));

        this.add(makeBtn("" + nums[9], this));

        this.add(makeBtn("" + ops[6],this));

        this.add(makeBtn("" + ops[7], this));

        /**
         * Special Button the equal operator calculates whatever is in the string
         * kinda works
         * works good enough
         * not gonna make it perfect
         * */
        this.add(makeBtn("" + ops[4], e -> {

            if(t1.getText().equals("")) return;

            String operation = t1.getText();
            String helper = t1.getText();
            char selectedMethod = ' ';
            String[] operands = null;
            double[] operandNumbs = new double[2];
            double answer = 0;
            char[] symbols = {
                '+',
                '-',
                '*',
                '/'
            };
            /**
             * select which type of operation is used in the calculation
             * if the first number is negative the string has to shortend so the string doesnt split on the wrong position
             * */

            if(operation.charAt(0) == '-'){
                helper = helper.substring(1);
                for (char symbol : symbols) {
                    if(helper.contains("" + symbol)) selectedMethod = symbol;
                }
                operands =  helper.split("\\" + selectedMethod);
                operands[0] = "-" + operands[0];
            }else {
                for (char symbol : symbols) {
                    if(helper.contains("" + symbol)) selectedMethod = symbol;
                }
                operands =  helper.split("\\" + selectedMethod);
            }

            /**
             * stops if no operator in the operation
             * */
            if(selectedMethod == ' ') return;

            /**
             * parsing the string to doubles
             * sometimes an error with split and negative number occurs the ternary operation kinda fixes this
             * */

            operandNumbs[0] = Double.parseDouble(operands[0]);
            operandNumbs[1] = operands.length > 2 ? Double.parseDouble(operands[2]) : Double.parseDouble(operands[1]);

            switch (selectedMethod){
                case '+' : {
                    answer = operandNumbs[0] + operandNumbs[1];
                }
                case '-' : answer = operandNumbs[0] - operandNumbs[1];
                case '*' : answer = operandNumbs[0] * operandNumbs[1];
                case '/' : answer = operandNumbs[0] / operandNumbs[1];
            }

            t1.setText("" + answer);
        }));
    }

    /**
     * @param text
     * @param al
     * @return
     */
    private JButton makeBtn (String text, ActionListener al){
        JButton b = new JButton(text);
        b.setSize(30,30);
        b.addActionListener(al);
        return b;
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();
        if(input.equals("C")) {
            t1.setText("");
            return;
        }
        if(input.equals("±")){
            input = "-";
        }
        t1.setText(t1.getText() + input);

    }

}
