package Graphs.Hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ParallelCoursesIII {
    ArrayList<ArrayList<Integer>> adj;
    public int minimumTime(int n, int[][] relations, int[] time) {
        adj=new ArrayList<>();
        for (int i=0;i<=n;i++)
            adj.add(new ArrayList<>());
        int[] inDegree=new int[n+1];
        for (int[] edge: relations){
            adj.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }
        int[] result=new int[n+1];
        Queue<Integer> queue=new LinkedList<>();
        int max=0;
        for (int i=1;i<=n;i++){
            if(inDegree[i]==0){
                queue.add(i);
                result[i]=time[i-1];
                max=Math.max(max,result[i]);
            }
        }
        while (!queue.isEmpty()){
            int node= queue.poll();
            for (int neighbour: adj.get(node)){
                inDegree[neighbour]--;
                result[neighbour]=Math.max(result[neighbour],result[node]+time[neighbour-1]);
                max=Math.max(max,result[neighbour]);
                if(inDegree[neighbour]==0)
                    queue.add(neighbour);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] relations1={{1,3},{2,3}};
        int[] time1={3,2,5};
        System.out.println(new ParallelCoursesIII().minimumTime(3,relations1,time1));
    }
}
