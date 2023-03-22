package Graphs.Medium;

import Graphs.Basics.WeightedPair;
import Graphs.Utils.WeightedTwoPair;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinCosttoConnectAllPoints {
    /*
     * Minimum Spanning Tree
     */

    int[] parent;
    int[] rank;
    int minCost=0;
    ArrayList<ArrayList<WeightedTwoPair>> adj;

    public int minCostConnectPoints(int[][] points) {
        adj=new ArrayList<>();
        parent=new int[points.length];
        rank=new int[points.length];

        for(int i=0;i<points.length;i++){
            adj.add(new ArrayList<>());
            parent[i]=i;
            rank[i]=1;
        }

        //Even if it is a undirected graph we are not adding the edge 2 ways i.e from i->j and j->i instead we add only i->j
        // since it a disjoint set implementation.
        PriorityQueue<WeightedTwoPair> queue=new PriorityQueue<>();
        for(int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                int dist=Math.abs(points[i][0]-points[j][0])+Math.abs(points[i][1]-points[j][1]);
                WeightedTwoPair ijpair=new WeightedTwoPair(i,j,dist);
                //WeightedTwoPair jipair=new WeightedTwoPair(j,i,dist);
                adj.get(i).add(ijpair);
                //adj.get(j).add(jipair);
                queue.add(ijpair);
                //queue.add(jipair);
            }
        }

        int count=0;
        while (count!=points.length-1){
            WeightedTwoPair edge= queue.poll();
            if(union(edge))
                count++;
        }
        return minCost;
    }

    private boolean union(WeightedTwoPair edge) {
        int parent1=getParent(edge.node);
        int parent2=getParent(edge.neighbour);

        if(parent1==parent2) return false;
        else {
            if(rank[parent1]==rank[parent2]){
                rank[parent1]++;
                parent[parent2]=parent1;
            }
            else if(rank[parent1]<rank[parent2])
                parent[parent1]=parent2;
            else parent[parent2]=parent1;
        }
        minCost+= edge.dist;
        return true;
    }

    private int getParent(int node) {
        int parentOfNode=parent[node];
        if(parentOfNode==node)
            return node;
        return parent[node]=getParent(parent[node]);
    }
}
