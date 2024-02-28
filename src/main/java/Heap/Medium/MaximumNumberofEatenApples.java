package Heap.Medium;

import Heap.Utils.AppleExpiry;

import java.util.PriorityQueue;

public class MaximumNumberofEatenApples {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<AppleExpiry> queue = new PriorityQueue<>(); //Least expiry on top

        int sum=0;
        for (int i=0;i<apples.length;i++){
            if(apples[i]>0)
                queue.add(new AppleExpiry(i+days[i]-1,apples[i]));
            while (!queue.isEmpty()){
                //Find the apple that has not rotten but just peek
                if(queue.peek().day>=i){
                    AppleExpiry appleExpiry = queue.peek();
                    sum++;
                    appleExpiry.count--;
                    if(appleExpiry.count<1)
                        queue.poll();
                    break;
                }
                queue.poll();
            }
        }

        //Removing remaining apples
        int day = apples.length;
        while (!queue.isEmpty()){
            while (!queue.isEmpty()){
                if(queue.peek().day>=day){
                    AppleExpiry appleExpiry = queue.peek();
                    sum++;
                    appleExpiry.count--;
                    if(appleExpiry.count<1)
                        queue.poll();
                    break;
                }
                queue.poll();
            }
            day++;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] apples1={1,2,3,5,2};
        int[] days1 = {3,2,1,4,2};
        System.out.println(new MaximumNumberofEatenApples().eatenApples(apples1,days1));
    }
}
