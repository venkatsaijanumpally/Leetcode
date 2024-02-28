package Dynamic.Medium;

import java.util.Arrays;

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int[][] memoize = new int[t.length()+1][s.length()+1];

        for (int i=0;i<s.length();i++) memoize[0][i]=1;

        for (int i=1;i<=t.length();i++){
            for (int j=1;j<=s.length();j++){
                if(t.charAt(i-1)==s.charAt(j-1)){
                    memoize[i][j]=memoize[i-1][j-1]+memoize[i][j-1];
                }
                else {
                    memoize[i][j] = memoize[i][j-1];
                }
            }
        }
        for (int[] arr: memoize){
            System.out.println(Arrays.toString(arr));
        }
        return memoize[t.length()][s.length()];
    }

    public int numDistinct2(String s, String t) {
        return recur(s,t,s.length()-1,t.length()-1);
    }

    private int recur(String s, String t, int i, int j) {
        if(j==-1)
            return 1;
        if(i==-1)
            return 0;

        if(s.charAt(i)==t.charAt(j)){
            return recur(s,t,i-1,j-1)+recur(s,t,i-1,j);
        }
        else return recur(s,t,i-1,j);
    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubsequences().numDistinct("rabbbit","rabbit"));;
        System.out.println(new DistinctSubsequences().numDistinct2("rabbbit","rabbit"));;
    }
}
