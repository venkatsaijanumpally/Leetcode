package Dynamic.Knapsack01;

public class TargetSum {
    /*
     *  This question is same as CountofNumberofSubsetWithGivenDifference
     *  There are few conditions added here as per the testcases below are the reasons
     *
     *  Reason for condition (totalsum+target)%2!=0
     *          If the totalsum is odd then for any sum1-sum2 will be odd
     *          If the totalsum is even then for any sum1-sum2 will be even
     *
     *          Example: {1,1,1,1,1}   If we write down all subsets s1,s2 which are possible the s1-s2 will be odd.
     *
     *                    S1                    S2                  Diff
     *                    {}                    {1,1,1,1,1}         5
     *                    {1}                   {1,1,1,1}           3
     *                    {1,1}                 {1,1,1}             1
     *                    {1,1,1}               {1,1}               1
     *                    {1,1,1,1}             {1}                 3
     *                    {1,1,1,1,1}           {}                  5
     *          We can see the diff alternates as 5,3,1 similarly for even totalsum array.
     *
     *  Reason for requiredSum<0
     *          As per the constraints provided all elements are non-negative so we cant get a negative sum subset.
     *
     *  Reason for not handling Ceil for requiredsum calculation.
     *          Since target and totalsum cant be of opposite(odd,even) so the sum is always even and dont require handling.
     */
    Integer[][] memoize;
    int totalsum=0;
    public int findTargetSumWays(int[] nums, int target) {
        for(int i=0;i<nums.length;i++)
            totalsum+=nums[i];

        int requiredSum=(totalsum+target)/2;
        if((totalsum+target)%2!=0 || requiredSum<0) return 0;
        memoize=new Integer[nums.length][requiredSum+1];
        return countNumberofSubsets(nums,nums.length-1,requiredSum);
    }

    private int countNumberofSubsets(int[] nums, int index, int requiredSum) {
        if(index==-1){
            if(requiredSum==0) return 1;
            return 0;
        }

        if(memoize[index][requiredSum]!=null) return memoize[index][requiredSum];
        if(nums[index]<=requiredSum)
            return memoize[index][requiredSum]=countNumberofSubsets(nums,index-1,requiredSum-nums[index]) +
                    countNumberofSubsets(nums,index-1,requiredSum);
        else return memoize[index][requiredSum]=countNumberofSubsets(nums,index-1,requiredSum);
    }

    public static void main(String[] args) {
        int[] arr={1,1,1,1,1};
        int[] arr1={1};
        int[] arr2={0,0,0,0,0,0,0,0,1};
        int[] arr3={7,9,3,8,0,2,4,8,3,9};
        int[] arr4={100};
        System.out.println(new TargetSum().findTargetSumWays(arr,3));
        System.out.println(new TargetSum().findTargetSumWays(arr1,2));
        System.out.println(new TargetSum().findTargetSumWays(arr2,1));
        System.out.println(new TargetSum().findTargetSumWays(arr3,0));
        System.out.println(new TargetSum().findTargetSumWays(arr4,-200));
    }
}
