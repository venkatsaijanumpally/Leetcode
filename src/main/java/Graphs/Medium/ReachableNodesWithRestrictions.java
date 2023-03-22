package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ReachableNodesWithRestrictions {
    ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] visited=new boolean[n];
        boolean[] isRestricted=new boolean[n];
        for(int restrictedNode: restricted)
            isRestricted[restrictedNode]=true;
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        for(int[] edge: edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue=new LinkedList<>();
        queue.add(0);
        visited[0]=true;
        int count=1;
        while (!queue.isEmpty()){
            int node= queue.poll();
            for(int neighbour: adj.get(node)){
                if(!visited[neighbour] && !isRestricted[neighbour]){
                    visited[neighbour]=true;
                    queue.offer(neighbour);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] edges1={{0,1},{1,2},{3,1},{4,0},{0,5},{5,6}};
        int[] restricted1={4,5};

        System.out.println(new ReachableNodesWithRestrictions().reachableNodes(7,edges1,restricted1));
    }
}
