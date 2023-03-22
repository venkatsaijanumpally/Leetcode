package Graphs.Basics;

import java.util.ArrayList;
import java.util.Stack;

public class ShortestPathinDirectedAcyclicGraph {
    boolean[] visited;
    public void shortestPath(ArrayList<ArrayList<WeightedPair>> adj, int src, int N){
        visited=new boolean[N];
        Stack<Integer> stack=new Stack<>();
        int[] dist=new int[N];

        for(int i=0;i<N;i++)
            if(!visited[i])
                topologicalSort(adj, i, stack);

        for(int i=0;i<N;i++)
            dist[i]=Integer.MAX_VALUE;
        dist[src]=0;
        while (!stack.isEmpty()){
            int node=stack.pop();

            if(dist[node]!=Integer.MAX_VALUE){
                for(WeightedPair pair: adj.get(node)){
                    int distance=dist[node]+pair.dist;
                    if(dist[pair.node]==Integer.MAX_VALUE || distance<dist[pair.node])
                        dist[pair.node]=distance;
                }
            }
        }

        for(int i=0;i<N;i++){
            if(dist[i]==Integer.MAX_VALUE)
                System.out.print("INF"+" ");
            else System.out.print(dist[i]+" ");
        }
    }

    //Topological sort
    //Here we are not handling cycle using dfsVisisted array as it is a DAG
    private void topologicalSort(ArrayList<ArrayList<WeightedPair>> adj, int node, Stack<Integer> stack) {
        visited[node]=true;

        for(WeightedPair neighbour: adj.get(node)){
            if(!visited[neighbour.node]){
                topologicalSort(adj, neighbour.node, stack);
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<WeightedPair>> adj=new ArrayList<>();
        for(int i=0;i<6;i++)
            adj.add(new ArrayList<>());

        adj.get(0).add(new WeightedPair(1,2));
        adj.get(0).add(new WeightedPair(4,1));
        adj.get(1).add(new WeightedPair(2,3));
        adj.get(2).add(new WeightedPair(3,6));
        adj.get(4).add(new WeightedPair(2,2));
        adj.get(4).add(new WeightedPair(5,4));
        adj.get(5).add(new WeightedPair(3,1));

        new ShortestPathinDirectedAcyclicGraph().shortestPath(adj,0,6);
    }
}
