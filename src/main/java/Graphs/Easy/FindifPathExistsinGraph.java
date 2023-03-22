package Graphs.Easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FindifPathExistsinGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=n;i++)
            adj.add(new ArrayList<>());
        for(int i=0;i< edges.length;i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        boolean[] visited=new boolean[n+1];
        Queue<Integer> queue=new LinkedList<>();
        visited[source]=true;
        queue.add(source);
        while (!queue.isEmpty()){
            int node= queue.poll();
            if(node==destination) return true;

            for(int c:adj.get(node)){
                if(!visited[c]){
                    queue.add(c);
                    visited[c]=true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr1={{1,2},{1,3},{1,5},{2,3},{3,5},{2,4},{3,4},{6,7}};
        System.out.println(new FindifPathExistsinGraph().validPath(7,arr1,1,6));
        System.out.println(new FindifPathExistsinGraph().validPath(7,arr1,1,4));
    }
}
