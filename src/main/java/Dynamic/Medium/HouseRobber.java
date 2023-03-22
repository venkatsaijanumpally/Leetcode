package Dynamic.Medium;

public class HouseRobber {
    //!Optimal
    public static int rob(int[] nums) {
        int[] max_cost=new int[nums.length+1];
        max_cost[max_cost.length-1]=0;
        max_cost[max_cost.length-2]=nums[nums.length-1];
        for(int i=max_cost.length-3;i>=0;i--){
            max_cost[i]=Math.max(nums[i]+max_cost[i+2],max_cost[i+1]);
        }
        return max_cost[0];
    }


    static Integer[] memoize;
    public static int recursionCaller(int[] nums){
        //return recursivePlain(nums,0,0);

        memoize=new Integer[nums.length];
        return recursiveMemoize(nums,0,0);
    }

    public static int recursivePlain(int[] nums, int index, int sum){
        if(index>nums.length-1) return sum;

        int take=recursivePlain(nums,index+2,sum)+nums[index];
        int skip=recursivePlain(nums,index+1,sum);

        return take>skip?take:skip;
    }

    //!Memoized
    public static int recursiveMemoize(int[] nums, int index, int sum){
        if(index>nums.length-1) return sum;
        if(memoize[index]!=null) return memoize[index];

        int take=recursiveMemoize(nums,index+2,sum)+nums[index];
        int skip=recursiveMemoize(nums,index+1,sum);
        memoize[index]=take>skip?take:skip;
        return memoize[index];
    }

    public static void main(String args[]) {
        int[] arr={2,7,9,3,1};
        int[] arr1={1,100,1,1,1,100,1,1,100,1};
        System.out.println(rob(arr));

        System.out.println();
        System.out.println(recursionCaller(arr));
    }
}
