package Dynamic.MatrixChainMultiplication;

public class MinimumScoreTriangulationofPolygon {

    public int minScoreTriangulation(int[] values) {
        //return minTriangulation(values, 0, values.length-1);
        return minTriangulation(values);
    }

    private int minTriangulation(int[] values, int start, int end) {
        if(end-start<2)
            return 0;
        int min=Integer.MAX_VALUE;
        for(int k=start+1;k<end;k++){
            int right=minTriangulation(values,start,k);
            int left=minTriangulation(values,k,end);
            min=Math.min(min,right+left+values[start]*values[end]*values[k]);
        }
        return min;
    }

    private int minTriangulation(int[] values){
        int n= values.length;
        int[][] t=new int[n+1][n+1];
        for(int size=2;size<n;size++){
            for(int i=0;i+size<n;i++){
                int j=size+i;
                t[i][j]=Integer.MAX_VALUE;
                for(int k=i+1;k<j;k++){
                    t[i][j]=Math.min(t[i][j],t[i][k]+t[k][j]+values[i]*values[j]*values[k]);
                }
            }
        }
        return t[0][n-1];
    }

    public static void main(String[] args) {
        int[] arr={3,7,4,5};
        System.out.println(new MinimumScoreTriangulationofPolygon().minScoreTriangulation(arr));
    }
}
