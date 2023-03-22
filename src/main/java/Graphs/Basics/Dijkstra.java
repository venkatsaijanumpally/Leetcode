package Graphs.Basics;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {
    /*
     * Approach: Dijkstra algorithm
     */
    ArrayList<ArrayList<WeightedPair>> adj=new ArrayList<>();
    public int dijkstra(int n, int[][] flights, int src) {
        for(int i=0;i<=n;i++)
            adj.add(new ArrayList<>());
        for(int[] edge:flights)
            adj.get(edge[0]).add(new WeightedPair(edge[1],edge[2]));

        int[] dist=new int[n+1];
        for(int i=0;i<=n;i++)
            dist[i]=Integer.MAX_VALUE;
        dist[src]=0;

        PriorityQueue<WeightedPair> queue=new PriorityQueue<>();
        queue.add(new WeightedPair(src,0));
        while (!queue.isEmpty()){
            WeightedPair node= queue.poll();

            for(WeightedPair neighbour: adj.get(node.node)){
                if(dist[neighbour.node]>dist[node.node]+ neighbour.dist){
                    dist[neighbour.node]=dist[node.node]+ neighbour.dist;
                    queue.add(new WeightedPair(neighbour.node, dist[neighbour.node]));
                }
            }
        }
        for(int i=1;i<n+1;i++)
            System.out.print(dist[i]+" ");
        return 0;
    }

    public static void main(String[] args) {
        int[][] flights={{1,2,100},{2,3,100},{3,1,100},{2,4,600},{3,4,200}};
        new Dijkstra().dijkstra(4,flights,1);
    }
}
