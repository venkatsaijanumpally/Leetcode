package Dynamic.LongestPalindromicSubsequence;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        String str2=new StringBuilder(s).reverse().toString();
        return recursion(s,str2);
    }

    private int recursion(String str1, String str2) {
        int[][] memoize=new int[str1.length()+1][str2.length()+1];
        for(int i=1;i<memoize.length;i++){
            for(int j=1;j<memoize[0].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1))
                    memoize[i][j]=1+memoize[i-1][j-1];
                else memoize[i][j]=memoize[i-1][j]+memoize[i][j-1];
            }
        }
        return memoize[str1.length()][str2.length()];
    }

    public static void main(String[] args) {
        System.out.println(new PalindromicSubstrings().countSubstrings("aaa"));
    }
}
