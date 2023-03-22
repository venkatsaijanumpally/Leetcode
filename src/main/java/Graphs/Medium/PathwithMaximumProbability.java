package Graphs.Medium;

import Graphs.Utils.ProbabilityEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class PathwithMaximumProbability {
    ArrayList<ArrayList<ProbabilityEdge>> adj=new ArrayList<>();
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i< edges.length;i++){
            adj.get(edges[i][0]).add(new ProbabilityEdge(edges[i][1],succProb[i]));
            adj.get(edges[i][1]).add(new ProbabilityEdge(edges[i][0],succProb[i]));
        }

        //MAX PRIORITY QUEUE see ProbabilityEdge CompareTo for implementation
        PriorityQueue<ProbabilityEdge> queue=new PriorityQueue<>();
        double[] t=new double[n];
        Arrays.fill(t,0d);
        t[start]=1d;
        queue.offer(new ProbabilityEdge(start,1d));
        while (!queue.isEmpty()){
            ProbabilityEdge edge=queue.poll();

            if(edge.probability!=t[edge.neighbour]) continue;
            for(ProbabilityEdge neighbour: adj.get(edge.neighbour)){
                double prob= t[edge.neighbour]* neighbour.probability;
                if(t[neighbour.neighbour]<prob){
                    t[neighbour.neighbour]=prob;
                    queue.offer(new ProbabilityEdge(neighbour.neighbour,t[neighbour.neighbour]));
                }
            }
        }
        System.out.println(Arrays.toString(t));
        return t[end];
    }

    public static void main(String[] args) {
        int[][] edges={{0,1},{1,2},{0,2}};
        double[] prob={0.5,0.5,0.2};
        new PathwithMaximumProbability().maxProbability(3,edges,prob,0,2);
    }
}
