package Graphs.Basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleUndirectedBFS {
    public boolean hasCycleBFS(int n, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[n+1];

        for(int i=1;i<=n;i++){
            if(!visited[i]){
                if(checkComponentCycle(adj, visited, i))
                    return true;
            }
        }
        return false;
    }

    private boolean checkComponentCycle(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int currnode) {
        Queue<Node> queue=new LinkedList<>();
        visited[currnode]=true;
        queue.offer(new Node(currnode,-1));

        while (!queue.isEmpty()){
            Node node=queue.poll();

            for(int child:adj.get(node.current)){
                if(child!=node.parent){
                    if(visited[child])
                        return true;
                    else {
                        queue.offer(new Node(child,node.current));
                        visited[child]=true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{2,4},{3,5},{5,10},{5,6},{10,9},{6,7},{9,8},{7,8},{8,11}};
        int[][] arr1={{1,2},{2,4},{3,5},{5,10},{5,6},{10,9},{6,7},{9,11},{7,8}};
        ArrayList<ArrayList<Integer>> adj=new GraphRepresentation().graphAdjacencyListUndirected(11,10,arr);
        ArrayList<ArrayList<Integer>> adj1=new GraphRepresentation().graphAdjacencyListUndirected(11,9,arr1);
        System.out.println(new DetectCycleUndirectedBFS().hasCycleBFS(11,adj));
        System.out.println(new DetectCycleUndirectedBFS().hasCycleBFS(11,adj1));
    }
}
