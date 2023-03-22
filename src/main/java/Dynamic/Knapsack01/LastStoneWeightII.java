package Dynamic.Knapsack01;

public class LastStoneWeightII {
    int totalSum=0;
    Integer[][] memoize;
    public int lastStoneWeightII(int[] stones) {
        for(int i=0;i<stones.length;i++)
            totalSum+=stones[i];
        memoize=new Integer[stones.length+1][totalSum+1];
        return recursiveMemoize(stones,0,0);
    }

    private int recursiveMemoize(int[] nums, int index, int sum) {
        if(index==nums.length)
            return Math.abs(sum-(totalSum-sum));

        if(memoize[index][sum]!=null) return memoize[index][sum];
        int add1=recursiveMemoize(nums,index+1,sum+nums[index]);
        int add2=recursiveMemoize(nums,index+1,sum);

        return memoize[index][sum]=Math.min(add1, add2);
    }

    public static void main(String[] args) {
        int[] arr={2,7,4,1,8,1};
        System.out.println(new LastStoneWeightII().lastStoneWeightII(arr));
    }
}
