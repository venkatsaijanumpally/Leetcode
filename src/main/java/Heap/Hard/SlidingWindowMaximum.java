package Heap.Hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {
    Deque<Integer> deque;

    public int[] maxSlidingWindow(int[] nums, int k) {
        deque = new ArrayDeque<>();
        for (int i = 0; i < k - 1; i++) {
            addToDeque(nums, i);
        }

        int[] result = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            addToDeque(nums, i);
            if (deque.peek() <= i - k)
                deque.poll();
            result[i-k+1]=nums[deque.peek()];
        }
        return result;
    }

    private void addToDeque(int[] nums, int index) {
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[index])
            deque.pollLast();
        deque.add(index);
    }

    public static void main(String[] args) {
        int[] nums1= {1,3,-1,-3,5,3,6,7};
        int[] nums2= {7,2,4};
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(nums2, 2)));
    }
}
