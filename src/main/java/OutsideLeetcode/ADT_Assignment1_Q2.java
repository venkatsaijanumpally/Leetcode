package OutsideLeetcode;

public class ADT_Assignment1_Q2 {

    int matrixSize=0;
    int[][] matrix;
    int[][] colMatrix;
    int[][] result;
    public int[][] matrixMultiplication(int k, int[][] colMatrix){
        this.colMatrix=colMatrix;
        int size= (int) Math.pow(2,k);
        result=new int[size][1];
        matrix=new int[size][size];
        formMatrix3(size,0,0,1);
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
        for(int i=0;i<size;i++){
                System.out.println(result[i][0]);
        }
        return matrix;
    }
    public void formMatrix(int subMatrixSize, int x, int y, int operator){
        if(subMatrixSize==1){
            matrix[x][y]=operator;
            result[x][0]+=colMatrix[y][0]*matrix[x][y];
            return;
        }
        for(int i=0;i<subMatrixSize/2;i++){
            matrix[x+i][y+i] = operator;
            result[x+i][0]+=colMatrix[y+i][0]*matrix[x+i][y+i];
        }
        formMatrix(subMatrixSize/2,x+subMatrixSize/2, y, operator);
        formMatrix(subMatrixSize/2,x, y+subMatrixSize/2, -operator);
        for(int i=0;i<subMatrixSize/2;i++){
            matrix[x+subMatrixSize/2+i][y+subMatrixSize/2+i] = operator*2;
            result[x+subMatrixSize/2+i][0]+=colMatrix[y+subMatrixSize/2+i][0]*matrix[x+subMatrixSize/2+i][y+subMatrixSize/2+i];
        }
    }

    public void formMatrix3(int subMatrixSize, int x, int y, int operator){
        if(subMatrixSize==1){
            result[x][0]+=colMatrix[y][0]*operator;
            return;
        }
        for(int i=0;i<subMatrixSize/2;i++){
            result[x+i][0]+=colMatrix[y+i][0]*operator;
        }
        formMatrix3(subMatrixSize/2,x+subMatrixSize/2, y, operator);
        formMatrix3(subMatrixSize/2,x, y+subMatrixSize/2, -operator);
        for(int i=0;i<subMatrixSize/2;i++){
            result[x+subMatrixSize/2+i][0]+=colMatrix[y+subMatrixSize/2+i][0]*operator*2;
        }
    }

    public void formMatrix2(int subMatrixSize, int x, int y, int operator){
        if(subMatrixSize==1){
            matrix[x][y]=operator;
            return;
        }
        for(int i=0;i<subMatrixSize/2;i++)
            matrix[x+i][y+i] = operator;
        formMatrix2(subMatrixSize/2,x+subMatrixSize/2, y, operator);
        formMatrix2(subMatrixSize/2,x, y+subMatrixSize/2, -operator);
        for(int i=0;i<subMatrixSize/2;i++)
            matrix[x+subMatrixSize/2+i][y+subMatrixSize/2+i] = operator*2;
    }

    public static void main(String[] args) {
        int[][] col1={{1},{1},{1},{1},{1},{1},{1},{1}};
        new ADT_Assignment1_Q2().matrixMultiplication(3,col1);
        new ADT_Assignment1_Q2().matrixMultiplication(2,new int[][]{{1},{1},{1},{1}});
        new ADT_Assignment1_Q2().matrixMultiplication(4,new int[][]{{1},{1},{1},{1},{1},{1},{1},{1},{1},{1},{1},{1},{1},{1},{1},{1}});
    }
}
