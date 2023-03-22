package Dynamic.LongestPalindromicSubsequence;

import java.util.Arrays;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        //TODO not yet working
        String str2=new StringBuilder(s).reverse().toString();
        int[][] memoize=new int[s.length()+1][s.length()+1];
        int max=1;
        int maxi=1,maxj=str2.length();

        for(int i=1;i<memoize.length;i++){
            for (int j=1;j<memoize.length;j++){
                if(s.charAt(i-1)==str2.charAt(j-1)){
                    memoize[i][j]=1+memoize[i-1][j-1];
                    if(max<memoize[i][j]) {
                        max=memoize[i][j];
                        maxi=i;
                        maxj=j;
                    }
                }
                else memoize[i][j]=0;
            }
        }
        System.out.println("Max:"+max);
        for(int i=0;i<memoize.length;i++){
            for (int j=0;j< memoize.length;j++)
                System.out.print(memoize[i][j]+" ");
            System.out.println();
        }

        StringBuilder res=new StringBuilder();
        while (maxi>0 && maxj>0){
            if(s.charAt(maxi-1)==str2.charAt(maxj-1)){
                res.append(s.charAt(maxi-1));
                maxi--;
                maxj--;
            }
            else break;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("ac"));
    }
}
