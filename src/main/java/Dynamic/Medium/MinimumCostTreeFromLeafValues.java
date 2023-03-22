package Dynamic.Medium;

public class MinimumCostTreeFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        int[][] maxNum=new int[arr.length][arr.length];
        int[][] minCost=new int[arr.length][arr.length];
        int[][] nonLeafNodeSum=new int[arr.length-1][arr.length-1];

        maxNum[arr.length-1][arr.length-1]=arr[arr.length-1];
        minCost[arr.length-1][arr.length-1]=arr[arr.length-1];

        /*for(int i=arr.length-2;i>=0;i--){
            int min=Integer.MAX_VALUE;
            int maxNumInTheSubTree=Math.max(arr[i],maxNum[i+1][arr.length-1]);
            int maxNumInLeftSubTree=arr[i];
            int minNonLeafSum=Integer.MAX_VALUE;
            maxNum[i][i]=arr[i];
            minCost[i][i]=arr[i];
            nonLeafNodeSum[i][i]=0;
            for(int pivot=i;pivot<arr.length-1;pivot++){
                minNonLeafSum=maxNumInLeftSubTree*maxNum[pivot+1][arr.length-1]+nonLeafNodeSum[]
                maxNumInLeftSubTree=Math.max(maxNumInLeftSubTree,arr[pivot]);
                if(min>costOfSubTree){
                    min=costOfSubTree;
                }
            }
            maxNum[i][arr.length-1]=maxNumInTheSubTree;
            minCost[i][arr.length-1]=min;
        }*/
        return minCost[0][arr.length-1];
    }

    public static void main(String[] args) {
        int[] arr={6,2,4};
        System.out.println(new MinimumCostTreeFromLeafValues().mctFromLeafValues(arr));
    }
}
