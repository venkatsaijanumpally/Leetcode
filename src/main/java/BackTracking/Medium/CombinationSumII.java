package BackTracking.Medium;

import java.util.*;

public class CombinationSumII {
    /*
     * Approach: Take combination of number of duplicates available and dont revisit the element again.
     *
     * E.g: {1,1,1,2,2,7,6,5};
     * Here we can take either 0 ones or 1 ones or 2 ones or 3 ones at a time
     * We take these combinations one at a time and move to next distinct element
     */
    HashMap<Integer,Integer> hm=new HashMap<>();
    List<List<Integer>> result=new ArrayList<>();
    int n=0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        for(int i:candidates){
            if(!hm.containsKey(i))
                hm.put(i,1);
            else hm.put(i,hm.get(i)+1);
        }

        int[][] numCount=new int[hm.size()][2];
        int i=0;
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            numCount[i][0]=entry.getKey();
            numCount[i++][1]=entry.getValue();
        }
        n=i;
        recursion(new ArrayList<>(),target,numCount,0);
        return result;
    }


    private void recursion(ArrayList<Integer> items, int target, int[][] nums,int index) {
        if(index== n){
            if(target!=0) return;
            result.add(new ArrayList<>(items));
            return;
        }
        if(nums[index][1]==0){
            recursion(items,target,nums,index+1);
            return;
        }

        if(target>=nums[index][0]){
            recursion(items,target,nums,index+1);
            items.add(nums[index][0]);
            nums[index][1]--;
            recursion(items,target-nums[index][0],nums,index);
            items.remove(items.size()-1);
            nums[index][1]++;
        }
        else {
            recursion(items,target,nums,index+1);
        }
    }

    public static void main(String[] args) {
        int[] candidates={10,1,2,7,6,1,5};
        System.out.println(new CombinationSumII().combinationSum2(candidates,8));
    }
}
