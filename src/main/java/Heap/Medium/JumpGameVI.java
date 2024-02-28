package Heap.Medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class JumpGameVI {
    /**
     * Both solutions maxResult and maxResultMonotonic are optimal
     */
    public int maxResult(int[] nums, int k) {
        Integer[] t = new Integer[nums.length];
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> t[o2]-t[o1]);

        t[0]=nums[0];
        queue.add(0);
        for (int i=1;i< nums.length;i++){
            int maxIndex = queue.poll();
            while (maxIndex<i-k){
                maxIndex = queue.poll();
            }
            t[i] = nums[i]+t[maxIndex];
            queue.add(i);
            if(maxIndex!=i-k) queue.add(maxIndex);
        }
        return t[nums.length-1];
    }

    public int maxResultMonotonic(int[] nums, int k){
        int[] t = new int[nums.length];
        Deque<Integer> queue = new ArrayDeque<>();

        t[0]=nums[0];
        queue.add(0);
        int maxIndex;
        for (int i=1;i< nums.length;i++){
            maxIndex = queue.poll();
            if (maxIndex<i-k){
                maxIndex = queue.poll();
            }
            t[i] = nums[i]+t[maxIndex];
            if(maxIndex!=i-k) queue.addFirst(maxIndex);
            addToDeque(i,t,queue,t[i]);
        }
        return t[nums.length-1];
    }

    private void addToDeque(int i, int[] t, Deque<Integer> queue, int val) {
        while (!queue.isEmpty() && t[queue.peekLast()]<val){
            queue.pollLast();
        }
        queue.addLast(i);
    }

    public static void main(String[] args) {
        int[] nums={1,-1,-2,4,-7,3};
        int[] nums2={10,-5,-2,4,0,3};
        System.out.println(new JumpGameVI().maxResultMonotonic(nums2,3));
    }
}
