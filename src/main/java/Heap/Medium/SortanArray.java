package Heap.Medium;

import java.util.PriorityQueue;
import java.util.Queue;

public class SortanArray {
    public int[] sortArray(int[] nums) {
        Queue<Integer> queue=new PriorityQueue<>();
        for (int x:nums)
            queue.add(x);
        int[] result=new int[nums.length];
        for (int i=0;i< nums.length;i++)
            result[i]= queue.poll();
        return result;
    }
}
