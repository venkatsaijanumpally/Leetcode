package Graphs.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {
    //TODO With BITMASK
    public int shortestPathLength(int[][] graph) {
        HashMap<Integer, ArrayList<Integer>> adj=new HashMap<>();
        boolean[] visited=new boolean[graph.length];

        for(int i=0;i<graph.length;i++)
            adj.put(i,new ArrayList<>());

        for(int i=0;i< graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                adj.get(i).add(graph[i][j]);
            }
        }

        Queue<Integer> queue=new LinkedList<>();
        queue.add(0);
        visited[0]=true;
        int dist=0;
        while (!queue.isEmpty()){
            int node= queue.poll();
            for(int neighbour: adj.get(node)){
                if(!visited[neighbour]){
                    dist++;
                    queue.add(neighbour);
                    visited[neighbour]=true;
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] adj={{1},{0,2,4},{1,3,4},{2},{1,2}};
        System.out.println(new ShortestPathVisitingAllNodes().shortestPathLength(adj));
    }
}
