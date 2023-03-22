package Graphs.Basics;

import java.util.ArrayList;

public class DetectCycleUndirectedDFS {
    public boolean hasCycleBFS(int n, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[n+1];

        for(int i=1;i<=n;i++){
            if(!visited[i]){
                if(recursive(adj, visited, i,-1))
                    return true;
            }
        }
        return false;
    }

    private boolean recursive(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int currentnode, int parent) {
        visited[currentnode]=true;
        System.out.print(currentnode+" ");
        for(int child: adj.get(currentnode)){
            if(!visited[child]){
                if(recursive(adj,visited,child, currentnode))
                    return true;
            }
            else if(child!= parent) return true;
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] arr={{1,2},{2,4},{3,5},{5,10},{5,6},{10,9},{6,7},{9,8},{7,8},{8,11}};
        int[][] arr1={{1,2},{2,4},{3,5},{5,10},{5,6},{10,9},{6,7},{9,11},{7,8}};
        ArrayList<ArrayList<Integer>> adj=new GraphRepresentation().graphAdjacencyListUndirected(11,10,arr);
        ArrayList<ArrayList<Integer>> adj1=new GraphRepresentation().graphAdjacencyListUndirected(11,9,arr1);
        System.out.println(new DetectCycleUndirectedDFS().hasCycleBFS(11,adj));
        System.out.println(new DetectCycleUndirectedDFS().hasCycleBFS(11,adj1));
    }
}
