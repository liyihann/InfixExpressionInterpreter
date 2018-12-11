import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class GUIForm {
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JScrollPane scrollPane1;
    private JButton analyzeButton;
    private JButton calculateButton;
    private JButton clearButton;
    private JButton syntaxAnalyzeButton;
    private JButton a7Button;
    private JButton button2;
    private JButton a8Button;
    private JButton a9Button;
    private JButton button5;
    private JButton button6;
    private JButton a0Button;
    private JButton a00Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton button12;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton button16;
    private JButton button17;
    private JButton deleteButton;
    private JButton button19;
    private JButton cButton;

    public GUIForm() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "";
                String str = textArea1.getText();
                if(!str.equals("")){
                    if(str.contains(" ")|str.contains("\r")|str.contains("\n")|str.contains("\t")){
                        result+="【提示：字符间有空格，已自动删除。】\n";
                    }
                    str = str.replaceAll(" |\r|\n|\t","");

                    Analyzer a = new Analyzer(str);
                    //词法分析
                    a.printLexicalAnalysis();
                    if(a.isLexicalError()){
                        result+="【提示：词法分析错误，无法计算。请检查输入。】\n";
                    }else{
                        //语法分析
                        SyntaxAnalyzer s = new SyntaxAnalyzer(a);
                        s.printSyntaxAnalysis();
                        if(s.isSyntaxError()){
                            result+="\n【提示：语法分析错误，无法计算。请检查输入。】\n";
                        }else{
                            try{
                                Calculator p = new Calculator();
                                p.setCh(str.toCharArray());
                                result+="计算结果为:";
                                result+= p.numberCalculate(p.convert2Postfix(p.getCh()));

                            }catch (Exception ex){
                                ex.printStackTrace();
                                result+="\n【提示：计算异常，请查看分析结果。请检查输入】\n";
                            }

                        }
                    }
                    textArea2.setText(result);
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
                    if(str.contains(" ")|str.contains("\r")|str.contains("\n")|str.contains("\t")){
                        result+="【提示：字符间有空格，已自动删除。】\n";
                    }
                    str = str.replaceAll(" |\r|\n|\t","");
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
                    if(str.contains(" ")|str.contains("\r")|str.contains("\n")|str.contains("\t")){
                        result+="【提示：字符间有空格，已自动删除。】\n";
                    }
                    str = str.replaceAll(" |\r|\n|\t","");
                    Analyzer a = new Analyzer(str);
                    //词法分析
                    a.printLexicalAnalysis();
                    if(a.isLexicalError()){
                        result+="【提示：词法分析错误，无法进行语法分析。请检查输入。】\n";
                    }else{
                        //语法分析
                        SyntaxAnalyzer s = new SyntaxAnalyzer(a);
                        result+= s.printSyntaxAnalysis();
                        if(s.isSyntaxError()){
                            result+="\n【提示：语法分析错误，无法打印语法树。请检查输入。】\n";
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
        a7Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("7");
            }
        });
        a8Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("8");
            }
        });
        a9Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("9");
            }
        });
        a4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("4");
            }
        });
        a5Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("5");
            }
        });
        a6Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("6");
            }
        });
        a1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("1");
            }
        });
        a2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("2");
            }
        });
        a3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("3");
            }
        });
        a0Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("0");
            }
        });
        a00Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("00");
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("+");
            }
        });
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("/");
            }
        });
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append(".");
            }
        });
        button12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("-");
            }
        });
        button16.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("*");
            }
        });
        button17.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append("(");
            }
        });
        button19.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.append(")");
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String s = textArea1.getText();
                textArea1.setText(s.substring(0,s.length() - 1));
            }
        });
        cButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textArea1.setText("");
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
