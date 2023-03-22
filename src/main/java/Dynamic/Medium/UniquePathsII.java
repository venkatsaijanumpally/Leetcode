package Dynamic.Medium;

public class UniquePathsII {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        if(obstacleGrid[m-1][n-1]==1)
            return 0;
        int[][] arr=new int[m][n];
        arr[m-1][n-1]=1;
        for(int i=m-2;i>=0;i--){
            if(obstacleGrid[i][n-1]!=1 && arr[i+1][n-1]!=0)
                arr[i][n-1]=1;
            else
                arr[i][n-1]=0;
        }
        for(int i=n-2;i>=0;i--){
            if(obstacleGrid[m-1][i]!=1 && arr[m-1][i+1]!=0)
                arr[m-1][i]=1;
            else
                arr[m-1][i]=0;
        }
        for(int i=m-2;i>=0;i--)
            for(int j=n-2;j>=0;j--){
                if(obstacleGrid[i][j]==1)
                    arr[i][j]=0;
                else
                    arr[i][j]=arr[i+1][j]+arr[i][j+1];
            }
        return arr[0][0];
    }
    public static void main(String args[]) {
        //int[][] arr=new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int[][] arr=new int[][]{{0,0},{1,1},{0,0}};
        System.out.println(uniquePathsWithObstacles(arr));
    }
}
