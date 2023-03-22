package BackTracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    List<List<Integer>> permutations=new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i:nums)
            arr.add(i);
        for(int i=0;i<nums.length;i++)
            recursion(arr,new ArrayList<>(),i);
        return permutations;
    }

    private void recursion(ArrayList<Integer> nums, List<Integer> items, int index) {
        if(nums.size()==1){
            items.add(nums.get(0));
            permutations.add(new ArrayList<>(items));
            items.remove(items.size()-1);
            return;
        }
        int removedItem=nums.remove(index);
        items.add(removedItem);
        for(int i=0;i<nums.size();i++){
            recursion(nums,items,i);
        }
        nums.add(index,removedItem);
        items.remove(items.size()-1);
    }

    public static void main(String[] args) {
        int[] nums1={1,2,3};
        System.out.println(new Permutations().permute(nums1));
    }
}
