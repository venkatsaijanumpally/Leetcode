package Dynamic.Knapsack01;

public class PartitionEqualSubsetSum {
    Boolean[][] memoize;
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int i=0;i<nums.length;i++)
            sum+=nums[i];
        if(sum%2!=0)
            return false;
        memoize=new Boolean[nums.length+1][sum/2+1];

        //#Top-Down Approach
        /*for(int i=0;i< nums.length+1;i++)
            for(int j=0;j<sum/2+1;j++){
                if(j==0) memoize[i][j]=true;
                else if(i==0) memoize[i][j]=false;
                else if(nums[i-1]<=j)
                    memoize[i][j]=memoize[i-1][j-nums[i-1]] || memoize[i-1][j];
                else memoize[i][j]=memoize[i-1][j];
            }
        return memoize[nums.length][sum/2];*/
        boolean n=subsetSum(nums,sum/2, nums.length-1);
        return n;
    }

    //#Memoized
    public boolean subsetSum(int[] nums, int target, int n) {
        if(target==0) return true;
        if(n==0) return false;
        if(memoize[n][target]!=null) return memoize[n][target];

        if(nums[n]<=target)
            return memoize[n][target]=subsetSum(nums,target-nums[n],n-1) || subsetSum(nums,target,n-1);
        else return memoize[n][target]=subsetSum(nums,target,n-1);
    }

    public static void main(String[] args) {
        int[] arr={1,2,2,5};
        System.out.println(new PartitionEqualSubsetSum().canPartition(arr));
    }
}
