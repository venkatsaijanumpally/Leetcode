package Heap.Easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TheKWeakestRowsinaMatrix {
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) ->
                (a[0] != b[0] ?
                        b[0] - a[0] :
                        b[1] - a[1])
        );

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

    public int[] kWeakestRows2(int[][] mat, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]!=o2[1]?o2[1]-o1[1]:o2[0]-o1[0];
            }
        });
        int pos=0;
        for (int[] row : mat){
            int lo = 0, hi = row.length;
            while(lo < hi){
                int mid = (lo + hi) / 2;
                if (row[mid] != 0) lo = mid + 1;
                else hi = mid;
            }
            q.add(new int[]{pos++,lo});
            if(q.size()>k) q.poll();
        }

        int[] output = new int[k];
        for(int i = k-1; i >= 0; i--)  output[i] = q.remove()[0];
        return output;
    }

    public static void main(String[] args) {
        int[][] mat={{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};
        System.out.println(Arrays.toString(new TheKWeakestRowsinaMatrix().kWeakestRows(mat, 3)));
        System.out.println(Arrays.toString(new TheKWeakestRowsinaMatrix().kWeakestRows2(mat, 3)));
    }
}
