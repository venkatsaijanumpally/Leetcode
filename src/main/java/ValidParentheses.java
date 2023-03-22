import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class ValidParentheses {
    public static boolean valid(String s){
        /*Map<Character,Character> map=new HashMap<Character, Character>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');*/
        Stack<Character> stack = new Stack<Character>();
        /*for(int i=0;i<s.length();i++){
            if(Pattern.matches("[({\\[]?",String.valueOf(s.charAt(i))))
                stack.push(s.charAt(i));
            else if(!stack.empty() && stack.peek()==map.get(s.charAt(i)))
                stack.pop();
            else
                return false;
        }*/
        for(char c : s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||stack.pop()!=c)
                return false;
        }
        return stack.empty();
    }
    public static void main(String args[]){
        System.out.println(valid("{}"));
    }
}
