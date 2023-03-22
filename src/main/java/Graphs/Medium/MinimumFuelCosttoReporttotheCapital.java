package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinimumFuelCosttoReporttotheCapital {
    public long minimumFuelCost(int[][] roads, int seats) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        int n= roads.length+1;
        int[] degree=new int[n];
        int[] representatives=new int[n];
        long[] litres=new long[n];


        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        for(int[] road: roads){
            adj.get(road[0]).add(road[1]);
            adj.get(road[1]).add(road[0]);
            degree[road[0]]++;
            degree[road[1]]++;
        }

        Queue<Integer> queue=new LinkedList<>();
        for(int i=1;i<n;i++){
            if(degree[i]==1) queue.offer(i);//Add all nodes with degree 1
        }

        //For all neighbours of a node find the amount of litres required to travel from neighbour to the node.
        while (!queue.isEmpty()){
            int node= queue.poll();
            for(int neighbour: adj.get(node)){
                int rem=(representatives[neighbour]%seats)>0?1:0;
                litres[node]+=(representatives[neighbour]/seats)+rem+litres[neighbour];
                representatives[node]+=representatives[neighbour];
                degree[neighbour]--;
                if(neighbour!=0 && degree[neighbour]==1) queue.offer(neighbour);
            }
            representatives[node]++;
            degree[node]=0;
        }

        for(int neighbour: adj.get(0)){
            int rem=(representatives[neighbour]%seats)>0?1:0;
            litres[0]+=(representatives[neighbour]/seats)+rem+litres[neighbour];
            representatives[0]+=representatives[neighbour];
        }

        System.out.println(Arrays.toString(litres));
        System.out.println(Arrays.toString(representatives));

        System.out.println();
        return litres[0];
    }

    public static void main(String[] args) {
        int[][] roads={{0,1},{0,2},{0,3}};
        int[][] roads2={{3,1},{3,2},{1,0},{0,4},{0,5},{4,6}};
        System.out.println(new MinimumFuelCosttoReporttotheCapital().minimumFuelCost(roads,5));
        System.out.println(new MinimumFuelCosttoReporttotheCapital().minimumFuelCost(roads2,2));
        System.out.println(new MinimumFuelCosttoReporttotheCapital().minimumFuelCost(new int[][]{},1));
    }
}
