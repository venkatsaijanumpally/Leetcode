package Dynamic.Medium;

public class MaximumAlternatingSubsequenceSum {
    public long maxAlternatingSum(int[] nums) {
        long[] tMax=new long[nums.length];
        long[] tMin=new long[nums.length];
        tMax[nums.length-1]=nums[nums.length-1];
        tMin[nums.length-1]=0;
        for (int i= nums.length-2;i>=0;i--){
            tMax[i]=Math.max(tMax[i+1],Math.max(nums[i]-tMin[i+1],nums[i]));
            tMin[i]=Math.min(0,Math.min(nums[i]-tMax[i+1],tMin[i+1]));
        }
        return tMax[0];
    }

    public static void main(String[] args) {
        int[] nums1={4,2,5,3};
        int[] nums2={5,6,7,8};
        int[] nums3={6,2,1,2,4,5};
        System.out.println(new MaximumAlternatingSubsequenceSum().maxAlternatingSum(nums1));
        System.out.println(new MaximumAlternatingSubsequenceSum().maxAlternatingSum(nums2));
        System.out.println(new MaximumAlternatingSubsequenceSum().maxAlternatingSum(nums3));
    }
}
