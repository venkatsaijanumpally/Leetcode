package Dynamic.Medium;

import java.util.Arrays;

public class PerfectSquares {

    /*
     * Observe the 3 dynamic programming variations correctly.
     * "numSquares" function is regular method we find min least number of perfect square numbers that sum to n by trying
     * to take different perfect squares from 1 to size plus min of remaining value
     *
     * Difference between "numSquares3" and "numSquares4" is just the calculation of square root which reduced the running
     * time.
     */

    public int numSquares(int n) {
        int size= (int) Math.sqrt(n);
        int[][] t=new int[n+1][size+1];
        Arrays.fill(t[1],1);
        for(int i=1;i<=n;i++)
            t[i][0]=Integer.MAX_VALUE-1;
        int minSquares=Integer.MAX_VALUE;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=size;j++){
                if(j*j<=i)
                    t[i][j]=Math.min(1+t[i-j*j][j],t[i][j-1]);
                else t[i][j]=t[i][j-1];
            }
        }
        for(int j=1;j<=size;j++)
            minSquares=Math.min(minSquares,t[n][j]);
        return minSquares;
    }

    public int numSquares3(int n) {
        int[] t=new int[n+1];
        t[1]=1;
        for(int i=2;i<=n;i++){
            int minCount=Integer.MAX_VALUE;
            for(int j=1;j<=(int) Math.sqrt(i);j++){
                if(j*j<=i)
                    minCount=Math.min(1+t[i-j*j],minCount);
            }
            t[i]=minCount;
        }
        return t[n];
    }

    //!Optimal Solution
    public int numSquares4(int n) {
        int[] t=new int[n+1];
        t[1]=1;
        for(int i=2;i<=n;i++){
            int minCount=Integer.MAX_VALUE;
            int j=1;
            while (i-j*j>=0){
                minCount=Math.min(1+t[i-j*j],minCount);
                ++j;
            }
            t[i]=minCount;
        }
        return t[n];
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(12));
        System.out.println(new PerfectSquares().numSquares3(12));
        System.out.println(new PerfectSquares().numSquares4(12));
    }
}
