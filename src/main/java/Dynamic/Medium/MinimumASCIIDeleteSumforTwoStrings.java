package Dynamic.Medium;

public class MinimumASCIIDeleteSumforTwoStrings {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] t=new int[s1.length()+1][s2.length()+1];
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    t[i][j]=(int)s1.charAt(i-1)+t[i-1][j-1];
                }
                else
                    t[i][j]=Math.max(t[i][j-1],t[i-1][j]);
            }
        }
        int sum1=0;
        int sum2=0;
        for(int i=0;i<s1.length();i++)
            sum1+=s1.charAt(i);
        for(int i=0;i<s2.length();i++)
            sum2+=s2.charAt(i);
        return sum1+sum2-2*t[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        System.out.println(new MinimumASCIIDeleteSumforTwoStrings().minimumDeleteSum("sea","eat"));
        System.out.println(new MinimumASCIIDeleteSumforTwoStrings().minimumDeleteSum("delete","leet"));
    }
}
