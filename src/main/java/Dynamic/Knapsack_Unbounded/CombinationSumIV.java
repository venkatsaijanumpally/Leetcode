package Dynamic.Knapsack_Unbounded;

import java.util.Arrays;

public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int[] t=new int[target+1];
        t[0]=1;
        for(int i=1;i<=target;i++){
            for(int j=0;j< nums.length;j++){
                if(nums[j]<=i){
                    t[i]+=t[i-nums[j]];
                }
            }
        }
        System.out.println(Arrays.toString(t));
        return t[target];
    }

    public static void main(String[] args) {
        int[] nums={1,2,3};
        System.out.println(new CombinationSumIV().combinationSum4(nums,4));
    }
}
