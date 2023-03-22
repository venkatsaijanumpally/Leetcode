package Graphs.Basics;

import java.util.ArrayList;

public class GraphRepresentation {

    public ArrayList<ArrayList<Integer>> graphAdjacencyListUndirected(int nodes, int edges, int[][] arr){
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=nodes;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<edges;i++){
            adj.get(arr[i][0]).add(arr[i][1]);
            adj.get(arr[i][1]).add(arr[i][0]);
        }
        return adj;
    }

    public ArrayList<ArrayList<Integer>> graphAdjacencyListDirected(int nodes, int edges, int[][] arr){
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=nodes;i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0;i<edges;i++){
            adj.get(arr[i][0]).add(arr[i][1]);
        }
        return adj;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{1,3},{1,5},{2,3},{3,5},{2,4},{3,4}};
        int[][] arr1={{1,2},{1,3},{1,5},{2,3},{3,5},{2,4},{3,4},{6,7}};
        System.out.println(new GraphRepresentation().graphAdjacencyListUndirected(5,7,arr));
        System.out.println(new GraphRepresentation().graphAdjacencyListUndirected(7,8,arr1));
    }
}
