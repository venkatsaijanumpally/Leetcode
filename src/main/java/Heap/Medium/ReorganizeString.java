package Heap.Medium;

import Heap.Utils.CharPair;

import java.util.PriorityQueue;
import java.util.Queue;

public class ReorganizeString {
    public String reorganizeString(String s) {
        Queue<CharPair> queue = new PriorityQueue<>();
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCount[(int) s.charAt(i) - 97]++;
        }
        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0)
                queue.offer(new CharPair((char) (97 + i), charCount[i]));
        }
        StringBuilder result=new StringBuilder();
        while (queue.size()>1){
            CharPair c1=queue.poll();
            CharPair c2=queue.poll();
            result.append(c1.c).append(c2.c);
            c1.count--;
            c2.count--;
            if(c1.count>0)
                queue.offer(c1);
            if(c2.count>0)
                queue.offer(c2);
        }
        if(queue.size()==1){
            CharPair c=queue.poll();
            if(c.count==1)
                return result.append(c.c).toString();
            return "";
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ReorganizeString().reorganizeString("aab"));
        System.out.println(new ReorganizeString().reorganizeString("aaab"));
    }
}
