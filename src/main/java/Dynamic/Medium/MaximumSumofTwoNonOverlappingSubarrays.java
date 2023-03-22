package Dynamic.Medium;

import java.util.Arrays;

public class MaximumSumofTwoNonOverlappingSubarrays {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int[] sumFirst=new int[nums.length];
        int[] maxFirst=new int[nums.length];
        int[] sumSecond=new int[nums.length];
        int[] maxSecond=new int[nums.length];

        int initialSum=0;
        int maxSoFar=0;
        for(int i=0;i<firstLen-1;i++){
            initialSum+=nums[i];
            maxSoFar=initialSum;
        }

        for(int i=firstLen-1;i<nums.length;i++){
            initialSum+=nums[i];
            maxSoFar=Math.max(maxSoFar,initialSum);
            sumFirst[i]=initialSum;
            maxFirst[i]=maxSoFar;
            initialSum-=nums[i+1-firstLen];
        }
        System.out.println(Arrays.toString(sumFirst));
        System.out.println(Arrays.toString(maxFirst));

        initialSum=0;
        maxSoFar=0;
        for(int i=0;i<secondLen-1;i++){
            initialSum+=nums[i];
            maxSoFar=initialSum;
        }
        for(int i=secondLen-1;i<nums.length;i++){
            initialSum+=nums[i];
            maxSoFar=Math.max(initialSum,maxSoFar);
            sumSecond[i]=initialSum;
            maxSecond[i]=maxSoFar;
            initialSum-=nums[i+1-secondLen];
        }
        System.out.println(Arrays.toString(sumSecond));
        System.out.println(Arrays.toString(maxSecond));

        int max=-1;
        for(int i=firstLen+secondLen-1;i< nums.length;i++){
            max=Math.max(sumFirst[i]+maxSecond[i-firstLen],max);
            max=Math.max(sumSecond[i]+maxFirst[i-secondLen],max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums1={0,6,5,2,2,5,1,9,4};
        int[] nums2={3,8,1,3,2,1,8,9,0};
        //System.out.println(new MaximumSumofTwoNonOverlappingSubarrays().maxSumTwoNoOverlap(nums1,1,2));
        System.out.println(new MaximumSumofTwoNonOverlappingSubarrays().maxSumTwoNoOverlap(nums2,3,2));
    }
}
