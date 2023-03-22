package BackTracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    /*
     * Reason for (i*(i+1)/2)>=target
     * e.g: If i=3 and target=20 then we only have 3+2+1 = 3*(3+1)/2 = 6 < 20 so even if we loop till the last we cant
     * get a solution so we terminate preemptively.
     */
    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        recursion(k,n, new ArrayList<>(),9);
        return result;
    }

    private void recursion(int size, int target, ArrayList<Integer> items, int start) {
        if(size==0){
            if(target==0) result.add(new ArrayList<>(items));
            return;
        }
        if(target==0 || start<1) return;

        for(int i=start;i>0 && (i*(i+1)/2)>=target ;i--){
            if(i<=target){
                items.add(i);
                recursion(size-1,target-i,items,i-1);
                items.remove(items.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSumIII().combinationSum3(3,7));
        System.out.println(new CombinationSumIII().combinationSum3(3,9));
    }
}
