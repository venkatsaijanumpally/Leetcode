package Dynamic.Medium;

import Dynamic.Utils.SortIntegersPair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class SortIntegersbyThePowerValue {
    HashMap<Integer,Integer> t;
    public int getKth(int lo, int hi, int k) {
        t=new HashMap<>();
        t.put(0,0);
        t.put(1,0);
        Queue<SortIntegersPair> queue=new PriorityQueue<>();
        if(lo==1)
            queue.add(new SortIntegersPair(1,1));
        for (int i=((lo==1)?2:lo);i<=hi;i++){
            queue.add(new SortIntegersPair(i,recursion(i)));
            if (queue.size()>k)
                queue.poll();
        }
        return queue.poll().x;
    }

    private int recursion(int x) {
        if(t.get(x)!=null)
            return t.get(x);

        if(x%2==0)
            t.put(x,1+recursion(x/2));
        else t.put(x,1+recursion(3*x+1));
        return t.get(x);
    }

    public static void main(String[] args) {
        System.out.println(new SortIntegersbyThePowerValue().getKth(12,15,2));
    }
}
