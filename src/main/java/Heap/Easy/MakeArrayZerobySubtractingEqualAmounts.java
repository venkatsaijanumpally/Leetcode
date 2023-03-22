package Heap.Easy;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MakeArrayZerobySubtractingEqualAmounts {
    public int minimumOperations(int[] nums) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        for(int x:nums)
            queue.add(x);

        int count=0,sum=0;
        while (!queue.isEmpty()){
            int x=queue.poll();
            if(sum==x)
                continue;
            sum=x;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr={1,5,0,3,5};
        System.out.println(new MakeArrayZerobySubtractingEqualAmounts().minimumOperations(arr));
    }
}
