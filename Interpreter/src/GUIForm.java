import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Scanner;

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
    private JScrollPane scrollPane1;
    private JButton syntaxAnalyzeButton;

    public GUIForm() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "";
                String str = textArea1.getText();
                if(!str.equals("")){
                    if(str.contains(" ")){
                        result+="【提示：字符间有空格，已自动删除。】\n";
                    }
                    str = str.replaceAll(" ","");
                    Calculator p = new Calculator();
                    try {
                        p.setCh(str.toCharArray());
                        result+="计算结果为:";
                        result+= p.numberCalculate(p.convert2Postfix(p.getCh()));
                        textArea2.setText(result);
                    } catch (Exception ex) {
                        textArea2.setText("【提示：计算异常，请查看分析结果】\n");
                        ex.printStackTrace();
                    }
                }
                else {
                    textArea2.setText("【提示：输入为空】\n");
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
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "";
                String str = textArea1.getText();
                if(!str.equals("")){
                    if(str.contains(" ")){
                        result+="【提示：字符间有空格，已自动删除。】\n";
                    }
                    str = str.replaceAll(" ","");
                    Analyzer a = new Analyzer(str);
                    //词法分析
                    result+= a.printLexicalAnalysis();
                    textArea2.setText(result);
                }
                else{
                    textArea2.setText("【提示：输入为空】\n");
                }

            }
        });
        syntaxAnalyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "";
                String str = textArea1.getText();
                if(!str.equals("")){
                    if(str.contains(" ")){
                        result+="【提示：字符间有空格，已自动删除。】\n";
                    }
                    str = str.replaceAll(" |\r|\n|\t","");
                    Analyzer a = new Analyzer(str);
                    //词法分析
                    a.printLexicalAnalysis();
                    if(a.isLexicalError()){
                        result+="【提示：词法分析错误，无法进行语法分析。请检查输入】\n";
                    }else{
                        //语法分析
                        SyntaxAnalyzer s = new SyntaxAnalyzer(a);
                        result+= s.printSyntaxAnalysis();
                        if(s.isSyntaxError()){
                            result+="\n【提示：语法分析错误，无法打印语法树。请检查输入】\n";
                        }else{
                            try{
                                SyntaxTree t = new SyntaxTree(s);
                                result+=t.printSyntaxTree();
                            }catch (Exception ex){
                                ex.printStackTrace();
                                result+="\n【提示：语法分析错误，无法打印语法树。请检查输入】\n";
                            }

                        }
                    }
                    textArea2.setText(result);
                }
                else{
                    textArea2.setText("【提示：输入为空】\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUIForm");
        frame.setContentPane(new GUIForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,400);
        frame.setVisible(true);
    }

}
