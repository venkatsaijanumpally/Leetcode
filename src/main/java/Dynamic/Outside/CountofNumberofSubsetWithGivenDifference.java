package Dynamic.Outside;

public class CountofNumberofSubsetWithGivenDifference {

    /*
     *  Two Equations:
     *              sum1-sum2=diff
     *              sum1+sum2=totalsum
     *          -----------------------------
     * Add both==>  2sum1=diff+totalsum
     *               sum1=(diff+totalsum)/2
     *
     * If we can find a subset sum of (diff+totalsum)/2 then increment the count.
     */

    int totalSum=0;
    int requiredSum=0;
    public int count(int[] nums, int diff){
        for(int i=0;i<nums.length;i++)
            totalSum+=nums[i];
        requiredSum=(totalSum+diff)/2;
        return new CountofsubsetswithsumequaltoX().countNoOfSubsetsWithSum(nums,requiredSum);
    }

    public static void main(String[] args) {
        int[] arr={1,1,2,3};
        int[] arr1={1,1,1,1,1};
        System.out.println(new CountofNumberofSubsetWithGivenDifference().count(arr,1));
        System.out.println(new CountofNumberofSubsetWithGivenDifference().count(arr1,3));
    }
}
