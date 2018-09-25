import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIForm {
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JButton analyzeButton;
    private JButton calculateButton;
    private JButton clearButton;
    private JPanel panel4;

    public GUIForm() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = textArea1.getText();

                Calculator p = new Calculator();
                try {
                    String postFix = p.conver2Postfix(str);
                    textArea2.setText("计算结果为:" + p.numberCalculate(postFix));
                } catch (Exception ex) {
                    textArea2.setText("【提示：计算异常】");
                    ex.printStackTrace();
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                textArea2.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUIForm");
        frame.setContentPane(new GUIForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
