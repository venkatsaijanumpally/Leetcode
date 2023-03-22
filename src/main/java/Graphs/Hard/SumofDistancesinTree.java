package Graphs.Hard;

import Graphs.Utils.NodeDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class SumofDistancesinTree {
    ArrayList<ArrayList<Integer>> adj;
    int[] child;
    int[] result;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {

        adj=new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        child=new int[n];
        result=new int[n];
        for(int[] edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited=new boolean[n];
        dfsForNodeZero(visited,0,0);
        dfsForOtherNodes(new boolean[n],0,n);
        return result;
    }

    private void dfsForNodeZero(boolean[] visited, int node,int length) {
        visited[node]=true;
        result[0]+=length;
        for(int neighbour: adj.get(node)){
            if(!visited[neighbour]){
                dfsForNodeZero(visited,neighbour,length+1);
                child[node]+=child[neighbour];
            }
        }
        child[node]++;
    }

    private void dfsForOtherNodes(boolean[] visited, int node, int n) {
        visited[node]=true;
        for(int neighbour: adj.get(node)){
            if(!visited[neighbour]){
                result[neighbour]=result[node]-child[neighbour]+n-child[neighbour];
                dfsForOtherNodes(visited,neighbour,n);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges={{0,1},{0,2},{2,3},{2,4},{2,5}};
        System.out.println(Arrays.toString(new SumofDistancesinTree().sumOfDistancesInTree(6, edges)));
    }
}
