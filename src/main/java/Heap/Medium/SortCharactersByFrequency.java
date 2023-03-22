package Heap.Medium;

import Heap.Utils.CharPair;

import java.util.PriorityQueue;
import java.util.Queue;

public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        int[] count=new int[128];
        for(int i=0;i<s.length();i++){
            count[(int)s.charAt(i)]++;
        }
        Queue<CharPair> queue=new PriorityQueue<>();
        String result="";
        for(int i=0;i<128;i++){
            if(count[i]>0) queue.offer(new CharPair((char) i , count[i]));
        }
        while (!queue.isEmpty()){
            CharPair pair=queue.poll();
            result=result.concat(String.valueOf(pair.c).repeat(pair.count));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SortCharactersByFrequency().frequencySort("tree"));
    }
}
