package Graphs.Basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {
    public String bfs(int nodes, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[nodes+1];
        StringBuilder sb=new StringBuilder();

        //This loop runs for each component of a graph
        for(int i=1;i<=nodes;i++){
            if(!visited[i]){
                Queue<Integer> queue=new LinkedList<>();
                queue.add(i);
                visited[i]=true;
                while (!queue.isEmpty()){
                    int node= queue.poll();
                    /*if(!visited[node]){
                        sb.append(node).append(" ");
                        visited[node]=true;
                        queue.addAll(adj.get(node));
                    }*/

                    sb.append(node).append(" ");
                    for(int c: adj.get(node)){
                        if(!visited[c]){
                            visited[c]=true;
                            queue.add(c);
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{1,3},{1,5},{2,3},{3,5},{2,4},{3,4}};
        ArrayList<ArrayList<Integer>> adj=new GraphRepresentation().graphAdjacencyListUndirected(5,7,arr);
        int[][] arr1={{1,2},{1,3},{1,5},{2,3},{3,5},{2,4},{3,4},{6,7}};
        ArrayList<ArrayList<Integer>> adj1=new GraphRepresentation().graphAdjacencyListUndirected(7,8,arr1);
        System.out.println(new GraphBFS().bfs(5,adj));
        System.out.println(new GraphBFS().bfs(7,adj1));
    }
}
