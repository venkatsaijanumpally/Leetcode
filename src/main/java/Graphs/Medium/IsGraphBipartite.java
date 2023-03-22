package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite {
    /*
     * https://www.youtube.com/watch?v=nbgaEu-pvkU&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=10
     * https://www.youtube.com/watch?v=uC884ske2uQ&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=11
     * Both solutions isBipartiteUsingBFS and isBipartiteUsingDFS are optimal
     */
    public boolean isBipartiteUsingBFS(int[][] graph) {
        ArrayList<int[]> adj = new ArrayList<>(Arrays.asList(graph));
        int[] colour=new int[graph.length];

        for(int i=0;i< graph.length;i++){
            if(colour[i]==0){
                Queue<Integer> queue=new LinkedList<>();
                queue.offer(i);
                colour[i]=1;
                while (!queue.isEmpty()){
                    int node= queue.poll();
                    for(int child: adj.get(node)){
                        if(colour[child]==0){
                            if(colour[node]==1) colour[child]=2;
                            else colour[child]=1;
                            queue.offer(child);
                        }
                        else if(colour[child]==colour[node])
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartiteUsingDFS(int[][] graph) {
        int[] colour=new int[graph.length];
        ArrayList<int[]> adj = new ArrayList<>(Arrays.asList(graph));

        for(int i=0;i< graph.length;i++){
            if(colour[i]==0){
                colour[i]=1;
                if(!recursiveColour(adj,colour,i))
                    return false;
            }
        }
        return true;
    }

    private boolean recursiveColour(ArrayList<int[]> adj, int[] colour, int node) {
        for(int child: adj.get(node)){
            if(colour[child]==0){
                colour[child]=-colour[node];
                if(!recursiveColour(adj,colour,child))
                    return false;
            }
            else if(colour[child]==colour[node])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2,3},{0,2},{0,1,3},{0,2}};
        int[][] arr1={{1,3},{0,2},{1,3},{0,2}};
        System.out.println(new IsGraphBipartite().isBipartiteUsingBFS(arr));
        System.out.println(new IsGraphBipartite().isBipartiteUsingBFS(arr1));
        System.out.println(new IsGraphBipartite().isBipartiteUsingDFS(arr));
        System.out.println(new IsGraphBipartite().isBipartiteUsingDFS(arr1));
    }
}
