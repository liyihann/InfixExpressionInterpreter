import java.util.*;

public class Calculator {
    private ArrayList<String> checkedword;

    public Calculator() {
    }
    public Calculator(SyntaxAnalyzer s) {
        this.checkedword = s.getWord();
    }

    private Stack<Double> stackN = new Stack<Double>();// 数字
    private Stack<String> stackF = new Stack<String>();// 符号
    private Stack<String> stackZ = new Stack<String>();// 中间

    /**
     * 判断是否为运算符
     *
     * @param s
     * @return
     */
    public boolean isOperator(String s) {
        if ("(".equals(s) || ")".equals(s) || "+".equals(s) || "-".equals(s)
                || "*".equals(s) || "/".equals(s))

            return true;
        else
            return false;
    }

    /**
     * 判断是否为数字
     * 由于checkedword已经检查过，非operator的均为数字
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        if(isOperator(s))
            return false;
        return true;
    }

    /**
     * 获取运算符的优先级
     *
     * @param op
     * @return
     */
    public int getPriority(String op) {
        if(")".equals(op)){
            return 1;
        }
        else if ("+".equals(op) || "-".equals(op)) {
            return 2;
        }
        else if ("*".equals(op) || "/".equals(op)){
            return 3;
        }
        else if ("(".equals(op)){
            return 4;
        }
        return 999;
    }

    public double operation(double a, double b, String c){// 计算方法
        if("+".equals(c)){
            return a + b;
        }else if("-".equals(c)){
            return b - a;
        }else if("*".equals(c)){
            return a * b;
        }else if(a != 0&& "/".equals(c)){
            return b / a;
        }
        return -999;
    }

    public String[] convert2Postfix(String[] infix){
        int n = infix.length;
        String str = "";
        for(int i = 0; i < n; i++){
            if(isNumber(infix[i])){
                if(i+1<n&&isOperator(infix[i+1])||i+1==n){// 如果一个数字的后面是运算符
                    stackZ.push(infix[i]);
                }else{// 数字的情况
                    stackZ.push(infix[i]);
                }
            }else{// 运算符
                if(stackF.isEmpty() || infix[i]=="(" || getPriority(infix[i])>getPriority(stackF.peek())){
                    stackF.push(infix[i]);
                }else if(infix[i] == ")"){// 当是右括号的时候
                    while(stackF.peek() != "("){
                        stackZ.push(stackF.pop());
                    }
                    stackF.pop();
                }else{// 优先级低于栈顶运算符的时候
                    while(!stackF.isEmpty() && getPriority(stackF.peek()) >= getPriority(infix[i]) && stackF.peek() != "("){
                        stackZ.push(stackF.pop());
                    }
                    stackF.push(infix[i]);
                }
            }
        }
        while(!stackF.isEmpty()){
            stackZ.push(stackF.pop());
        }
        ArrayList<String> post = new ArrayList<String>();
        while(!stackZ.isEmpty()){
            post.add(stackZ.pop());
        }
        Collections.reverse(post);
        String[] postfix = post.toArray(new String[post.size()]);
        return postfix;
    }

    public double numberCalculate(String[] postfix){// 计算后缀表达式
        int n = postfix.length;
        double sum = 0;
        for(int i = 0; i < n; i++){
            if("+".equals(postfix[i]) || "-".equals(postfix[i]) || "*".equals(postfix[i]) || "/".equals(postfix[i])){// 如果是运算符，则弹出连个数字进行运算
                double num1 = stackN.pop();
                double num2 = stackN.pop();
                sum = operation(num1, num2, postfix[i]);
                stackN.push(sum);
            }
            else if(isNumber(postfix[i])){// 如果是数字
                stackN.push(Double.valueOf(postfix[i]));
            }
        }
        return stackN.peek();
    }

    public String printCalculateResult(){
        String str = "计算结果为：\t";
        int size=this.checkedword.size();
        String[] words = this.checkedword.toArray(new String[size]);
        String[] post = convert2Postfix(words);
        str += numberCalculate(post);
        return str;
    }
}

