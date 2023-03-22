package Dynamic.Medium;

public class LengthofLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] arr) {
        /*int[][] memoize=new int[arr.length()+1][text2.length()+1];
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
        return memoize[text1.length()][text2.length()];*/
        return 1;
    }
}
