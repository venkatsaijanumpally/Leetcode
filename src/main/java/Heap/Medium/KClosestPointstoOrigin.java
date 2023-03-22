package Heap.Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o2[0]*o2[0]) + (o2[1]*o2[1]) - ((o1[0]*o1[0]) + (o1[1]*o1[1]));
            }
        });

        for(int[] point:points){
            queue.add(point);
            if(queue.size()>k)
                queue.poll();
        }
        return queue.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] points1={{1,3},{-2,2}};
        int[][] points2={{-5,4},{-6,-5},{4,6}};
        //System.out.println(Arrays.deepToString(new KClosestPointstoOrigin().kClosest(points1, 1)));
        System.out.println(Arrays.deepToString(new KClosestPointstoOrigin().kClosest(points2, 2)));
    }
}
