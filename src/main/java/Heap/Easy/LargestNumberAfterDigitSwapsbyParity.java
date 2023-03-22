package Heap.Easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LargestNumberAfterDigitSwapsbyParity {
    public int largestInteger(int num) {
        PriorityQueue<Integer> oddQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        PriorityQueue<Integer> evenQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        ArrayList<Boolean> parity = new ArrayList<>();
        int size=0;
        //Parity list is constructed in reverse order
        while (num>0){
            int x=num%10;
            num/=10;
            if(x%2==0) {
                parity.add(true);
                evenQueue.add(x);
            }
            else {
                parity.add(false);
                oddQueue.add(x);
            }
            size++;
        }
        int newNum=0;
        for (int i=size-1;i>=0;i--){
            if(parity.get(i))
                newNum=newNum*10+ evenQueue.poll();
            else newNum=newNum*10 + oddQueue.poll();
        }
        return newNum;
    }

    public static void main(String[] args) {
        System.out.println(new LargestNumberAfterDigitSwapsbyParity().largestInteger(1234));
        System.out.println(new LargestNumberAfterDigitSwapsbyParity().largestInteger(65875));
    }
}
