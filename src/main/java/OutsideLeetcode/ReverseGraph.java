package OutsideLeetcode;

import OutsideLeetcode.Utils.Node;

import java.util.ArrayList;

public class ReverseGraph {

    ArrayList<ArrayList<Integer>> adj;
    ArrayList<ArrayList<Integer>> rev;
    public void reverse(int n, int[][] edges){
        adj=new ArrayList<>();
        rev=new ArrayList<>();
        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }
        for(int[] edge:edges)
            adj.get(edge[0]).add(edge[1]);
        for(int i=1;i<=n;i++){
            for(int j:adj.get(i)){
                rev.get(j).add(i);
            }
        }

        //Print
        for(int i=1;i<=n;i++){
            for(int j:adj.get(i)){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        System.out.println("\nReverse");
        for(int i=1;i<=n;i++){
            for(int j:rev.get(i)){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }

    public void sortAdjacencyList(ArrayList<ArrayList<Integer>> arr, int n){
        ArrayList<ArrayList<Integer>> sortedAdj=new ArrayList<>();
        for(int i=0;i<=n;i++)
            sortedAdj.add(new ArrayList<>());
        for(int i=1;i<arr.size();i++){
            ArrayList list=arr.get(i);
            for(int j=0;j<list.size();j++){
                sortedAdj.get((Integer) list.get(j)).add(i);
            }
        }

        for(ArrayList list:arr){
            for(int i=0;i<list.size();i++)
                System.out.print(list.get(i)+" ");
            System.out.println();
        }
        System.out.println("\nSORTED");
        for(ArrayList list:sortedAdj){
            for(int i=0;i<list.size();i++)
                System.out.print(list.get(i)+" ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] arr1={{1,2},{2,3},{3,4},{1,5},{5,3},{5,4}};
        new ReverseGraph().reverse(5,arr1);


        System.out.println("\nSort");
        int[][] arr2={{1,5},{2,3},{3,4},{1,2},{5,4},{5,3}};
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=5;i++)
            adj.add(new ArrayList<>());
        for(int[] edge:arr2){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        new ReverseGraph().sortAdjacencyList(adj,5);
    }
}
