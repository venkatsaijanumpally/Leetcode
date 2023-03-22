package Graphs.Hard;

public class LongestIncreasingPathinaMatrix {
    Integer[][] maxValuefromNode;
    int n,m;
    int[][] matrix;
    public int longestIncreasingPath(int[][] matrix) {
        this.matrix=matrix;
        n= matrix.length;
        m=matrix[0].length;
        maxValuefromNode=new Integer[n][m];

        int max=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                max=Math.max(recursive(i,j,-1), max);
            }
        }
        return max;
    }

    private int recursive(int i, int j, int prev) {
        if(matrix[i][j]<=prev)
            return 0;
        if(maxValuefromNode[i][j]!=null)
            return maxValuefromNode[i][j];

        int left=0,right=0,up=0,down=0;
        if(i==0){}
        else up=recursive(i-1,j,matrix[i][j]);

        if(i==n-1){}
        else down=recursive(i+1,j,matrix[i][j]);

        if(j==0){}
        else left=recursive(i,j-1,matrix[i][j]);

        if(j==m-1){}
        else right=recursive(i,j+1,matrix[i][j]);

        return maxValuefromNode[i][j]=1 + Math.max( Math.max(up,down), Math.max(left,right) );
    }

    public static void main(String[] args) {
        int[][] arr={{9,9,4},{6,6,8},{2,1,1}};
        int[][] arr2={{3,4,5},{3,2,6},{2,2,1}};
        int[][] arr3={{1}};
        System.out.println(new LongestIncreasingPathinaMatrix().longestIncreasingPath(arr));
        System.out.println(new LongestIncreasingPathinaMatrix().longestIncreasingPath(arr2));
        System.out.println(new LongestIncreasingPathinaMatrix().longestIncreasingPath(arr3));
    }
}
