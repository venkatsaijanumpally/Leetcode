package Heap.Easy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int stone:stones)
            queue.add(stone);
        while (!queue.isEmpty() && queue.size()!=1){
            int x= queue.poll();//x may be bigger
            int y= queue.poll();
            if(x==y)
                continue;
            else
                queue.offer(x-y);
        }

        if(!queue.isEmpty())
            return queue.poll();
        return 0;
    }

    public static void main(String[] args) {
        int[] stones1={2,7,4,1,8,1};
        System.out.println(new LastStoneWeight().lastStoneWeight(stones1));
    }
}
