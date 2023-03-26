package Heap.Medium;

import java.util.*;

public class ReduceArraySizetoTheHalf {
    public int minSetSize(int[] arr) {
        Queue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int x: arr){
            hm.put(x,hm.getOrDefault(x,0)+1);
        }
        for (Map.Entry<Integer,Integer> entry:hm.entrySet()){
            queue.add(entry.getValue());
        }
        int count=0;
        int size=0;
        while (size< arr.length/2){
            count++;
            size+= queue.poll();
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr1={3,3,3,3,5,5,5,2,2,7};
        System.out.println(new ReduceArraySizetoTheHalf().minSetSize(arr1));
    }
}
