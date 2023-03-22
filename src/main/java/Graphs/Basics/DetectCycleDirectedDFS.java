package Graphs.Basics;

import java.util.ArrayList;

public class DetectCycleDirectedDFS {
    /*
     * https://www.youtube.com/watch?v=uzVUw90ZFIg&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=12
     * We maintain visited and dfsVisited arrays
     * visited array: track all visited nodes
     * dfsVisisted array: track all visited nodes in current path.
     */
    public boolean hasCycleDFS(int n, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[n+1];
        boolean[] dfsVisited=new boolean[n+1];

        //The below code is not the loop for every component as seen in Undirected graph see video for reason
        for(int i=1;i<=n;i++){
            if(!visited[i]){
                if(recursive(adj, visited, dfsVisited, i))
                    return true;
            }
        }
        return false;
    }

    private boolean recursive(ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] dfsVisited, int currnode) {
        visited[currnode]=true;
        dfsVisited[currnode]=true;

        /*
         * In this loop we check if there is a cycle
         * Case 1: If the child is not visited then we explore that child.
         * Case 2: If the child is already visited by current path then there is a cycle.
         * Case 3: If the child is already visited by another path then there is no point exploring that child since we
         * already found that child does not have any cycle. In this case we ignore the child and move to next child.
         */
        for(int child: adj.get(currnode)){
            if(!visited[child]){
                if(recursive(adj,visited,dfsVisited,child))
                    return true;
            }
            else if(dfsVisited[child])
                return true;
        }

        dfsVisited[currnode]=false;
        return false;
    }

    public static void main(String[] args) {
        int[][] arr={{2,1}};
        int[][] arr1={{2,1},{1,2}};
        int[][] arr2={{2, 1}, {3, 2}, {4, 3}};
        ArrayList<ArrayList<Integer>> adj=new GraphRepresentation().graphAdjacencyListDirected(2,1,arr);
        ArrayList<ArrayList<Integer>> adj1=new GraphRepresentation().graphAdjacencyListDirected(2,2,arr1);
        ArrayList<ArrayList<Integer>> adj2=new GraphRepresentation().graphAdjacencyListDirected(4,3,arr2);
        System.out.println(new DetectCycleDirectedDFS().hasCycleDFS(2,adj));
        System.out.println(new DetectCycleDirectedDFS().hasCycleDFS(2,adj1));
        System.out.println(new DetectCycleDirectedDFS().hasCycleDFS(4,adj2));
    }
}
