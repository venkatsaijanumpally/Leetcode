package Dynamic.Medium;

import java.util.Arrays;

public class DecodeWays {
    Integer[][] memoize;
    public int numDecodings(String s) {
        memoize=new Integer[s.length()+1][s.length()+1];
        /*for (int[] arr:memoize)
            Arrays.fill(arr,-1);*/
        return recursive(s,1,1);
    }

    /*
     * Approach: Memoized Recursion
     * Rules:
     *      - If char at prev index is ZERO then it is invalid combination
     *      - If the number generated from prev to index goes beyond 26 then it is invalid.
     *      - At a index there are 2 possibilities,
     *          1. Take the number generated from prev to index and move to next index
     *          2. Skip the index
     */
    private int recursive(String sb, int index, int prev) {
        if(index>sb.length()){
            if(prev==index) return 1;
            return 0;
        }
        if(memoize[index][prev]!=null) return memoize[index][prev];
        if(sb.charAt(prev-1)=='0') return memoize[index][prev]=0;

        int num=Integer.parseInt(sb.substring(prev-1,index));
        if(num>26) return memoize[index][prev]=0;

        return memoize[index][prev]=recursive(sb,index+1,index+1)+recursive(sb,index+1,prev);
    }


    public static void main(String[] args){
        System.out.println(new DecodeWays().numDecodings("12"));
        System.out.println(new DecodeWays().numDecodings("226"));
        System.out.println(new DecodeWays().numDecodings("06"));
    }
}
