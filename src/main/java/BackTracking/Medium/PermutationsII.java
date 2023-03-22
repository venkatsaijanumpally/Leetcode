package BackTracking.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutationsII {
    /*
     * Maintain a hashmap and run permutation for every entry.
     */
    HashMap<Integer,Integer> hm=new HashMap<>();
    List<List<Integer>> result=new ArrayList<>();
    int n;
    public List<List<Integer>> permuteUnique(int[] nums) {
        n=nums.length;
        for(int i:nums){
            if(!hm.containsKey(i))
                hm.put(i,1);
            else hm.put(i,hm.get(i)+1);
        }

        recursion(new ArrayList<>(),0);
        return result;
    }

    private void recursion(ArrayList<Integer> items, int size) {
        if(size==n){
            result.add(new ArrayList<>(items));
            return;
        }

        for(Map.Entry<Integer,Integer> entry: hm.entrySet()){
            int count=entry.getValue();
            if(count==0)
                continue;
            int key=entry.getKey();

            items.add(key);
            hm.put(key,count-1);
            recursion(items,size+1);
            items.remove(items.size()-1);
            hm.put(key,count);
        }
    }

    public static void main(String[] args) {
        int[] nums1={1,1,2};
        System.out.println(new PermutationsII().permuteUnique(nums1));
    }
}
