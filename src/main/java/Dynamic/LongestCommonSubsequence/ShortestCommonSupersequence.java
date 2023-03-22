package Dynamic.LongestCommonSubsequence;

public class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        return topDown(str1,str2);
    }

    private String topDown(String str1, String str2) {
        int[][] memoize=new int[str1.length()+1][str2.length()+1];
        for(int i=0;i<memoize[0].length;i++)
            memoize[0][i]=0;
        for (int i=1;i<memoize.length;i++)
            memoize[i][0]=0;
        for (int i=1;i<memoize.length;i++){
            for(int j=1;j<memoize[0].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1))
                    memoize[i][j]=1+memoize[i-1][j-1];
                else memoize[i][j]=Math.max(memoize[i-1][j],memoize[i][j-1]);
            }
        }

        //Print array
        for(int i=0;i< memoize.length;i++){
            for (int j=0;j<memoize[0].length;j++)
                System.out.print(memoize[i][j]+" ");
            System.out.println();
        }

        /*
         * The way we are generating the string is similar to printingLongestcommonsubsequence, refer for details.
         * In printingLongestcommonsubsequence we only append when the charAt(i)==char(j) but here
         * If the charAt(i)==charAt(j) append to string
         * Else we need to move LEFT or UP. If we move LEFT then we append the value on the UP i.e charAt(j). If we move
         * UP we append the value on the LEFT i.e charAT(i).
         * The logic here is whatever the chars that are not there in the LCS will get inserted in the sequence in else
         * condition.
         *
         * Example for "AGGTAB","GXTXAYB"
         *          LCS - "GTAB"
         *
         *           A is inserted at beginning
         *          /
         *          A G G T   A   B
         *            G X T X A Y B
         *              /    \   \
         *             /      \   Y is inserted between A and B
         *            /         X is inserted between T and A
         *          (G,X) is inserted between G and T (Order can be GX or XG anything is valid)
         *
         */
        int i= str1.length(),j=str2.length();
        StringBuilder res=new StringBuilder();
        while (i>0 && j>0){
            if(str1.charAt(i-1)==str2.charAt(j-1)){
                res.append(str1.charAt(i-1));
                i--;
                j--;
            }
            else {
                if(memoize[i][j-1]>memoize[i-1][j]){
                    res.append(str2.charAt(j-1));
                    j--;
                }
                else {
                    res.append(str1.charAt(i-1));
                    i--;
                }
            }
        }
        while (i>0){
            res.append(str1.charAt(i-1));
            --i;
        }
        while (j>0){
            res.append(str2.charAt(j-1));
            --j;
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ShortestCommonSupersequence().shortestCommonSupersequence("AGGTAB","GXTXAYB"));
    }
}
