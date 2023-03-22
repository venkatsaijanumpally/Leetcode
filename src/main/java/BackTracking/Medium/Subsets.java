package BackTracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        //'i' sets size of subset
        for(int i=0;i<=nums.length;i++){
            recursion(nums,i,new ArrayList<>(),0);
        }
        return result;
    }

    private void recursion(int[] nums, int size, ArrayList<Integer> items, int start) {
        if(size==0){
            result.add(new ArrayList<>(items));
            return;
        }
        if(start== nums.length) return;

        for(int i=start;i< nums.length;i++){
            items.add(nums[i]);
            recursion(nums,size-1,items,i+1);
            items.remove(items.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums={1,2,3};
        System.out.println(new Subsets().subsets(nums));
    }
}
