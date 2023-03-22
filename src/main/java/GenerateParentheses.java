import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static void main(String[] args) throws IOException {
        System.out.println(generateParenthesesCaller(3));
    }

    static List<String> result=new ArrayList<>();
    //static int open=0,close=0;
    private static List<String> generateParenthesesCaller(int n) {
        generateParentheses(n,"", 0, 0);
        return result;
    }
    private static void generateParentheses(int n, String s, int open, int close){
        if(open==n){
            if(close==n){
                if(s.length()>0)
                    result.add(s);
                return;
            }
            else{
                generateParentheses(n, s+")", open, close+1);
                return;
            }
        }
        if(open==close){
            generateParentheses(n, s+"(", open+1, close);
            return;
        }
        generateParentheses(n, s+")", open, close+1);
        generateParentheses(n, s+"(", open+1, close);
    }
}
