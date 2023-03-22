package Graphs.Medium;

import Graphs.Basics.WeightedPair;
import Graphs.Utils.PairWithStops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightsWithinKStops {
    /*
     * Approach: Dijkstra Algorithm
     * The algorithm is modified dijkstra algorithm. Dijkstra algorithm only considers a node if the new distance is
     * lesser than existing one but here we consider 2 situations
     * 1. If distance is less than existing one we add the node to the priority queue and continue just like dijkstra
     * 2. If the number of stops in the new path is less than the existing one then add the node to the queue. This
     *    situation is considered so as to atleast reach the destination even if the distance is more.
     */
    ArrayList<ArrayList<WeightedPair>> adj=new ArrayList<>();
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if(k==0)
            return src==dst?0:-1;
        k++;
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        for(int[] edge: flights){
            adj.get(edge[0]).add(new WeightedPair(edge[1],edge[2]));
        }

        int[][] dist=new int[n][2];
        for(int i=0;i<n;i++)
            for(int j=0;j<2;j++)
                dist[i][j]=Integer.MAX_VALUE;
        Queue<PairWithStops> queue=new PriorityQueue<>();
        queue.offer(new PairWithStops(src,0,0));
        dist[src][0]=0;
        dist[src][1]=0;
        while (!queue.isEmpty()){
            PairWithStops pair= queue.poll();
            //The first appearing destination node will be the least distance out of all
            if(pair.node==dst) return dist[pair.node][0];
            if(k== pair.stops) continue;
            for(WeightedPair neighbour: adj.get(pair.node)){
                int distance=pair.dist+neighbour.dist;
                if(distance<dist[neighbour.node][0]){
                    dist[neighbour.node][0]=distance;
                    dist[neighbour.node][1]= pair.stops+1;
                    queue.offer(new PairWithStops(neighbour.node,distance, dist[neighbour.node][1]));
                }
                else if(pair.stops<dist[neighbour.node][1]){
                    queue.offer(new PairWithStops(neighbour.node,distance, pair.stops+1));
                }
            }
        }
        return dist[dst][0];
    }

    public static void main(String[] args) {
        int[][] flights={{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int[][] flightes2={{0,3,3},{3,4,3},{4,1,3},{0,5,1},{5,1,100},{0,6,2},{6,1,100},{0,7,1},{7,8,1},{8,9,1},{9,1,1},{1,10,1},{10,2,1},{1,2,100}};
        //System.out.println(new CheapestFlightsWithinKStops().findCheapestPrice(4,flights,0,3,1));
        System.out.println(new CheapestFlightsWithinKStops().findCheapestPrice(11,flightes2,0,2,4));
    }
}
