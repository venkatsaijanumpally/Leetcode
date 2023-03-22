package BackTracking.Medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NondecreasingSubsequences {
    Set<List<Integer>> result=new HashSet<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        recursion(0,0,new ArrayList<>(),nums);
        return new ArrayList<>(result);
    }

    private void recursion(int index, int size, ArrayList<Integer> items, int[] nums) {
        if(index== nums.length){
            if(size>=2)
                result.add(new ArrayList<>(items));
            return;
        }

        if(items.isEmpty() || items.get(items.size()-1)<=nums[index]){
            items.add(nums[index]);
            recursion(index+1,size+1,items,nums);
            items.remove(items.size()-1);
        }
        recursion(index+1,size,items,nums);
    }



    public static void main(String[] args) {
        int[] nums={4,6,7,7};
        int[] nums2={1,2,3,4,5,6,7,8,9,10,1,1,1,1,1};
        System.out.println(new NondecreasingSubsequences().findSubsequences(nums2));
        System.out.println(new NondecreasingSubsequences().findSubsequences(nums2).size());
    }
}
