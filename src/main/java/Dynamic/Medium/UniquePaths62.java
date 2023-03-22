package Dynamic.Medium;

import java.util.ArrayList;

public class UniquePaths62 {
    public static int uniquePaths(int m,int n) {
        int[][] arr=new int[m][n];
        for(int i=0;i<m;i++)
            arr[i][n-1]=1;
        for(int i=0;i<n;i++)
            arr[m-1][i]=1;
        for(int i=m-2;i>=0;i--)
            for(int j=n-2;j>=0;j--)
                arr[i][j]=arr[i+1][j]+arr[i][j+1];
        return arr[0][0];
    }
    public static void main(String args[]) {
        System.out.println(uniquePaths(3,7));
    }
}
