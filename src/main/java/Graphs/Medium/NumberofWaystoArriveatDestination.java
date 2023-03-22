package Graphs.Medium;

import Graphs.Basics.WeightedPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class NumberofWaystoArriveatDestination {
    ArrayList<ArrayList<WeightedPair>> adj;
    int minDistance=Integer.MAX_VALUE;
    int minCount=0;
    public int countPaths(int n, int[][] roads) {
        adj=new ArrayList<>();
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        for(int[] edge: roads){
            adj.get(edge[0]).add(new WeightedPair(edge[1],edge[2]));
            adj.get(edge[1]).add(new WeightedPair(edge[0],edge[2]));
        }

        return dijkstraways(n);
        //return minCount;
    }

    private void dijkstra(int n) {
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<WeightedPair> queue=new PriorityQueue<>();
        queue.offer(new WeightedPair(0,0));
        dist[0]=0;
        while (!queue.isEmpty()){
            WeightedPair node= queue.poll();

            for(WeightedPair neighbour: adj.get(node.node)){
                int distance=dist[node.node]+neighbour.dist;

                if(distance<=dist[neighbour.node]){
                    dist[neighbour.node]=distance;
                    queue.offer(new WeightedPair(neighbour.node,distance));

                    if(neighbour.node==n-1){
                        if(distance==minDistance)
                            minCount++;
                        else if(distance<minDistance){
                            minCount=1;
                            minDistance=distance;
                        }
                    }
                }
            }
        }
    }

    private int dijkstraways(int n) {
        int[] dist=new int[n];
        long[] ways=new long[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<WeightedPair> queue=new PriorityQueue<>();
        queue.offer(new WeightedPair(0,0));
        dist[0]=0;
        ways[0]=1;
        while (!queue.isEmpty()){
            WeightedPair node= queue.poll();

            if(node.dist>dist[node.node])continue;
            for(WeightedPair neighbour: adj.get(node.node)){
                int distance= dist[node.node]+neighbour.dist;

                if(distance<dist[neighbour.node]){
                    dist[neighbour.node]=distance;
                    ways[neighbour.node]=ways[node.node];
                    queue.offer(new WeightedPair(neighbour.node,distance));
                }
                else if(distance==dist[neighbour.node]){
                    ways[neighbour.node]=(ways[neighbour.node]+ways[node.node])%1000000007;
                }
            }
        }
        return (int)ways[n-1];
    }

    public static void main(String[] args) {
        int[][] roads={{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        int[][] roads2={{1,0,10}};
        int[][] roads3={{0,1,1},{1,2,4},{0,4,3},{3,2,5},{3,4,1},{3,0,5},{1,3,1}};
        System.out.println(new NumberofWaystoArriveatDestination().countPaths(7,roads));
        System.out.println(new NumberofWaystoArriveatDestination().countPaths(2,roads2));
        System.out.println(new NumberofWaystoArriveatDestination().countPaths(5,roads3));
    }
}
