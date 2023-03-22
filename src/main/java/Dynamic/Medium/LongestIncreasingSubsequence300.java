package Dynamic.Medium;

import java.util.Arrays;

public class LongestIncreasingSubsequence300 {
    public static void main(String args[]) {
        int[] arr={10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLISonline(arr));
    }
    public static int lengthOfLIS(int[] nums) {
        int[] LIS=new int[nums.length];
        LIS[0]=1;
        int max=Integer.MIN_VALUE;
        /*for(int i=0;i<nums.length;i++)
            LIS[i]=1;*/
        for(int i=1;i<nums.length;i++){
            LIS[i]=1;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j] && LIS[i]<LIS[j]+1)
                    LIS[i]=LIS[j]+1;
            }
            if(max<LIS[i])
                max=LIS[i];
        }
        /*for(int i=0;i<nums.length;i++)
            if(max<LIS[i])
                max=LIS[i];*/
        return max;
    }

    public static int lengthOfLISonline(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
    }
}
