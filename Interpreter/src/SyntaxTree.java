import java.util.*;

public class SyntaxTree {
    private ArrayList<String> checkedword;

    public SyntaxTree(){}

    public SyntaxTree(SyntaxAnalyzer s) {
        this.checkedword = s.getWord();
    }

    static class TreeNode {
        TreeNode leftChild;
        TreeNode rightChild;
        String value;
    }

    /**
     * 将算术表达式转化成二叉树
     *
     * @param words
     *            为了方便，使用字符串数组来存储表达式
     * @return 二叉树的根节点
     */
    public TreeNode createBinaryTree(String[] words) {

        // 存储操作数的栈
        Stack<String> opStack = new Stack<String>();
        // 存储转换后的逆波兰式的队列
        Queue<String> reversePolish = new LinkedList<String>();

        for (String s : words) {

            // 如果是数字
            if(isDigit(s)){

                reversePolish.offer(s);
                // 如果是操作符
            } else if(isOperator(s)){

                //是左括号直接入栈
                if("(".equals(s)){

                    opStack.push(s);
                    // 如果是右括号
                } else if(")".equals(s)){

                    // 把离上一个“（”之间的操作符全部弹出来放入逆波兰式的队列中
                    while(!opStack.isEmpty()){

                        String op = opStack.peek();
                        if(op.equals("(")){

                            opStack.pop();
                            break;

                        } else{

                            reversePolish.offer(opStack.pop());
                        }
                    }
                } else{

                    while(!opStack.isEmpty()){
                        // 如果栈顶元素为"("直接入栈
                        if("(".equals(opStack.peek())){

                            opStack.push(s);
                            break;
                            //如果栈顶元素优先级大于s
                        }else if(isGreater(opStack.peek(), s)){

                            reversePolish.offer(opStack.pop());

                        }else if(isGreater(s, opStack.peek())){

                            opStack.push(s);
                            break;
                        }
                    }
                    // 如果栈为空，直接入栈
                    if(opStack.isEmpty())

                        opStack.push(s);
                }
            }
        }
        // 将剩余的操作符入队
        while(!opStack.isEmpty()){

            reversePolish.offer(opStack.pop());
        }
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        // 将逆波兰式转化成二叉树
        while(!reversePolish.isEmpty()){

            String s = reversePolish.poll();
            // 以当前的元素的值新建一个节点
            TreeNode node = new TreeNode();
            node.value = s;
            // 如果是数字
            if(isDigit(s)){

                nodeStack.push(node);
                // 如果是操作符
            } else if(isOperator(s)){

                //从栈里弹出两个节点作为当前节点的左右子节点
                TreeNode rightNode = nodeStack.pop();
                TreeNode leftNode = nodeStack.pop();
                node.leftChild = leftNode;
                node.rightChild = rightNode;

                // 入栈
                nodeStack.push(node);
            }

        }
        return nodeStack.pop();
    }

    /**
     * 判断是否为运算符
     *
     * @param s
     * @return
     */
    public boolean isOperator(String s) {

        if ("(".equals(s) || ")".equals(s) || "+".equals(s) || "-".equals(s)
                || "*".equals(s) || "/".equals(s)||"%".equals(s)||"^".equals(s))

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
    public boolean isDigit(String s) {

        if(isOperator(s))
            return false;

        return true;
    }

    /**
     * 判断op1和op2的优先级，如果op1>=op2，返回true，如果op1<op2，返回false
     *
     * @param op1
     * @param op2
     * @return
     */
    public boolean isGreater(String op1, String op2) {
        if (getPriority(op1) >= getPriority(op2))
            return true;
        else
            return false;
    }

    /**
     * 获取运算符的优先级
     *
     * @param op
     * @return
     */
    public int getPriority(String op) {
        if ("+".equals(op) || "-".equals(op))
            return 1;
        else if ("*".equals(op) || "/".equals(op))
            return 2;
        else
            throw new IllegalArgumentException("Unsupported operator!");
    }

    /**
     * 打印出还原的算术表达式
     * @param root
     */
    public String printMathExpression(TreeNode root){
        String str = "";
        if(root != null){
            if(isOperator(root.value)){
                str+="(";
            }
            str+=printMathExpression(root.leftChild);
            str+=root.value;
            str+=printMathExpression(root.rightChild);
            if(isOperator(root.value)){
                str+=")";
            }
        }
        return str;
    }

    //层序遍历二叉树
    public String levelOrder(TreeNode Node) {
        String str = "";
        if (Node == null) {
            return str;
        }
        int depth = depth(Node);
        for (int i = 1; i <= depth; i++) {
            str+="Line"+i+":    ";
            for(int j = 0;j<(Math.pow(2,depth-i)-1);j++){
                str+="  ";
            }
            str+=levelOrder(Node, i, depth,i);
            str+="\n";
        }
        return str;
    }

    public String levelOrder(TreeNode Node, int level,int depth,int curLevel) {
        String str = "";
        if (level < 1) {
            return str;
        }
        if(Node == null){
            str+="#";
//            System.out.println("Nodevalue:#,Level:"+level+",curlevel:"+curLevel);
            for(int j = 0;j<(Math.pow(2,(depth+1-curLevel))-1);j++){
                str+="  ";
            }
            while(level>1){
                str+="#";
                for(int j = 0;j<(Math.pow(2,(depth+1-curLevel))-1);j++){
                    str+="  ";
                }
                for(int t = 2;t<=depth;t++){
                    System.out.println("t:"+t+",Level:"+level+",curlevel:"+curLevel);
                    if(level>t) {
                        str += "#";
                        System.out.println("Nodevalue:#,Level:" + level + ",curlevel:" + curLevel);
                        for (int j = 0; j < (Math.pow(2, (depth + 1 - curLevel)) - 1); j++) {
                            str += "  ";
                        }
                    }
                }
                level--;
            }
            return str;
        }
        if (level == 1) {
//            System.out.println("Nodevalue:"+Node.value+",Level"+level+",curlevel:"+curLevel);
            str+=Node.value;
            for(int j = 0;j<(Math.pow(2,(depth+1-curLevel))-1);j++){
                str+="  ";
            }
            return str;
        }
        // 左子树
        str += levelOrder(Node.leftChild, level - 1, depth, curLevel);
        // 右子树
        str += levelOrder(Node.rightChild,level - 1, depth, curLevel);

        return str;
    }

    public int depth(TreeNode Node) {
        if (Node == null) {
            return 0;
        }
        int l = depth(Node.leftChild);
        int r = depth(Node.rightChild);
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    public String printSyntaxTree(){
        String str = "-----打印语法树-----\n";
        int size=this.checkedword.size();
        String[] words = this.checkedword.toArray(new String[size]);
        TreeNode root = createBinaryTree(words);
        str+="使用括号表示：\n";
        str+=printMathExpression(root);
        str+="\n\n";
        str+="使用树形表示：\n";
        str+=levelOrder(root);
        str+= "-----语法树打印完成-----\n";

        return str;
    }


}
