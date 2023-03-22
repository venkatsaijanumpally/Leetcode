package Graphs.Medium;

import Graphs.Basics.WeightedPair;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance {
    int minCities=Integer.MAX_VALUE;
    int city=-1;
    ArrayList<ArrayList<WeightedPair>> adj;
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        adj=new ArrayList<>();
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        for(int[] edge: edges){
            adj.get(edge[0]).add(new WeightedPair(edge[1],edge[2]));
            adj.get(edge[1]).add(new WeightedPair(edge[0],edge[2]));
        }

        for(int i=0;i<n;i++){
            dijkstra(i,distanceThreshold,n);
        }
        return city;
    }

    private void dijkstra(int node, int distanceThreshold, int n) {
        PriorityQueue<WeightedPair> queue=new PriorityQueue<>();
        queue.add(new WeightedPair(node,0));
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[node]=0;
        while (!queue.isEmpty()){
            WeightedPair curr= queue.poll();

            for(WeightedPair neighbour:adj.get(curr.node)){
                int distance=dist[curr.node]+neighbour.dist;
                if(distance<=distanceThreshold && distance<dist[neighbour.node]){
                    dist[neighbour.node]=distance;
                    queue.add(new WeightedPair(neighbour.node,distance));
                }
            }
        }

        int count=0;
        for(int i=0;i<n;i++){
            if(dist[i]!=Integer.MAX_VALUE)
                count++;
        }

        if(minCities==count)
            city=node>city?node:city;
        else if(minCities>count){
            city=node;
            minCities=count;
        }
    }

    public static void main(String[] args) {
        int[][] edges={{0,1,2},{0,4,8},{1,2,3},{1,4,2},{2,3,1},{3,4,1}};
        int[][] edges2={{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        System.out.println(new FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance().findTheCity(4,edges2,4));
        System.out.println(new FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance().findTheCity(5,edges,2));
    }
}
