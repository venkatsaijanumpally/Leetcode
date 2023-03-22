package Dynamic.Outside;

public class PrintingLongestcommonsubsequence {
    /*
     * This problem is similar to LongestCommonSubsequence.
     * https://www.youtube.com/watch?v=x5hQvnUcjiM&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=23
     */

    public String printingLongestcommonsubsequence(String text1, String text2) {
        return topDownUsingCount(text1,text2);
        //return topDown(text1,text2);
        //return recursive(text1,text2,text1.length()-1,text2.length()-1).toString();
    }

    //!Optimal Solution
    /*
     * The solution topDown and topDownUsingCount both are good but the drawback with topDown solution is we consume
     * more memory due to stringbuilder array. Here we use the same approach as of Longest common subsequence and
     * generate the memoized integer array and one additional step is to generate the string out of the memoized array.
     * The way we are generating string is the same logic how we are saving the data in memoized array for reference see
     * the video.
     */
    private String topDownUsingCount(String text1, String text2) {
        int[][] memoize=new int[text1.length()+1][text2.length()+1];
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

        int i=text1.length(),j=text2.length();
        StringBuilder res=new StringBuilder();
        //Generate String
        while (i>0&&j>0){
            if(text1.charAt(i-1)==text2.charAt(j-1)){
                res.append(text1.charAt(i-1));
                j--;
                i--;
            }
            else {
                if(memoize[i-1][j]>memoize[i][j-1])
                    i--;
                else j--;
            }
        }
        return res.reverse().toString();
    }

    /*
     * In this approach as we have seen in LongestCommonSubsequence we save strings in the memoized array instead
     * of the count.
     */
    private String topDown(String text1, String text2) {
        StringBuilder[][] memoize;
        memoize=new StringBuilder[text1.length()+1][text2.length()+1];
        for(int i=0;i<memoize[0].length;i++)
            memoize[0][i]=new StringBuilder();
        for(int i=0;i<memoize.length;i++)
            memoize[i][0]=new StringBuilder();
        for(int i=1;i<memoize.length;i++){
            for(int j=1;j<memoize[0].length;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1))
                    memoize[i][j]=new StringBuilder(memoize[i-1][j-1]).append(text1.charAt(i-1));
                else {
                    StringBuilder s1=memoize[i-1][j];
                    StringBuilder s2=memoize[i][j-1];
                    memoize[i][j]=s1.length()>s2.length()?s1:s2;
                }
            }
        }
        return memoize[text1.length()][text2.length()].toString();
    }

    private StringBuilder recursive(String text1, String text2, int index1, int index2) {
        if(index1==-1 || index2==-1) return new StringBuilder();

        if(text1.charAt(index1)==text2.charAt(index2))
            return new StringBuilder(recursive(text1,text2,index1-1,index2-1)).append(text1.charAt(index1));

        StringBuilder s1=recursive(text1,text2,index1-1,index2);
        StringBuilder s2=recursive(text1,text2,index1,index2-1);
        return s1.length()>s2.length()?s1:s2;
    }

    public static void main(String[] args) {
        System.out.println(new PrintingLongestcommonsubsequence().printingLongestcommonsubsequence("acbcf","abcdaf"));
    }
}
