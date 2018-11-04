import java.util.*;

public class Calculator {

    private static Stack<Integer> stackN = new Stack<Integer>();// 数字
    private static Stack<Character> stackF = new Stack<Character>();// 符号
    private static Stack<Character> stackZ = new Stack<Character>();// 中间

    public static char[] getCh() {
        return ch;
    }

    public static void setCh(char[] ch) {
        Calculator.ch = ch;
    }

    private static char [] ch;
    private static char [] ch1;
    private static int [] num = new int[10];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        ch = str.toCharArray();
        //使用方法
        int result = numberCalculate(convert2Postfix(ch));
        System.out.println(result);
        in.close();
    }


    public static int priority(char c){//优先级
        if(c == ')'){
            return 1;
        }else if(c == '+' || c == '-'){
            return 2;
        }else if(c == '*' || c == '/'){
            return 3;
        }else if(c == '('){
            return 4;
        }
        return 999;
    }

    public static int operation(int a, int b, char c){// 计算方法
        if(c == '+'){
            return a + b;
        }else if(c == '-'){
            return b - a;
        }else if(c == '*'){
            return a * b;
        }else if(c == '/' && a != 0){
            return b / a;
        }
        return -1;
    }

    public static char[] convert2Postfix(char [] ch){
        int n = ch.length;
        String str = "";
        for(int i = 0; i < n; i++){
            if(ch[i] >= '0' && ch[i] <= '9'){// 数字
                if(i + 1 < n && (ch[i+1] < '0' || ch[i+1] > '9') || i + 1 == n){// 如果一个数字的后面是运算符
                    stackZ.push(ch[i]);
                    stackZ.push('#');
                }else{// 数字的情况
                    stackZ.push(ch[i]);
                }
            }else{// 运算符
                if(stackF.isEmpty() || ch[i]=='(' || priority(ch[i]) > priority(stackF.peek())){
                    stackF.push(ch[i]);
                }else if(ch[i] == ')'){// 当是右括号的时候
                    while(stackF.peek() != '('){
                        stackZ.push(stackF.pop());
                    }
                    stackF.pop();
                }else{// 优先级低于栈顶运算符的时候
                    while(!stackF.isEmpty() && priority(ch[i]) <= priority(stackF.peek()) && stackF.peek() != '('){
                        stackZ.push(stackF.pop());
                    }
                    stackF.push(ch[i]);
                }
            }
        }
        while(!stackF.isEmpty()){
            stackZ.push(stackF.pop());
        }
        while(!stackZ.isEmpty()){
            str += stackZ.pop()+"";
        }
        ch1 = str.toCharArray();
        int a = ch1.length;
        for(int i = 0; i < a/2; i++){//  ch1反转
            char t;
            t = ch1[i];
            ch1[i] = ch1[a-1-i];
            ch1[a-1-i] = t;
        }
        return ch1;
    }


    public static int numberCalculate(char [] ch){// 计算后缀表达式
        int n = ch.length;
        int sum = 0;
        int k = 0;
        int tmp = 0;
        for(int i = 0; i < n; i++){
            if(ch[i] == '#'){
                continue;
            }else if(ch[i] == '+' || ch[i] == '-' || ch[i] == '*' || ch[i] == '/'){// 如果是运算符，则弹出连个数字进行运算
                sum = operation(stackN.pop(), stackN.pop(), ch[i]);
                stackN.push(sum);
            }else{// 如果是数字
                if(ch[i+1] == '#'){// 如果下一个是‘#’
                    num[k++] = ch[i] - '0';
                    for(int j = 0; j < k; j++){
                        tmp += (num[j] * (int)Math.pow(10, k-j-1));
                    }
                    stackN.push(tmp);
                    tmp = 0;
                    k = 0;
                }else{// 下一个元素是数字
                    num[k++] = ch[i] - '0';
                }
            }
        }
        return stackN.peek();
    }
}

