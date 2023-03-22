package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPathsFromSourcetoTarget {
    List<List<Integer>> result;
    int numNodes;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        result=new ArrayList<>();
        numNodes=graph.length;
        recursive(0,graph,new ArrayList<>());
        return result;
    }

    //DFS approach
    private void recursive(int curr, int[][] adj, ArrayList<Integer> path) {
        path.add(curr);
        if(curr==numNodes-1){
            result.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return;
        }
        for(int child: adj[curr]){
            recursive(child,adj,path);
        }
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{3},{3},{}};
        System.out.println(new AllPathsFromSourcetoTarget().allPathsSourceTarget(arr));
    }
}
