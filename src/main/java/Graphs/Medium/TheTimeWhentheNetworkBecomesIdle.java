package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TheTimeWhentheNetworkBecomesIdle {
    ArrayList<ArrayList<Integer>> adj;
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        adj=new ArrayList<>();
        int n=patience.length;
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());

        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue=new LinkedList<>();
        queue.add(0);
        boolean[] visited=new boolean[n];
        int[] time=new int[n];
        visited[0]=true;
        //Run BFS which gives shortest paths as it is unweighted
        while (!queue.isEmpty()){
            int node= queue.poll();
            for(int neighbour: adj.get(node)){
                if(!visited[neighbour]){
                    time[neighbour]=time[node]+1;
                    visited[neighbour]=true;
                    queue.add(neighbour);
                }
            }
        }
        //System.out.println(Arrays.toString(time));

        int max=0;
        //Using time[i] and patience[i] calculate the time taken for a node 'i' to be idle
        //If remainder is greater then no new message will be sent
        //If remainder if greater than zero then the the last message is sent by reminder time
        //If reminder = 0 then last message is sent patience[i] time back.
        for(int i=1;i<n;i++){
            int totalDist=time[i]*2;
            int temp=totalDist+1;
            int rem=totalDist%patience[i];
            if(patience[i]>totalDist){}
            else if(rem>0)
                temp+=totalDist-rem;
            else
                temp+=totalDist-patience[i];
            max=Math.max(max,temp);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] edges={{0,1},{1,2},{2,3},{3,4}};
        int[] patience={0,2,5,4,5};
        int[][] edges2={{0,1},{0,2},{1,2}};
        int[] patience2={0,2,1};
        System.out.println(new TheTimeWhentheNetworkBecomesIdle().networkBecomesIdle(edges,patience));
        System.out.println(new TheTimeWhentheNetworkBecomesIdle().networkBecomesIdle(edges2,patience2));
    }
}
