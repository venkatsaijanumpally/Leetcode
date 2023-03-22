package Heap.Medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestElementinaSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        for(int[] arr: matrix){
            for (int x: arr){
                queue.add(x);
                if(queue.size()>k)
                    queue.poll();
            }
        }
        return queue.poll();
    }

    public int kthSmallest2(int[][] matrix, int k) {
        Queue<Integer[]> queue=new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0]-o2[0];
            }
        });

        boolean[][] visited=new boolean[matrix.length][matrix[0].length];
        k--;
        visited[0][0]=true;
        queue.add(new Integer[]{matrix[0][0],0,0});
        while (k>0){
            Integer[] node=queue.poll();
            Integer x=node[1];
            Integer y=node[2];
            if(x+1<matrix.length && y<matrix[0].length && !visited[x+1][y]){
                queue.add(new Integer[]{matrix[x+1][y],x+1,y});
                visited[x+1][y]=true;
            }
            if(x<matrix.length && y+1<matrix[0].length && !visited[x][y+1]){
                queue.add(new Integer[]{matrix[x][y+1],x,y+1});
                visited[x][y+1]=true;
            }
            k--;
        }
        return queue.poll()[0];
    }

    public int kthSmallest3(int[][] matrix, int k) {
        Queue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return matrix[o1[0]][o1[1]]-matrix[o2[0]][o2[1]];
            }
        });

        k--;
        for(int i=0;i<matrix.length;i++){
            queue.add(new int[]{0,i});
        }
        while (k>0){
            int[] node=queue.poll();
            int x=node[0];
            int y=node[1];
            if(x+1<matrix.length)
                queue.add(new int[]{x+1,y});
            k--;
        }
        int[] node=queue.poll();
        return matrix[node[0]][node[1]];
    }

    public static void main(String[] args) {
        int[][] mt={{1,5,9},{10,11,13},{12,13,15}};
        System.out.println(new KthSmallestElementinaSortedMatrix().kthSmallest(mt,8));
        System.out.println(new KthSmallestElementinaSortedMatrix().kthSmallest2(mt,8));
        System.out.println(new KthSmallestElementinaSortedMatrix().kthSmallest2(mt,5));
        System.out.println(new KthSmallestElementinaSortedMatrix().kthSmallest3(mt,8));
        System.out.println(new KthSmallestElementinaSortedMatrix().kthSmallest3(mt,5));
    }
}
