package Dynamic.Medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

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

    public int mctFromLeafValues2(int[] arr){
        Integer[][] t=new Integer[arr.length][arr.length];
        int[][] maxElement=new int[arr.length][arr.length];
        int[][] minSum=new int[arr.length][arr.length];

        //t[0][0]=arr[0];
        /*leftMax[0]=arr[0];
        rightMax[arr.length-1]=arr[arr.length-1];
        for (int i=1;i<arr.length;i++){
            leftMax[i]=Math.max(arr[i],leftMax[i-1]);
            t[i][i]=arr[i];
        }
        for (int i=arr.length-2;i>=0;i--)
            rightMax[i]=Math.max(arr[i],rightMax[i+1]);*/

        for (int i=0;i<arr.length;i++){
            t[i][i]=arr[i];
            minSum[i][i]=0;
            maxElement[i][i]=arr[i];
        }

        for (int size=1;size<arr.length;size++){
            for (int i=0;i<arr.length-size;i++){
                int j=i+size;
                int minSumm=Integer.MAX_VALUE;
                int product=0;
                for (int k=i;k<j;k++){
                    int calc=maxElement[i][k]*maxElement[k+1][j]+minSum[i][k]+minSum[k+1][j];
                    if(calc<minSumm){
                        minSumm=calc;
                        product=maxElement[i][k]*maxElement[k+1][j];
                    }
                }
                t[i][j]=product;
                minSum[i][j]=minSumm;
                maxElement[i][j]=Math.max(maxElement[i][i],maxElement[i+1][j]);
            }
        }
        return minSum[0][arr.length-1];
    }

    public static void main(String[] args) {
        int[] arr={6,2,4};
        System.out.println(new MinimumCostTreeFromLeafValues().mctFromLeafValues2(arr));
    }
}
