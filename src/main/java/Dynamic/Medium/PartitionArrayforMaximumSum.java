package Dynamic.Medium;

public class PartitionArrayforMaximumSum {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] maxTill=new int[arr.length+1];
        for(int i=0;i<arr.length;i++){
            int max=arr[i];
            int sumOfSubarray=0;
            for(int size=1;size<=k && i-size+1>=0;size++){
                max=Math.max(arr[i-size+1],max);
                sumOfSubarray=size*max;
                if(maxTill[i+1]<sumOfSubarray+maxTill[i+1-size]) maxTill[i+1]=sumOfSubarray+maxTill[i+1-size];
            }
        }
        return maxTill[arr.length];
    }

    public static void main(String[] args) {
        int[] arr1={1,15,7,9,2,5,10};
        System.out.println(new PartitionArrayforMaximumSum().maxSumAfterPartitioning(arr1,3));
    }
}
