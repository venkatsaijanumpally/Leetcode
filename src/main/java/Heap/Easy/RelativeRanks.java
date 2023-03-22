package Heap.Easy;

import Heap.Utils.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class RelativeRanks {
    public String[] findRelativeRanks(int[] score) {
        Queue<Pair> queue=new PriorityQueue<>();
        for(int i=0;i<score.length;i++){
            queue.add(new Pair(i,score[i]));
        }

        String[] res=new String[score.length];
        int rank=4;
        Pair first=queue.poll();
        res[first.index]="Gold Medal";
        if(!queue.isEmpty()){
            Pair sec=queue.poll();
            res[sec.index]="Silver Medal";
        }
        if(!queue.isEmpty()){
            Pair third=queue.poll();
            res[third.index]="Bronze Medal";
        }

        while (!queue.isEmpty()){
            Pair p=queue.poll();
            res[p.index]=String.valueOf(rank);
            rank++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RelativeRanks().findRelativeRanks(new int[]{10, 3, 8, 9, 4})));
    }
}
