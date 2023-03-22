package Graphs.Easy;

import java.util.ArrayList;

public class FindCenterofStarGraph {
    public int findCenter(int[][] edges) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=edges.length+1;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i< edges.length;i++){
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
            if(adj.get(edges[i][0]).size()>1) return edges[i][0];
            if(adj.get(edges[i][1]).size()>1) return edges[i][1];
        }
        return 1;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{2,3},{4,2}};
        int[][] arr1={{1,2},{5,1},{1,3},{1,4}};
        System.out.println(new FindCenterofStarGraph().findCenter(arr));
        System.out.println(new FindCenterofStarGraph().findCenter(arr1));
    }
}
