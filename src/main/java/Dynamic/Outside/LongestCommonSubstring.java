package Dynamic.Outside;

public class LongestCommonSubstring {
    /*
     * https://www.youtube.com/watch?v=HrybPYpOvz0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=22
     */
    Integer[][] memoize;
    public int longestCommonSubstring(String text1, String text2) {
        memoize=new Integer[text1.length()][text2.length()];
        //return recursive(text1,text2,0,0);
        return topDown(text1,text2);
    }

    private int topDown(String text1, String text2) {
        int max=0;
        int[][] memoize=new int[text1.length()+1][text2.length()+1];
        for(int i=0;i< memoize.length;i++){
            for (int j=0;j<memoize[0].length;j++){
                if(i==0 || j==0) memoize[i][j]=0;
                else {
                    if(text1.charAt(i-1)==text2.charAt(j-1)){
                        memoize[i][j]=1+memoize[i-1][j-1];
                        if(max<memoize[i][j]) max=memoize[i][j];
                    }
                    else memoize[i][j]=0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubstring().longestCommonSubstring("abcde","abfce"));
    }
}
