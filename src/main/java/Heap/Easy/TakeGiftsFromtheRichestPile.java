package Heap.Easy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TakeGiftsFromtheRichestPile {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int x : gifts)
            queue.add(x);
        long count = 0;
        while (k > 0) {
            int x = queue.poll();
            x = (int) Math.floor(Math.sqrt(x));
            if (x > 0) queue.offer(x);
            k--;
        }
        for (int x : gifts)
            count += queue.poll();
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {25, 64, 9, 4, 100};
        System.out.println(new TakeGiftsFromtheRichestPile().pickGifts(arr, 4));
    }
}
