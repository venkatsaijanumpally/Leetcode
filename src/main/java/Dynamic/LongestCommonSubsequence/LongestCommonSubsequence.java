package Dynamic.LongestCommonSubsequence;

public class LongestCommonSubsequence {
    /*
     * https://www.youtube.com/watch?v=hR3s9rGlMTU&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=21
     * Both solutions recursiveBottomUp and topDown are optimal
     */
    Integer[][] memoize;
    public int longestCommonSubsequence(String text1, String text2) {
        memoize=new Integer[text1.length()][text2.length()];
        //return recursive(text1,text2,0,0);
        //return recursiveBottomUp(text1,text2,text1.length()-1,text2.length()-1);
        return topDown(text1,text2);
    }

    private int recursive(String text1, String text2, int index1, int index2) {
        if(index1>=text1.length() || index2>=text2.length()) return 0;
        if(text1.charAt(index1)==text2.charAt(index2))
            return 1+recursive(text1,text2,index1+1,index2+1);
        else return Math.max(recursive(text1,text2,index1+1,index2),recursive(text1,text2,index1,index2+1));
    }

    private int recursiveBottomUp(String text1, String text2, int index1, int index2) {
        if(index1==-1 || index2==-1) return 0;
        if(memoize[index1][index2]!=null) return memoize[index1][index2];
        if(text1.charAt(index1)==text2.charAt(index2))
            return memoize[index1][index2]=1+recursiveBottomUp(text1,text2,index1-1,index2-1);
        else return memoize[index1][index2]=Math.max(recursiveBottomUp(text1,text2,index1-1,index2),
                recursiveBottomUp(text1,text2,index1,index2-1));
    }

    private int topDown(String text1, String text2) {
        int[][] memoize=new int[text1.length()+1][text2.length()+1];

        //i==0 || j==0 is initialization.
        // If i=0,j=anything then (i,j)=0 and if j=0,i=anything then (i,j)=0
        //The logic of base condition is - If one string is empty and other is anything then the longest common
        // subsequence is 0.
        //We can even start the loop from i=1 and j=1 since default value of elements in int array is ZERO
        for(int i=0;i< memoize.length;i++){
            for (int j=0;j<memoize[0].length;j++){
                if(i==0 || j==0) memoize[i][j]=0;
                else {
                    if(text1.charAt(i-1)==text2.charAt(j-1))
                        memoize[i][j]=1+memoize[i-1][j-1];
                    else memoize[i][j]=Math.max(memoize[i][j-1],memoize[i-1][j]);
                }
            }
        }
        return memoize[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("abcde","ace"));
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("abc","abc"));
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("abc","def"));
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("abcdgh","abedfhr"));
    }
}
