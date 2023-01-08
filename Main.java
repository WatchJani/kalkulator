import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static String Res = "";
    public static String number = "";
    public static String operation = "";
    public static final String[][] BUTTON_TEXTS = {
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"0", "CE", "/", "="}
    };

    public static void main(String[] args) {

        JPanel mainPanel = new JPanel(new BorderLayout());
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField field = new JTextField(10);
        JPanel btnPanel = new JPanel(new GridLayout(BUTTON_TEXTS.length,
                BUTTON_TEXTS[0].length));

        for (int i = 0; i < BUTTON_TEXTS.length; i++) {
            for (int j = 0; j < BUTTON_TEXTS[i].length; j++) {
                JButton btn = new JButton(BUTTON_TEXTS[i][j]);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String myChar = ((JButton) e.getSource()).getText();

                        if(myChar.equals("CE")){
                            Res = "";
                            operation="";
                            number="";
                        }else{
                            Res+=myChar;
                            if (myChar.equals("=")){
                                String twoNumber[];
                                twoNumber = number.split(" ");

                                float firstNumber =Float.parseFloat(twoNumber[0]);
                                float secondNumber = Float.parseFloat(twoNumber[1]);

                                Res= Float.toString(Operation(firstNumber, secondNumber, operation));
                                number = Res;
                                operation="";
                                myChar="-1";
                            }

                            if(!myChar.equals("-1")){
                                if (myChar.equals("+") || myChar.equals("-") || myChar.equals("*") || myChar.equals("/")){
                                    if(operation.length() == 1){
                                        return;
                                    }
                                    number += " ";
                                    operation += myChar;
                                }else{
                                    number+=myChar;
                                }
                            }
                        }
                        field.setText(Res);
                    }
                });
                btnPanel.add(btn);
            }
        }

        mainPanel.add(field, BorderLayout.PAGE_START);
        mainPanel.add(btnPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static float Operation(float first, float second, String operation){
        if( operation.equals("+") ){
            return first + second;
        }else if(operation.equals("-")){
            return first - second;
        } else if (operation.equals("*")) {
            return first * second;
        }else{
            return first / second;
        }
    }
}