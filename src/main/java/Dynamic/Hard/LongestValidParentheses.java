package Dynamic.Hard;

import java.util.Arrays;
import java.util.Stack;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int[] nullifiedItemsBetween=new int[s.length()];
        Stack<Integer> stack=new Stack<>();

        int out=0;
        int max=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.push(i);
            }
            else {
                if(!stack.isEmpty()){
                    int len=i-stack.pop()+1;
                    if(!stack.isEmpty()){
                        nullifiedItemsBetween[stack.peek()]+=len;
                        max=Math.max(nullifiedItemsBetween[stack.peek()],max);
                    }
                    else {
                        out+=len;
                        max=Math.max(out,max);
                    }
                }
                else {
                    out=0;
                }
            }
        }
        System.out.println(Arrays.toString(nullifiedItemsBetween));
        return max;
    }

    public static void main(String[] args) {
        /*System.out.println(new LongestValidParentheses().longestValidParentheses(")()())"));
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()"));
        System.out.println(new LongestValidParentheses().longestValidParentheses(""));
        System.out.println(new LongestValidParentheses().longestValidParentheses("()(()"));
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()()"));
        System.out.println(new LongestValidParentheses().longestValidParentheses(")())()(()))"));*/
        System.out.println(new LongestValidParentheses().longestValidParentheses("(((((()())()()))()"));
        System.out.println(new LongestValidParentheses().longestValidParentheses("(())"));
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()))())("));
        System.out.println(new LongestValidParentheses().longestValidParentheses("(()"));
    }
}
