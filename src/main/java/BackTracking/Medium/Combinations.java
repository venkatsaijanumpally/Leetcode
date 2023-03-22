package BackTracking.Medium;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    List<List<Integer>> combinations=new ArrayList<>();
    int n=0;
    public List<List<Integer>> combine(int n, int k) {
        ArrayList<Integer> nums=new ArrayList<>();
        for(int i=1;i<=n;i++)
            nums.add(i);
        this.n=n;
        recursion(new ArrayList<>(),k,1);
        return combinations;
    }

    private void recursion(ArrayList<Integer> items, int size,int index) {
        if(size==0){
            combinations.add(new ArrayList<>(items));
            return;
        }
        if(index==n+1)
            return;

        for(int i=index;i<=n;i++){
            items.add(i);
            recursion(items,size-1,i+1);
            items.remove(items.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Combinations().combine(4,2));
        System.out.println(new Combinations().combine(1,1));
    }
}
