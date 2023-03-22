package Heap.Medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementinanArray {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        for(int num:nums){
            queue.add(num);
            if(queue.size()>k)
                queue.poll();
        }
        return queue.poll();
    }


}
