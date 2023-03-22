package Heap.Easy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumAmountofTimetoFillCups {
    public int fillCups(int[] amount) {
        Queue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int i:amount)
            queue.add(i);

        //To remove '0' elements
        while (true){
            int x=queue.poll();
            if(x>0){
                queue.offer(x);
                break;
            }
        }
        int count=0;
        while (queue.size()>1){
            int first= queue.poll();
            int second= queue.poll();
            if(first>1)
                queue.add(first-1);
            if(second>1)
                queue.add(second-1);
            count++;
        }
        if(queue.size()!=0)
            count+= queue.poll();
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumAmountofTimetoFillCups().fillCups(new int[]{1,4,2}));
    }
}
