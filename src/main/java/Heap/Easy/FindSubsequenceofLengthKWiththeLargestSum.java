package Heap.Easy;

import java.util.*;

public class FindSubsequenceofLengthKWiththeLargestSum {
    //Both are optimal
    public int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        for(int i=0;i< nums.length;i++){
            queue.offer(nums[i]);
            if(queue.size()>k)
                queue.poll();
        }
        int[] result=new int[k];
        HashMap<Integer,Integer> hm=new HashMap<>();
        int pos=0;
        while (queue.size()>0){
            int x=queue.poll();
            hm.put(x,hm.getOrDefault(x,0)+1);
        }
        for(int i=0;i< nums.length;i++){
            if(hm.containsKey(nums[i])){
                result[pos++]=nums[i];
                hm.put(nums[i],hm.get(nums[i])-1);
                if(hm.get(nums[i])==0)
                    hm.remove(nums[i]);
            }
        }
        return result;
    }

    public int[] maxSubsequence2(int[] nums, int k) {
        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });

        for(int i=0;i< nums.length;i++){
            queue.offer(new int[]{i,nums[i]});
            if(queue.size()>k)
                queue.poll();
        }
        int[] result=new int[k];
        int pos=0;

        PriorityQueue<int[]> queue2=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        while (queue.size()>0){
            queue2.offer(queue.poll());
        }
        while (queue2.size()>0){
            result[pos++]=queue2.poll()[1];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr={2,1,3,3};
        System.out.println(Arrays.toString(new FindSubsequenceofLengthKWiththeLargestSum().maxSubsequence(arr, 2)));
        System.out.println(Arrays.toString(new FindSubsequenceofLengthKWiththeLargestSum().maxSubsequence2(arr, 2)));
    }
}
