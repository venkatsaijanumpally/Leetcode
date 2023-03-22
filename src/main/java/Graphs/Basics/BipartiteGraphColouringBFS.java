package Graphs.Basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraphColouringBFS {
    /*
     * https://www.youtube.com/watch?v=nbgaEu-pvkU&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=10
     */
    public boolean colouringPossible(int n, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited=new boolean[n+1];
        int[] colour= new int[n+1];

        for(int i=1;i<=n;i++){
            if(!visited[i]){
                visited[i]=true;
                colour[i]=1;
                Queue<Integer> queue=new LinkedList<>();
                queue.offer(i);

                while (!queue.isEmpty()){
                    int node= queue.poll();

                    for(int child: adj.get(node)){
                        if(colour[child]==0) {
                            if(colour[node]==1) colour[child]=2;
                            else colour[child]=1;
                            queue.offer(child);
                            visited[child]=true;
                        }
                        else if(colour[child]==colour[node])
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{2,3},{3,4},{4,5},{2,7},{7,6},{6,5},{5,8}};
        ArrayList<ArrayList<Integer>> adj=new GraphRepresentation().graphAdjacencyListUndirected(8,8,arr);
        int[][] arr1={{1,2},{2,3},{3,4},{4,5},{2,7},{7,5},{5,6}};
        ArrayList<ArrayList<Integer>> adj1=new GraphRepresentation().graphAdjacencyListUndirected(7,7,arr1);
        System.out.println(new BipartiteGraphColouringBFS().colouringPossible(8,adj));
        System.out.println(new BipartiteGraphColouringBFS().colouringPossible(7,adj1));
    }
}
