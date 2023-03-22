package Graphs.Basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ShortestPathinUndirectedGraphwithUnitWeights {
    int[] dist;
    public void shortestDistance(ArrayList<ArrayList<Integer>> adj, int src, int dest,int N){
        dist=new int[N];
        for(int i=0;i<N;i++)
            dist[i]=Integer.MAX_VALUE;
        dist[src]=0;

        Queue<Integer> queue=new LinkedList<>();
        queue.offer(src);
        while (!queue.isEmpty()){
            int node= queue.poll();
            int distance=dist[node]+1;
            for(int neighbour: adj.get(node)){
                if(dist[neighbour]==Integer.MAX_VALUE || distance<dist[neighbour]){
                    dist[neighbour]=distance;
                    queue.offer(neighbour);
                }
            }
        }

        for(int i=0;i<N;i++){
            if(dist[i]==Integer.MAX_VALUE)
                System.out.print("MAX ");
            else System.out.print(dist[i]+" ");
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<9;i++)
            adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(0).add(3);
        adj.get(1).add(2);
        adj.get(1).add(0);
        adj.get(1).add(3);
        adj.get(3).add(0);
        adj.get(3).add(1);
        adj.get(3).add(4);
        adj.get(2).add(1);
        adj.get(2).add(6);
        adj.get(4).add(3);
        adj.get(4).add(5);
        adj.get(5).add(4);
        adj.get(5).add(6);
        adj.get(6).add(5);
        adj.get(6).add(2);
        adj.get(6).add(7);
        adj.get(6).add(8);
        adj.get(7).add(8);
        adj.get(7).add(6);
        adj.get(8).add(7);
        adj.get(8).add(6);
        new ShortestPathinUndirectedGraphwithUnitWeights().shortestDistance(adj,0,8,9);
    }
}
