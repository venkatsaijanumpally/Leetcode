package Dynamic.Medium;

public class DeleteOperationforTwoStrings {
    public int minDistance(String word1, String word2) {
        int[][] memoize=new int[word1.length()+1][word2.length()+1];

        for(int i=1;i<memoize.length;i++){
            for(int j=1;j<memoize[0].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    memoize[i][j]=1+memoize[i-1][j-1];
                else memoize[i][j]=Math.max(memoize[i][j-1],memoize[i-1][j]);
            }
        }
        return word1.length()+word2.length()-2*memoize[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        System.out.println(new DeleteOperationforTwoStrings().minDistance("sea","eat"));
        System.out.println(new DeleteOperationforTwoStrings().minDistance("leetcode","etco"));
    }
}
