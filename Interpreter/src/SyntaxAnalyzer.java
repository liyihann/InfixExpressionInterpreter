import java.util.ArrayList;

public class SyntaxAnalyzer {
    private ArrayList<String> word;
    private ArrayList<Integer> value;
    private boolean isSyntaxError;

    public SyntaxAnalyzer(){}

    public SyntaxAnalyzer(Analyzer a) {
        this.value = a.getCheckedvalue();
        this.word = a.getCheckedword();
        this.isSyntaxError = false;
    }

    public ArrayList<String> getWord() {
        return word;
    }

    public ArrayList<Integer> getValue() {
        return value;
    }

    public boolean isSyntaxError() {
        return isSyntaxError;
    }

    //文法：E->E+T|E-T|T  T->T*F|T/F|F F->(E)|d
    //语法分析
    int idex=0;
    int symbol;
    int error=0;
    String syntaxAnalysis = "";

    //获得下一个token的种类编码
    public void Next(){
        if(idex<this.value.size()){
            symbol=this.value.get(idex);
            idex++;
        }
        else{
            symbol=0;
        }

    }
    // E->T{+T|-T}
    public void E(){
        syntaxAnalysis+="E->T";
        T();
        while(symbol==5||symbol==6){
            if(symbol==5){
                syntaxAnalysis+="\nE->T+T";
            }
            if(symbol==6){
                syntaxAnalysis+="\nE->T-T";
            }
            Next();
            T();

        }
    }
    //T->F{*F|/F}
    public void T(){
        syntaxAnalysis+="\nT->F";
        F();
        while(symbol==7||symbol==8){
            if(symbol==7){
                syntaxAnalysis+="\nT->F*F";
            }
            if(symbol==8){
                syntaxAnalysis+="\nT->F/F";
            }
            Next();
            F();
        }
    }
    //F->(E)|d
    public void F(){
        if(symbol==1||symbol==2){
            syntaxAnalysis+="\nF->d:"+this.word.get(idex-1);
            if(idex<this.value.size()){
                syntaxAnalysis+="\n匹配终结符d";
            }
            else{
                syntaxAnalysis+="\n读取完毕";
            }

            Next();

        }
        else if(symbol==3){
            syntaxAnalysis+="\nF->(E)\n";
            Next();
            E();
            if(symbol==4){
                Next();
            }
            else{
                error=-1;
                syntaxAnalysis+="\n【提示：第"+(idex)+"词法单元错误】";
                syntaxAnalysis+="\n【提示：括号有误，请检查输入】";
            }
        }
        else{
            error=-1;
            syntaxAnalysis+="\n【提示：第"+(idex)+"词法单元错误】";
            syntaxAnalysis+="\n【提示：操作符或操作数格式有误，请检查输入】";
        }

    }
    public String printSyntaxAnalysis(){
        String str = "-----语法检查开始-----\n";
        Next();
        E();
        str+=syntaxAnalysis;
        if(symbol==0&&error==0){
            str+="\n-----语法检查正确-----\n\n";
        }
        else{
            if(symbol!=0){
                str=str+"\n【提示：第"+idex+"词法单元错误】";
                str+="\n【提示：异常符号，请检查输入】";
                isSyntaxError = true;
            }
            str+="\n【提示：语法检查错误，无法继续分析。请检查输入】\n";
        }
        return str;

    }

}
