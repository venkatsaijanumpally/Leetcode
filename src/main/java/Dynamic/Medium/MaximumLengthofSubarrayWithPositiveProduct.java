package Dynamic.Medium;

import java.util.Arrays;

public class MaximumLengthofSubarrayWithPositiveProduct {
    public int getMaxLen(int[] nums) {
        if(nums.length==1)
            return nums[0]>0?1:0;
        int[] maxPositiveLeft=new int[nums.length];
        int[] maxPositiveRight=new int[nums.length];
        int[] maxNegativeLeft=new int[nums.length];
        int[] maxNegativeRight=new int[nums.length];
        if(nums[0]==0){
            maxPositiveLeft[0]=0;
            maxNegativeLeft[0]=0;
        }
        else if(nums[0]>0){
            maxPositiveLeft[0]=1;
            maxNegativeLeft[0]=0;
        }
        else {
            maxPositiveLeft[0]=0;
            maxNegativeLeft[0]=1;
        }

        if(nums[nums.length-1]==0){
            maxPositiveRight[nums.length-1]=0;
            maxNegativeRight[nums.length-1]=0;
        }
        else if(nums[nums.length-1]>0){
            maxNegativeRight[nums.length-1]=0;
            maxPositiveRight[nums.length-1]=1;
        }
        else {
            maxNegativeRight[nums.length-1]=1;
            maxPositiveRight[nums.length-1]=0;
        }

        int max=0;
        for (int i=1;i< nums.length;i++){
            if(nums[i]==0){
                maxPositiveLeft[i]=0;
                maxNegativeLeft[i]=0;
            }
            else if(nums[i]>0){
                maxPositiveLeft[i]=maxPositiveLeft[i-1]+1;
                maxNegativeLeft[i]=maxNegativeLeft[i-1]>0?maxNegativeLeft[i-1]+1:0;
            }
            else {
                maxPositiveLeft[i]=maxNegativeLeft[i-1]>0?maxNegativeLeft[i-1]+1:0;
                maxNegativeLeft[i]=maxPositiveLeft[i-1]+1;
            }
            max=Math.max(max,maxPositiveLeft[i]);
        }

        for(int i= nums.length-2;i>=0;i--){
            if(nums[i]==0){
                maxPositiveRight[i]=0;
                maxNegativeRight[i]=0;
            }
            else if(nums[i]>0){
                maxPositiveRight[i]=maxPositiveRight[i+1]+1;
                maxNegativeRight[i]=maxNegativeRight[i+1]>0?maxNegativeRight[i+1]+1:0;
            }
            else {
                maxPositiveRight[i]=maxNegativeRight[i+1]>0?maxNegativeRight[i+1]+1:0;
                maxNegativeRight[i]=maxPositiveRight[i+1]+1;
            }
            max=Math.max(max,maxPositiveRight[i]);
        }
        System.out.println(Arrays.toString(maxPositiveLeft));
        System.out.println(Arrays.toString(maxNegativeLeft));
        System.out.println(Arrays.toString(maxPositiveRight));
        System.out.println(Arrays.toString(maxNegativeRight));
        return max;
    }

    public static void main(String[] args) {
        int[] arr={1,-2,-3,4};
        int[] arr2={-17,-9,17,-3,-5,-13,2,6,0};
        System.out.println(new MaximumLengthofSubarrayWithPositiveProduct().getMaxLen(arr));
        System.out.println(new MaximumLengthofSubarrayWithPositiveProduct().getMaxLen(arr2));
    }
}
