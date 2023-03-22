package Graphs.Basics;

import java.util.ArrayList;

public class BipartiteGraphColouringDFS {
    /*
     * https://www.youtube.com/watch?v=uC884ske2uQ&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=11
     */
    public boolean colouringPossible(int n, ArrayList<ArrayList<Integer>> adj){
        int[] colour= new int[n+1];
        boolean[] visited=new boolean[n+1];

        for(int i=1;i<=n;i++){
            if(!visited[i]){
                visited[i]=true;
                colour[i]=1;
                if(!recursive(visited,colour,i,adj))
                    return false;
            }
        }
        return true;
    }

    private boolean recursive(boolean[] visited, int[] colour, int curr, ArrayList<ArrayList<Integer>> adj) {
        for(int child: adj.get(curr)){
            if(colour[child]==0){
                visited[child]=true;
                if(colour[curr]==1) colour[child]=2;
                else colour[child]=1;
                if(!recursive(visited,colour,child,adj))
                    return false;
            }
            else if(colour[child]==colour[curr])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{2,3},{3,4},{4,5},{2,7},{7,6},{6,5},{5,8}};
        ArrayList<ArrayList<Integer>> adj=new GraphRepresentation().graphAdjacencyListUndirected(8,8,arr);
        int[][] arr1={{1,2},{2,3},{3,4},{4,5},{2,7},{7,5},{5,6}};
        ArrayList<ArrayList<Integer>> adj1=new GraphRepresentation().graphAdjacencyListUndirected(7,7,arr1);
        System.out.println(new BipartiteGraphColouringDFS().colouringPossible(8,adj));
        System.out.println(new BipartiteGraphColouringDFS().colouringPossible(7,adj1));
    }
}
