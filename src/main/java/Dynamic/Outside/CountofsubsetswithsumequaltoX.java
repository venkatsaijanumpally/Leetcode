package Dynamic.Outside;

public class CountofsubsetswithsumequaltoX {
    /*
     * Q. https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/
     * https://www.youtube.com/watch?v=F7wqWbqYn9g
     */

    Integer[][] memoize;
    public int countNoOfSubsetsWithSum(int[] nums, int target){
        memoize=new Integer[nums.length+1][target+1];
        return recursiveMemoize(nums,target,0);
    }

    private int recursiveMemoize(int[] nums, int target, int index) {
        if(target==0 || index==nums.length){
            if(target==0) return 1;
            return 0;
        }

        if(memoize[index][target]!=null) return memoize[index][target];
        if(nums[index]<=target)
            return memoize[index][target]=recursiveMemoize(nums,target-nums[index],index+1)+
                    recursiveMemoize(nums,target,index+1);
        else return memoize[index][target]=recursiveMemoize(nums,target,index+1);
    }

    public static void main(String[] args) {
        int[] arr={1, 2, 3, 3};
        int[] arr1={1, 1, 1, 1};
        System.out.println(new CountofsubsetswithsumequaltoX().countNoOfSubsetsWithSum(arr,6));
        System.out.println(new CountofsubsetswithsumequaltoX().countNoOfSubsetsWithSum(arr1,1));
    }
}
