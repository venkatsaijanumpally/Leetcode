package Graphs.Medium;

import java.util.*;

public class LoudandRich {
    ArrayList<ArrayList<Integer>> adj;
    int[] topoSorted;
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        adj=new ArrayList<>();
        int N=quiet.length;
        topoSorted=new int[N];
        int k=N-1;
        for(int i=0;i<N;i++)
            adj.add(new ArrayList<>());
        //The adjacency list instead of representing neighbours of a node represents parents of a node
        for(int[] edge: richer){
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited=new boolean[N];
        for(int i=0;i<N;i++){
            if(!visited[i]){
                //Result of current topological sort
                Stack<Integer> result=new Stack<>();
                //DFS Stack
                Stack<Integer> stack=new Stack<>();
                stack.push(i);
                while (!stack.isEmpty()){
                    int node=stack.pop();
                    visited[node]=true;
                    for(int neighbour: adj.get(node)){
                        if(!visited[neighbour])
                            stack.push(neighbour);
                    }
                    result.push(node);
                }
                //topoSorted.addFirst(result);

                while (!result.isEmpty()){
                    topoSorted[k--]=result.pop();
                }
            }
        }
        //System.out.println(Arrays.toString(topoSorted));

        //Traverse through the topological order and take the node with min quietness from all its parent hierarchy.
        int[] result=new int[N];
        for(int i=N-1;i>=0;i--){
            int leastVal=quiet[topoSorted[i]];
            int leastNode=topoSorted[i];
            for(int parent: adj.get(topoSorted[i])){
                if(quiet[result[parent]]<leastVal){
                    leastVal=quiet[result[parent]];
                    leastNode=result[parent];
                }
            }
            result[topoSorted[i]]=leastNode;
        }
        //System.out.println(Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        int[][] richer={{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet={3,2,5,4,6,1,7,0};
        new LoudandRich().loudAndRich(richer,quiet);
    }
}
