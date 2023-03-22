import java.util.Arrays;

public class SetMatrixZeroes {
    /**
     * https://www.youtube.com/watch?v=M65xBewcqcI
     * @param matrix
     */
    public void setZeroesOptimal(int[][] matrix){
        int n=matrix.length;
        int m=matrix[0].length;
        boolean col=false,row=false;
        /*for(int i=0;i<m;i++){
            if(matrix[0][i]==0){
                row=true;
                break;
            }
        }
        for(int i=0;i<n;i++){
            if(matrix[i][0]==0){
                col=true;
                break;
            }
        }*/
        for(int i=0;i<n;i++){
            if(matrix[i][0]==0) col=true;
            for(int j=1;j<m;j++){
                if(matrix[i][j]==0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
        }
        /*for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(j==0 && col){
                    matrix[i][j]=0;
                }
                else if(i==0 && row){
                    matrix[i][j]=0;
                }
                else if(matrix[i][0]==0 || matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
        }*/

        /*
        In this loop we run j from m-1 to 1 only, because if we take 0 also in consideration the
        value of matrix[i][0] will be impacted because of matrix[0][0] which internally can get changed to '0'
        if any element is zero in the first row which is changed in the first loop.
         */
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=1;j--){
                if(matrix[i][0]==0 || matrix[0][j]==0){
                    matrix[i][j]=0;
                }
            }
            if(col)
                matrix[i][0]=0;
        }
        System.out.println(Arrays.deepToString(matrix));
    }
    //https://www.tutorialcup.com/interview/matrix/set-matrix-zeroes.htm
    public void setZeroes(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==0){
                    for(int k=0;k<m;k++){
                        if(matrix[i][k]!=0)
                            matrix[i][k]=-1;
                    }
                    for(int k=0;k<n;k++){
                        if(matrix[k][j]!=0)
                            matrix[k][j]=-1;
                    }
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==-1)
                    matrix[i][j]=0;
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    public static void main(String[] args) {
        int[][] matrix={{1,1,1},{1,0,1},{1,1,1}};
        int[][] matrix1={{1,1,2,0},{3,4,5,2},{1,3,1,5}};
        new SetMatrixZeroes().setZeroesOptimal(matrix1);
    }
}
