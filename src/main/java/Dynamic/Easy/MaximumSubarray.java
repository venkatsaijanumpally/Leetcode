package Dynamic.Easy;

public class MaximumSubarray {
    /**
     * KadaneAlgorithm and kadaneOptimized both are Optimal.
     * kadaneOptimized is easier to understand and comparatively faster.
     */
    public int maxSubArray(int[] nums) {
        //return usingDP(nums);
        return kadaneOptimized(nums);
    }

    /*
     * Kadane Algorithm
     * The simple idea of Kadane’s algorithm is to look for all positive contiguous segments of the array (maxSumEndingHere
     * is used for this). And keep track of maximum sum contiguous segment among all positive segments (maxSum is used
     * for this). Each time we get a sum(either positive sum or negative) compare it with maxSum and update maxSum if
     * it is greater than maxSum.
     *
     * how it works:
     * - At any index in the array we calculate maxSumEndingHere, if maxSumEndingHere is negative which means the sum of
     *   the best subarray ending here will give a negative result so we discard this subarray by making value 0.
     *   Best subarray here means that there may be many subarray possible ending here, out of all those subarrays even
     *   if we take the subarray that give the max value it will still give us negative value.
     * - If we have all negative elements in the array then the program works for just finding the max value out of all
     *   individual elements. In this case maxSumEndingHere will always have just the individual element at a index.
     */
    //!Optimal
    private int KadaneAlgorithm(int[] nums) {
        int maxSum=Integer.MIN_VALUE,maxSumEndingHere=0;

        for(int i=0;i<nums.length;i++){
            maxSumEndingHere+=nums[i];
            if(maxSum<maxSumEndingHere)
                maxSum=maxSumEndingHere;
            if(maxSumEndingHere<0)
                maxSumEndingHere=0;
        }
        return maxSum;
    }

    /*
     * The approach is similar to KadaneAlgorithm.
     * Basic idea here is
     * 1. If (trailing subarray sum)+(current element) is less than current element then start a new subarray with
     *    current element.
     * 2. If (trailing subarray sum)+(current element) is greater than current element then we extend the trailing
     *    subarray by adding current element.
     */
    //!Optimal
    private int kadaneOptimized(int[] nums) {
        int maxSum=Integer.MIN_VALUE,maxSumEndingHere=0;

        for(int i=0;i<nums.length;i++){
            maxSumEndingHere=Math.max(nums[i],nums[i]+maxSumEndingHere);
            if(maxSum<maxSumEndingHere) maxSum=maxSumEndingHere;
        }
        return maxSum;
    }

    private int usingDP(int[] nums) {
        int[] t=new int[nums.length];

        int max=nums[0];
        if(nums[0]>0) t[0]=nums[0];
        else t[0]=0;

        for(int i=1;i<nums.length;i++){
            if(max<nums[i])max=nums[i];
            if(t[i-1]+nums[i]>0)
                t[i]=t[i-1]+nums[i];
            else t[i]=0;
        }

        if(max<=0) return max;
        for(int i=0;i<nums.length;i++)
            if(max<t[i]) max=t[i];
        return max;
    }

    public static void main(String[] args) {
        int[] arr={-2,1,-3,4,-1,2,1,-5,4};
        int[] arr1={-2,-1,-3};
        System.out.println(new MaximumSubarray().maxSubArray(arr));
        System.out.println(new MaximumSubarray().maxSubArray(arr1));
    }
}
