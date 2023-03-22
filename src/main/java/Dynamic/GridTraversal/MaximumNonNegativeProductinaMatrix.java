package Dynamic.GridTraversal;

import Dynamic.GridTraversal.Values.Values;

public class MaximumNonNegativeProductinaMatrix {
    public int maxProductPath(int[][] grid) {
        int m= grid.length;
        int n=grid[0].length;
        if(m==1&&n==1) return grid[0][0];
        long[][][] memoize=new long[m][n][2];

        memoize[m-1][n-1][0]=grid[m-1][n-1];
        memoize[m-1][n-1][1]=grid[m-1][n-1];

        for(int i=m-2;i>=0;i--){
            memoize[i][n-1][0]=grid[i][n-1]*memoize[i+1][n-1][0];
            memoize[i][n-1][1]=grid[i][n-1]*memoize[i+1][n-1][1];
        }
        for(int i=n-2;i>=0;i--){
            memoize[m-1][i][0]=grid[m-1][i]*memoize[m-1][i+1][0];
            memoize[m-1][i][1]=grid[m-1][i]*memoize[m-1][i+1][1];
        }

        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                long r1=grid[i][j]*memoize[i][j+1][0];
                long r2=grid[i][j]*memoize[i][j+1][1];
                long d1=grid[i][j]*memoize[i+1][j][0];
                long d2=grid[i][j]*memoize[i+1][j][1];
                memoize[i][j][0]=Math.max(Math.max(r1,r2),Math.max(d1,d2));
                memoize[i][j][1]=Math.min(Math.min(r1,r2),Math.min(d1,d2));
            }
        }
        if(memoize[0][0][0]<0) return -1;
        long result=memoize[0][0][0]%1000000007;
        return (int)result;
    }

    public static void main(String[] args) {
        int[][] grid={{-1,-2,-3},{-2,-3,-3},{-3,-3,-2}};
        int[][] grid1={{-1}};
        System.out.println(new MaximumNonNegativeProductinaMatrix().maxProductPath(grid));
        System.out.println(new MaximumNonNegativeProductinaMatrix().maxProductPath(grid1));
        System.out.println(new MaximumNonNegativeProductinaMatrix().maxProductPath(Values.gridArr));
    }
}
