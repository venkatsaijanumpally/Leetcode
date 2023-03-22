package BackTracking.Medium;

import java.util.ArrayList;

public class PartitiontoKEqualSumSubsets {
    //TODO
    //Not Working
    ArrayList<Integer> numsList=new ArrayList<>();
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum=0;
        for(int i=0;i<nums.length;i++){
            numsList.add(nums[i]);
            sum+=nums[i];
        }
        if(sum%k!=0) return false;
        for(int i=1;i<=k;i++){
            if(!recursion(0,sum/k))
                return false;
        }
        return true;
    }

    private boolean recursion(int index, int target) {
        if(target==0) return true;
        if(index==numsList.size()) return false;

        for(int i=index;i<numsList.size();i++){
            if(numsList.get(i)<=target){
                int rem=numsList.remove(i);
                if(recursion(i,target-rem)) return true;
                numsList.add(i,rem);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums={4,3,2,3,5,2,1};
        int[] nums2={1,2,3,4};
        System.out.println(new PartitiontoKEqualSumSubsets().canPartitionKSubsets(nums,4));
        System.out.println(new PartitiontoKEqualSumSubsets().canPartitionKSubsets(nums2,3));
    }
}
