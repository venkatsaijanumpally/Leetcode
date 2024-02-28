package Heap.Medium;

import Heap.Utils.DiffPair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {
    /*public int longestSubarray(int[] nums, int limit) {
        PriorityQueue<Integer> minimumQueue = new PriorityQueue<>((o1, o2) -> o1-o2);
        PriorityQueue<Integer> maximumQueue = new PriorityQueue<>((o1, o2) -> o2-o1);

        int max=1;
        int start=0,end;
        for (int i=0;i<nums.length;i++){
            end = i;
            minimumQueue.add(nums[i]);
            maximumQueue.add(nums[i]);

            while (start!=end){
                int currMin = minimumQueue.peek();
                int currMax = maximumQueue.peek();
                int diff = currMax - currMin;
                if(diff<=limit){
                    max = Math.max(max, end-start+1);
                    break;
                }
                else {
                    minimumQueue.remove(nums[start]);
                    maximumQueue.remove(nums[start]);
                    start++;
                }
            }
        }
        return max;
    }*/

    public int longestSubarray(int[] nums, int limit) {
        PriorityQueue<DiffPair> minimumQueue = new PriorityQueue<>((o1, o2) -> o1.value-o2.value);
        PriorityQueue<DiffPair> maximumQueue = new PriorityQueue<>((o1, o2) -> o2.value-o1.value);

        int max=1;
        int start=0,end;
        for (int i=0;i<nums.length && (nums.length - start + 1) > max ;i++){
            end = i;
            DiffPair pair = new DiffPair(nums[i],i);
            minimumQueue.add(pair);
            maximumQueue.add(pair);

            while (start!=end){
                DiffPair currMin = minimumQueue.peek();
                DiffPair currMax = maximumQueue.peek();
                int diff = currMax.value - currMin.value;
                if(diff<=limit){
                    max = Math.max(max, end-start+1);
                    break;
                }
                else {
                    start = Math.min(currMin.index, currMax.index)+1;
                    while (minimumQueue.peek().index<start)
                        minimumQueue.poll();
                    while (maximumQueue.peek().index<start)
                        maximumQueue.poll();
                }
            }
        }
        return max;
    }

    public int longestSubarrayMonotonic(int[] nums, int limit){
        //Monotonic queues
        Deque<Integer> minimumDeque = new ArrayDeque<>();
        Deque<Integer> maximumDeque = new ArrayDeque<>();

        int max=1;
        int start=0;
        for (int end=0;end< nums.length  && (nums.length - start + 1) > max ;end++){
            addToMinimumDeque(nums,end,minimumDeque);
            addToMaximumDeque(nums,end,maximumDeque);
            while (nums[maximumDeque.peek()] - nums[minimumDeque.peek()] > limit){
                start = Math.min(maximumDeque.peek(),minimumDeque.peek())+1;
                removeTillStart(minimumDeque, start);
                removeTillStart(maximumDeque, start);
            }
            max = Math.max(max, end-start+1);
        }
        return max;
    }

    private void removeTillStart(Deque<Integer> deque, int start) {
        while (deque.peek() < start)
            deque.poll();
    }

    private void addToMinimumDeque(int[] nums, int i, Deque<Integer> minimumDeque) {
        while (!minimumDeque.isEmpty() && nums[i]<=nums[minimumDeque.peekLast()]){
            minimumDeque.pollLast();
        }
        minimumDeque.offer(i);
    }

    private void addToMaximumDeque(int[] nums, int i, Deque<Integer> maximumDeque) {
        while (!maximumDeque.isEmpty() && nums[i]>=nums[maximumDeque.peekLast()]){
            maximumDeque.pollLast();
        }
        maximumDeque.offer(i);
    }

    public static void main(String[] args) {
        int[] nums = {8,2,4,7};
        int[] nums2 = {10,1,2,4,7,2};
        int[] nums3 = {4,2,2,2,4,4,2,2};
        System.out.println(new LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit().longestSubarrayMonotonic(nums,4));
        System.out.println(new LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit().longestSubarrayMonotonic(nums2,5));
        System.out.println(new LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit().longestSubarrayMonotonic(nums3,0));
    }
}
