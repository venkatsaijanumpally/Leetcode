package Dynamic.Outside;

public class Longestrepeatingsubsequence {
    /*
     * https://www.youtube.com/watch?v=hbTaCmQGqLg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=30
     */
    public int longest(String s){
        int[][] memoize=new int[s.length()+1][s.length()+1];

        //(0,j) and (i,0) is 0 since if a string is empty the longest repeating subsequence we can get is 0
        for(int i=1;i<memoize.length;i++){
            for (int j=1;j<memoize.length;j++){
                if(s.charAt(i-1)==s.charAt(j-1) && i!=j)
                    memoize[i][j]=1+memoize[i-1][j-1];
                else memoize[i][j]=Math.max(memoize[i][j-1],memoize[i-1][j]);
            }
        }
        return memoize[s.length()][s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new Longestrepeatingsubsequence().longest("AABEBCDD"));
    }
}
