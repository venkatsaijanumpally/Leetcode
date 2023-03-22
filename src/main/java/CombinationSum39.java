import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CombinationSum39 {
    public static void main(String[] args) throws IOException {
        int[] arr={2,3,6,7};
        /*ArrayList<Integer> ar=new ArrayList<>();
        ar.add(2);
        ar.add(2);
        ar.add(3);
        result.add(ar);
        ar.remove(ar.size()-1);
        ar.remove(ar.size()-1);
        ar.remove(ar.size()-1);
        ar.add(7);
        result.add(ar);
        ar.remove(ar.size()-1);
        ArrayList<Integer> arint=new ArrayList<>();
        int i=5;
        arint.add(i);
        i=10;
        arint.add(i);*/

        combinationSum(arr, 7, new ArrayList<>(), 0, 0);
        System.out.println("\nRESULT"+result);
    }

    static ArrayList<List<Integer>> result = new ArrayList<>();
    public static void combinationSum(int[] candidates, int target, ArrayList<Integer> list, int sum, int index) {
        if(sum==target){
           result.add(list);
            System.out.println(result);
           return;
        }
        if(sum>target || index>candidates.length-1)
            return;
        list.add(candidates[index]);
        combinationSum(candidates, target, new ArrayList<>(list), sum+candidates[index], index);
        list.remove(list.size()-1);
        combinationSum(candidates, target, new ArrayList<>(list), sum, index+1);
    }
}
