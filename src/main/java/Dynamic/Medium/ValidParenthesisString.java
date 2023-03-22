package Dynamic.Medium;

import java.util.Stack;

public class ValidParenthesisString {
    Boolean[][] memoize;
    public boolean checkValidString(String s) {
        memoize=new Boolean[s.length()+1][s.length()+1];
        //return recursive(s,0,1);
        return greedyApproach(s);
    }


    /*
     * https://www.youtube.com/watch?v=KuE_Cn3xhxI
     * Approach: Greedy
     * Maintain Open and Star stacks. If a close parenthesis occurs then pop open stack else pop star stack else false.
     * After the completion of loop check if elements in the order if open stack elements occurs before star stack elements
     * else return false.
     * example: "*("
     * here if we just consider size of open stack and star stack it will show both are equal which is a wrong answer,
     * we need to check if '*' comes after the '(' or not.
     */
    //!Optimal
    private boolean greedyApproach(String s) {
        Stack<Integer> open=new Stack<>();
        Stack<Integer> star=new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(')
                open.push(i);
            else if(s.charAt(i)==')'){
                if(open.size()>0) open.pop();
                else if(star.size()>0)star.pop();
                else return false;
            }
            else star.push(i);
        }

        while (open.size()>0 && star.size()>0){
            int openElement=open.pop();
            int starElement=star.pop();
            if(starElement<openElement)
                return false;
        }
        if(open.size()>0) return false;
        return true;
    }

    /*
     * Approach:Memoized Recursion
     * if char is '(' increment open and move to next index.
     * if char is ')' if open>0 then decrement open and move to next index else return false.
     * if char is '*' there are 2 possibilities
     *      If open==0 then we can consider it as a open brace or a empty string
     *      Else we can consider it as open brace or close brace or empty string.
     */
    private boolean recursive(String s, int open, int index) {
        if(index>s.length()) return open==0;
        if(memoize[open][index]!=null) return memoize[open][index];

        if(s.charAt(index-1)=='*'){
            if(open==0) return memoize[open][index]=recursive(s,open+1,index+1)||recursive(s,open,index+1);
            return memoize[open][index]=recursive(s,open+1,index+1)||
                    recursive(s,open-1,index+1)||
                    recursive(s,open,index+1);
        }
        else if(s.charAt(index-1)=='(')
            return memoize[open][index]=recursive(s,open+1,index+1);
        else{
            if(open==0) return false;
            return memoize[open][index]=recursive(s,open-1,index+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new ValidParenthesisString().checkValidString("(*))"));
        System.out.println(new ValidParenthesisString().checkValidString("(*(**)"));
        System.out.println(new ValidParenthesisString().checkValidString(")*()"));
        System.out.println(new ValidParenthesisString().checkValidString("*("));
    }
}
