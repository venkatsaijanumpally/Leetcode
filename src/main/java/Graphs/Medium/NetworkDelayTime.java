package Graphs.Medium;

import Graphs.Basics.WeightedPair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NetworkDelayTime {
    ArrayList<ArrayList<WeightedPair>> adj=new ArrayList<>();
    int[] dist;
    /*
     * Method 1: DFS
     */
    public int networkDelayTimeDFS(int[][] times, int n, int k) {
        dist=new int[n+1];
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
            dist[i]=Integer.MAX_VALUE;
        }
        for(int[] edge:times)
            adj.get(edge[0]).add(new WeightedPair(edge[1],edge[2]));
        dfs(k,0);
        int max=-1;
        for(int i=1;i<=n;i++)
            max=Math.max(dist[i],max);
        if(max==Integer.MAX_VALUE)
            return -1;
        return max;
    }

    private void dfs(int node, int distTravelled) {
        dist[node]=distTravelled;

        for(WeightedPair neighbour: adj.get(node)){
            int distance=distTravelled+neighbour.dist;
            if(distance<dist[neighbour.node])
                dfs(neighbour.node,distance);
        }
    }

    /*
     * Method 2: BFS
     */
    public int networkDelayTimeBFS(int[][] times, int n, int k) {
        dist=new int[n+1];
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
            dist[i]=Integer.MAX_VALUE;
        }
        for(int[] edge:times)
            adj.get(edge[0]).add(new WeightedPair(edge[1],edge[2]));

        Queue<WeightedPair> queue=new LinkedList<>();
        queue.offer(new WeightedPair(k,0));
        dist[k]=0;
        while (!queue.isEmpty()){
            WeightedPair curr= queue.poll();

            for(WeightedPair neighbour: adj.get(curr.node)){
                int distance=curr.dist+neighbour.dist;
                if(distance<dist[neighbour.node]){
                    dist[neighbour.node]=distance;
                    queue.offer(new WeightedPair(neighbour.node,distance));
                }
            }
        }


        int max=-1;
        for(int i=1;i<=n;i++)
            max=Math.max(dist[i],max);
        if(max==Integer.MAX_VALUE)
            return -1;
        return max;
    }


    /*
     * Method 3: BFS with Priority queue
     */
    //!Optimal Solution
    public int networkDelayTimePriority(int[][] times, int n, int k) {
        dist=new int[n+1];
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
            dist[i]=Integer.MAX_VALUE;
        }
        for(int[] edge:times)
            adj.get(edge[0]).add(new WeightedPair(edge[1],edge[2]));

        PriorityQueue<WeightedPair> queue=new PriorityQueue<>();
        queue.offer(new WeightedPair(k,0));
        dist[k]=0;
        while (!queue.isEmpty()){
            WeightedPair curr= queue.poll();

            for(WeightedPair neighbour: adj.get(curr.node)){
                int distance=curr.dist+neighbour.dist;
                if(distance<dist[neighbour.node]){
                    dist[neighbour.node]=distance;
                    queue.offer(new WeightedPair(neighbour.node,distance));
                }
            }
        }


        int max=-1;
        for(int i=1;i<=n;i++)
            max=Math.max(dist[i],max);
        if(max==Integer.MAX_VALUE)
            return -1;
        return max;
    }

    public static void main(String[] args) {
        int[][] times={{2,1,1},{2,3,1},{3,4,1}};
        int[][] times2={{1,2,1}};
        System.out.println(new NetworkDelayTime().networkDelayTimeDFS(times,4,2));
        System.out.println(new NetworkDelayTime().networkDelayTimeDFS(times2,2,1));
        System.out.println(new NetworkDelayTime().networkDelayTimeDFS(times2,2,2));

        System.out.println(new NetworkDelayTime().networkDelayTimeBFS(times,4,2));
        System.out.println(new NetworkDelayTime().networkDelayTimeBFS(times2,2,1));
        System.out.println(new NetworkDelayTime().networkDelayTimeBFS(times2,2,2));

        System.out.println(new NetworkDelayTime().networkDelayTimePriority(times,4,2));
        System.out.println(new NetworkDelayTime().networkDelayTimePriority(times2,2,1));
        System.out.println(new NetworkDelayTime().networkDelayTimePriority(times2,2,2));
    }
}
