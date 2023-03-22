package Heap.Easy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumProductofTwoElementsinanArray {
    public int maxProduct(int[] nums) {
        Queue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int i=0;i<nums.length;i++){
            queue.add(nums[i]);
        }
        return (queue.poll()-1)*(queue.poll()-1);
    }

    public static void main(String[] args) {
        int[] nums={3,4,5,2};
        System.out.println(new MaximumProductofTwoElementsinanArray().maxProduct(nums));
    }
}
