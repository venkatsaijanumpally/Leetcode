package Dynamic.GridTraversal;

public class MinimumPathSum {
    public static void main(String args[]) {
        //int[][] arr=new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int[][] arr=new int[][]{{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(arr));
    }

    public static int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][] arr=new int[m][n];
        arr[m-1][n-1]=grid[m-1][n-1];
        for(int i=m-2;i>=0;i--)
            arr[i][n-1]=arr[i+1][n-1]+grid[i][n-1];
        for(int i=n-2;i>=0;i--)
            arr[m-1][i]=arr[m-1][i+1]+grid[m-1][i];
        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                arr[i][j]= grid[i][j]+Math.min(arr[i + 1][j], arr[i][j + 1]);
            }
        }
        return arr[0][0];
    }
}
