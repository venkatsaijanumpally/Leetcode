package Dynamic.Outside;

public class MinimumNumberofInsertionandDeletiontoconvertStringatoStringb {
    public void minInsertionandDeletion(String str1, String str2){
        int lcs=longestCommonSubsequence(str1,str2);
        System.out.println("Longest Common Subsequence length:"+lcs);
        System.out.println("Number of Deletions:"+(str1.length()-lcs));
        System.out.println("Number of insertions:"+(str2.length()-lcs));
    }

    //Same LCS approach
    private int longestCommonSubsequence(String str1, String str2) {
        int[][] memoize=new int[str1.length()+1][str2.length()+1];

        for(int i=1;i<memoize.length;i++){
            for(int j=1;j<memoize[0].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1))
                    memoize[i][j]=1+memoize[i-1][j-1];
                else memoize[i][j]=Math.max(memoize[i-1][j],memoize[i][j-1]);
            }
        }
        return memoize[str1.length()][str2.length()];
    }

    public static void main(String[] args) {
        new MinimumNumberofInsertionandDeletiontoconvertStringatoStringb().minInsertionandDeletion("heap","pea");
    }
}
