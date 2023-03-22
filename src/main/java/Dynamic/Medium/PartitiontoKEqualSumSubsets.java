package Dynamic.Medium;

public class PartitiontoKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //TODO not working learn BITMASKING and BACKTRACKING
        int sum=0;
        for(int i=0;i<nums.length;i++)
            sum+=nums[i];
        if(sum%k!=0) return false;
        int count=recursiveMemoize(nums,sum/k,0);
        return count==k;
    }

    private int recursiveMemoize(int[] nums, int sum, int index) {
        if(index==nums.length){
            if(sum==0) return 1;
            return 0;
        }

        if(nums[index]<=sum)
            return recursiveMemoize(nums,sum-nums[index],index+1)+
                    recursiveMemoize(nums,sum,index+1);
        return recursiveMemoize(nums,sum,index+1);
    }

    public static void main(String[] args) {
        int[] nums1={4,3,2,3,5,2,1};
        System.out.println(new PartitiontoKEqualSumSubsets().canPartitionKSubsets(nums1,4));
    }
}
