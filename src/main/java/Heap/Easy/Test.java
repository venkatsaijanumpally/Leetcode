package Heap.Easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {
    public int[] kWeakestRows(int[][] mat, int k){
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0])
                    return o1[1]-o2[1];
                else return o1[0]-o2[0];
            }
        });

        for(int i=0;i< mat.length;i++){
            int count=0;
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==0)
                    break;
                count++;
            }
            queue.add(new int[]{count,i});
        }
        int[] res= new int[k];
        int remaining=queue.size()-k;
        for(int i=0;i<remaining;i++){
            queue.poll();
        }
        k--;
        while (k>=0){
            res[k--]=queue.poll()[1];
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] mat={{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};
        System.out.println(Arrays.toString(new Test().kWeakestRows(mat, 3)));
    }
}
