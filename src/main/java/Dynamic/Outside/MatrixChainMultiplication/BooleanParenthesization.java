package Dynamic.Outside.MatrixChainMultiplication;

import java.util.HashMap;

public class BooleanParenthesization {
    /*
     * https://www.youtube.com/watch?v=pGVguAcWX4g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=38
     * https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
     */
    HashMap<String,Integer> hm=new HashMap<>();
    public int count(char[] s){
        //return recursive(s,0,s.length-1,true);
        return recursiveMemoize(s,0,s.length-1,true);
    }

    private int recursive(char[] s, int start, int end, boolean isTrue) {
        if(start>end) return 0;
        if(start==end){
            if(isTrue)
                return s[start]=='T'?1:0;
            else return s[start]=='F'?1:0;
        }

        int count=0;
        for(int k=start+1;k<end;k+=2){
            int leftTrue=recursive(s,start,k-1,true);
            int leftFalse=recursive(s,start,k-1,false);
            int rightTrue=recursive(s,k+1,end,true);
            int rightFalse=recursive(s,k+1,end,false);

            if(s[k]=='^'){
                if(isTrue)
                    count+=leftTrue*rightFalse+leftFalse*rightTrue;
                else
                    count+=leftFalse*rightFalse+leftTrue*rightTrue;
            }
            else if(s[k]=='&'){
                if(isTrue)
                    count+=leftTrue*rightTrue;
                else
                    count+=leftFalse*rightFalse+leftFalse*rightTrue+leftTrue*rightFalse;
            }
            else {
                if(isTrue)
                    count+=+leftFalse*rightTrue+leftTrue*rightFalse+leftTrue*rightTrue;
                else
                    count+=leftFalse*rightFalse;
            }
        }
        return count;
    }

    /*
     * For memoization we used a map instead we can also use 3D array.
     */
    private int recursiveMemoize(char[] s, int start, int end, boolean isTrue){
        if(start>end) return 0;
        if(start==end){
            if(isTrue)
                return s[start]=='T'?1:0;
            else return s[start]=='F'?1:0;
        }
        String key= start +" "+ end +" "+ (isTrue ? 'T' : 'F');
        if(hm.containsKey(key)) return hm.get(key);

        int count=0;
        for(int k=start+1;k<end;k+=2){
            int leftTrue=recursive(s,start,k-1,true);
            int leftFalse=recursive(s,start,k-1,false);
            int rightTrue=recursive(s,k+1,end,true);
            int rightFalse=recursive(s,k+1,end,false);

            if(s[k]=='^'){
                if(isTrue)
                    count+=leftTrue*rightFalse+leftFalse*rightTrue;
                else
                    count+=leftFalse*rightFalse+leftTrue*rightTrue;
            }
            else if(s[k]=='&'){
                if(isTrue)
                    count+=leftTrue*rightTrue;
                else
                    count+=leftFalse*rightFalse+leftFalse*rightTrue+leftTrue*rightFalse;
            }
            else {
                if(isTrue)
                    count+=+leftFalse*rightTrue+leftTrue*rightFalse+leftTrue*rightTrue;
                else
                    count+=leftFalse*rightFalse;
            }
        }

        hm.put(key,count);
        return count;
    }


    private int findCount(char[] symbol, char[] operator, int index) {
        boolean left=symbol[index]=='T';
        boolean right=symbol[index+1]=='T';

        switch (operator[index]){
            case '^':return (left ^ right) ? 1:0;
            case '|':return (left | right) ? 1:0;
            default:return (left & right) ? 1:0;
        }
    }

    public static void main(String[] args) {
        char[] s={'T','^','F','&','T'};
        char[] s1={'T','|','T','&','F','^','T'};
        System.out.println(new BooleanParenthesization().count(s));
        System.out.println(new BooleanParenthesization().count(s1));
    }
}
