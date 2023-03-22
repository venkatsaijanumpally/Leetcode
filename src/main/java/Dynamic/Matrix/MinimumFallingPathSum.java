package Dynamic.Matrix;

public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {

        for(int i=matrix.length-2;i>-1;i--){
            matrix[i][0]+=Math.min(matrix[i+1][0],matrix[i+1][1]);
            for(int j=1;j< matrix.length-1;j++){
                 matrix[i][j]+=Math.min(matrix[i+1][j],Math.min(matrix[i+1][j+1],matrix[i+1][j-1]));
            }
            matrix[i][matrix.length-1]+=Math.min(matrix[i+1][matrix.length-1],matrix[i+1][matrix.length-2]);
        }

        int min=Integer.MAX_VALUE;
        for(int i=0;i<matrix.length;i++)
            min=Math.min(matrix[0][i],min);
        return min;
    }

    public static void main(String[] args) {
        int[][] arr={{2,1,3},{6,5,4},{7,8,9}};
        System.out.println(new MinimumFallingPathSum().minFallingPathSum(arr));
    }
}
