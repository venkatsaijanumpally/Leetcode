package Graphs.Medium;

import java.util.ArrayList;

public class PossibleBipartition {
    int[] colour;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=n;i++)
            adj.add(new ArrayList<>());
        for(int[] edge: dislikes){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        colour=new int[n+1];
        for(int i=0;i<=n;i++){
            if(colour[i]==0){
                colour[i]=1;
                if(dfsCycle(adj,i))
                    return false;
            }
        }
        return true;
    }

    private boolean dfsCycle(ArrayList<ArrayList<Integer>> adj, int node) {
        for(int neighbour: adj.get(node)){
            if(colour[neighbour]==0){
                colour[neighbour]=-colour[node];
                if(dfsCycle(adj,neighbour))
                    return true;
            }
            else if(colour[neighbour]==colour[node])
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{1,3},{2,4}};
        int[][] arr2={{1,2},{1,3},{2,3}};
        int[][] arr3={{1,2},{2,3},{3,4},{4,5},{1,5}};
        System.out.println(new PossibleBipartition().possibleBipartition(4,arr));
        System.out.println(new PossibleBipartition().possibleBipartition(3,arr2));
        System.out.println(new PossibleBipartition().possibleBipartition(5,arr3));
    }
}
