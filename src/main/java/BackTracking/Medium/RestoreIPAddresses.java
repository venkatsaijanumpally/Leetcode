package BackTracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {

    List<String> result=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        recursion(s,3, new StringBuilder(),0);
        return result;
    }

    private void recursion(String s, int dotsLeft, StringBuilder sb, int start) {
        if(dotsLeft==0){
            if(s.length()-start>3 || (s.length()-start>1 && s.charAt(start)=='0') || notValidIPBit(s.substring(start))) return;
            sb.append(s.substring(start));
            result.add(sb.toString());
            sb.delete(sb.length()-(s.length()-start),sb.length());
            return;
        }

        StringBuilder temp;
        for(int i=start;i<s.length()-dotsLeft && i-start<3;i++){
            if(i-start>0 && s.charAt(start)=='0') return;//If the bit has 0 as starting digit
            temp=new StringBuilder(s.substring(start,i+1));
            if(notValidIPBit(temp))//Is the bit in range
                return;
            sb.append(temp).append('.');
            recursion(s,dotsLeft-1,sb,i+1);
            sb.delete(sb.length()-temp.length()-1,sb.length());
        }
    }

    private boolean notValidIPBit(String substring) {
        int bit=Integer.parseInt(substring);
        if(bit>255)
            return true;
        return false;
    }

    private boolean notValidIPBit(StringBuilder sb) {
        int bit=Integer.parseInt(sb.toString());
        if(bit>255)
            return true;
        return false;
    }

    public static void main(String[] args) {
        String s="25525511135";
        //System.out.println(new RestoreIPAddresses().restoreIpAddresses(s));
        System.out.println(new RestoreIPAddresses().restoreIpAddresses("0000"));
    }
}
