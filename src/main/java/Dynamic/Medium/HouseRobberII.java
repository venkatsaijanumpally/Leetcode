package Dynamic.Medium;

import java.util.Arrays;

public class HouseRobberII {
    public int rob(int[] nums) {
        //TODO
        return 1;
    }

    Integer[] memoize1;
    Integer[] memoize2;
    public int recursionCaller(int[] nums){
        //return recursivePlain(nums,0,0);

        memoize1=new Integer[nums.length-1];
        memoize2=new Integer[nums.length];
        int res1= recursiveMemoize(nums,0,0,memoize1);
        int res2= recursiveMemoize(nums,1,0,memoize2);
        System.out.println(Arrays.toString(memoize1));
        System.out.println(Arrays.toString(memoize2));
        return res1>res2?res1:res2;
    }

    //!Memoized
    public int recursiveMemoize(int[] nums, int index, int sum, Integer[] memoize){
        if(index>memoize.length-1) return sum;
        if(memoize[index]!=null) return memoize[index];

        int take=recursiveMemoize(nums,index+2,sum,memoize)+nums[index];
        int skip=recursiveMemoize(nums,index+1,sum,memoize);
        memoize[index]=take>skip?take:skip;
        return memoize[index];
    }

    public static void main(String[] args) {
        int[] arr={2,3,2};
        int[] arr1={1,2,3,1};
        int[] arr2={1,2,3};
        System.out.println(new HouseRobberII().rob(arr));

        System.out.println();
        System.out.println(new HouseRobberII().recursionCaller(arr2));
    }
}
