package BackTracking.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetsII {
    HashMap<Integer,Integer> hm=new HashMap<>();
    List<List<Integer>> result=new ArrayList<>();
    int n=0;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        for(int i:nums){
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
        recursion(new ArrayList<>(),numCount,0);
        return result;
    }

    private void recursion(ArrayList<Integer> items, int[][] numCount, int index) {
        if(index>=numCount.length){
            result.add(new ArrayList<>(items));
            return;
        }

        recursion(items,numCount,index+1);//Without taking the item
        for(int i=1;i<=numCount[index][1];i++){
            items.add(numCount[index][0]);
            recursion(items,numCount,index+1);
        }
        //Remove all added items
        for(int i=1;i<=numCount[index][1];i++)
            items.remove(items.size()-1);
    }

    public static void main(String[] args) {
        int[] nums1={1,2,2};
        int[] nums2={0};
        System.out.println(new SubsetsII().subsetsWithDup(nums1));
        System.out.println(new SubsetsII().subsetsWithDup(nums2));
    }
}
