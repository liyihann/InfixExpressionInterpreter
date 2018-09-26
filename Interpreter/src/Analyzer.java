import java.util.ArrayList;

public class Analyzer {


    private String expression;



    private ArrayList<String> token;
    private ArrayList<Integer> definition;

    public Analyzer() {
        this.expression = "";
        this.token =new ArrayList<String>();
        this.definition=new ArrayList<Integer>();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public ArrayList<String> getToken() {
        return token;
    }


    public void setToken(){
        String str="";
        for (int i = 0;i<expression.length();i++) {
            if(expression.charAt(i)>47&&expression.charAt(i)<58){
                str+=expression.charAt(i);
            }
            else {
                this.token.add(str);
                token.add(String.valueOf(expression.charAt(i)));
                str="";
            }
        }
        this.token.add(str);
        for(int i = 0;i<token.size();i++){
            if(token.get(i)==""){
                token.remove(i);
            }
        }
    }

    public ArrayList<Integer> getDefinition() {
        return definition;
    }

    public void setDefinition() {
        for(int i = 0; i< token.size(); i++){
            definition.add(-1);
            if(token.get(i).charAt(0)>47&&token.get(i).charAt(0)<58){
                definition.set(i,1);//整数
            }
            if(token.get(i).charAt(0)==43||token.get(i).charAt(0)==42||token.get(i).charAt(0)==45||token.get(i).charAt(0)==47||token.get(i).charAt(0)==37||token.get(i).charAt(0)==94){
                definition.set(i,2);//算术操作符
            }
            if(token.get(i).charAt(0)==40||token.get(i).charAt(0)==41){
                definition.set(i,3);//开/闭括号
            }
        }
    }
    public String printAnalysis(){
        String str = "";
        for(int i = 0;i<token.size();i++){
            str+=token.get(i)+":";
            switch (definition.get(i)){
                case 1:
                    str+="整数";
                    break;
                case 2:
                    str+="算术操作符";
                    break;
                case 3:
                    str+="括号";
                    break;
                default:
                    str+="未识别";
                    break;
            }
            str+="\n";
        }
        return str;

    }


}
