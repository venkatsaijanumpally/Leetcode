package Graphs.Medium;

import java.util.ArrayList;
import java.util.List;

public class AllAncestorsofaNodeinaDirectedAcyclicGraph {
    List<List<Integer>> result=new ArrayList<>();
    List<List<Integer>> adj=new ArrayList<>();
    boolean[] currentVisited;
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        for(int i=0;i<n;i++){
            result.add(new ArrayList<>());
            adj.add(new ArrayList<>());
        }
        for(int[] edge: edges)
            adj.get(edge[0]).add(edge[1]);
        for(int i=0;i<n;i++){
            currentVisited=new boolean[n];
            for(int neighbour: adj.get(i)){
                dfs(neighbour, i);
            }
        }
        return result;
    }

    private void dfs(int node, int parent) {
        if(!currentVisited[node]){
            currentVisited[node]=true;
            result.get(node).add(parent);
            for(int neighbour: adj.get(node)){
                dfs(neighbour, parent);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr={{0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        System.out.println(new AllAncestorsofaNodeinaDirectedAcyclicGraph().getAncestors(8,arr));
    }
}
