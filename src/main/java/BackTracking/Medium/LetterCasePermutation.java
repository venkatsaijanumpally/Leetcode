package BackTracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    List<String> result=new ArrayList<>();
    public List<String> letterCasePermutation(String s) {
        recursion(s,0,new StringBuilder());
        return result;
    }

    private void recursion(String s, int index, StringBuilder sb) {
        if(index==s.length()){
            result.add(sb.toString());
            return;
        }

        if(Character.isDigit(s.charAt(index))){
            sb.append(s.charAt(index));
            recursion(s,index+1,sb);
            sb.delete(sb.length()-1,sb.length());
        }
        else {
            sb.append(Character.toUpperCase(s.charAt(index)));
            recursion(s,index+1,sb);
            sb.delete(sb.length()-1,sb.length());

            sb.append(Character.toLowerCase(s.charAt(index)));
            recursion(s,index+1,sb);
            sb.delete(sb.length()-1,sb.length());
        }
    }

    public static void main(String[] args) {
        String s1="a1b2";
        System.out.println(new LetterCasePermutation().letterCasePermutation(s1));
        System.out.println(new LetterCasePermutation().letterCasePermutation("C"));
    }
}
