package Dynamic.Medium;

public class MaximumNumberofPointswithCost {
    public long maxPoints(int[][] points) {
        int rows= points.length,cols=points[0].length;
        long[][] t=new long[rows][cols];
        for (int i=0;i<cols;i++){
            t[rows-1][i]=points[rows-1][i];
        }

        for (int i=rows-2;i>=0;i--){
            int j=i+1;
            for (int k=0;k<cols;k++){
                for (int l=0;l<cols;l++){
                    t[i][k]=Math.max(t[i][k], points[i][k]+t[j][l]-Math.abs(k-l));
                }
            }
        }
        long max=0;
        for (int i=0;i<cols;i++)
            max=Math.max(max,t[0][i]);
        return max;
    }

    //Optimal solution using left maximum and right maximum
    public long maxPoints2(int[][] points) {
        int rows= points.length,cols=points[0].length;
        long[][] t=new long[rows][cols];
        for (int i=0;i<cols;i++){
            t[rows-1][i]=points[rows-1][i];
        }

        for (int i=rows-2;i>=0;i--){
            int j=i+1;
            int leftMaxIndex=0;
            int[] rightMax=new int[cols];
            rightMax[cols-1]=cols-1;
            for (int k=cols-2;k>=0;k--){
                if(t[j][k]>t[j][rightMax[k+1]]-(rightMax[k+1]-k))
                    rightMax[k]=k;
                else rightMax[k]=rightMax[k+1];
            }

            for (int k=0;k<cols;k++){
                if(t[j][k]>t[j][leftMaxIndex]-(k-leftMaxIndex)) //Update left maximum as we traverse
                    leftMaxIndex=k;
                t[i][k]=points[i][k] + Math.max(t[j][leftMaxIndex]-(k-leftMaxIndex), t[j][rightMax[k]]-(rightMax[k]-k));
            }
        }
        long max=0;
        for (int i=0;i<cols;i++)
            max=Math.max(max,t[0][i]);
        return max;
    }

    public static void main(String[] args) {
        int[][] points1={{1,2,3},{1,5,1},{3,1,1}};
        System.out.println(new MaximumNumberofPointswithCost().maxPoints2(points1));
    }
}
