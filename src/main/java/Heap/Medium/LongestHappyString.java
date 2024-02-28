package Heap.Medium;

import Heap.Utils.HappyPair;

import java.util.PriorityQueue;
import java.util.Queue;

public class LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        Queue<HappyPair> queue = new PriorityQueue<>();
        if(a>0)
            queue.add(new HappyPair('a',a));
        if(b>0)
            queue.add(new HappyPair('b',b));
        if(c>0)
            queue.add(new HappyPair('c',c));

        StringBuilder result = new StringBuilder();
        char prev = 'd';
        while (queue.size()>=2){
            HappyPair first = queue.poll();
            HappyPair second = queue.poll();

            concatChar(result, first, prev, 2);
            concatChar(result, second, first.character, 1);

            prev = second.character;
            if(first.count != 0)
                queue.offer(first);
            if(second.count!=0)
                queue.offer(second);
        }
        if(!queue.isEmpty()){
            HappyPair pair = queue.poll();
            if(prev!=pair.character){
                int count = 2;
                while (count>0 && pair.count>0){
                    result.append(pair.character);
                    count--;
                    --pair.count;
                }
            }
            else {
                result.append(pair.character);
            }
        }
        return result.toString();
    }

    private void concatChar(StringBuilder result, HappyPair pair, char prev, int count) {
        if(count==2 && prev!= pair.character){
            while (count>0 && pair.count>0){
                result.append(pair.character);
                pair.count--;
                --count;
            }
        }
        else {
            result.append(pair.character);
            --pair.count;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestHappyString().longestDiverseString(1,1,7));
    }
}
