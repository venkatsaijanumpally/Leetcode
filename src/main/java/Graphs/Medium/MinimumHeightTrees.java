package Graphs.Medium;

import java.util.*;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n==1){
            List<Integer> result=new ArrayList<>();
            result.add(0);
            return result;
        }
        ArrayList<HashSet<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++)
            adj.add(new HashSet<>());

        int[] indegree=new int[n];
        int[] outdegree=new int[n];
        for(int[] edge:edges){
            indegree[edge[0]]++;
            indegree[edge[1]]++;
            outdegree[edge[0]]++;
            outdegree[edge[1]]++;
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(indegree[i]==1 && outdegree[i]==1)
                queue.offer(i);
        }

        int remainingNodes=n;
        /*while (remainingNodes>2){
            int node= queue.poll();
            indegree[node]=0;
            outdegree[node]=0;
            int parent=adj.get(node).iterator().next();
            indegree[parent]--;
            outdegree[parent]--;
            adj.get(parent).remove(node);
            remainingNodes--;
            if(indegree[parent]==1 && outdegree[parent]==1)
                queue.add(parent);
        }*/
        while (remainingNodes>2){
            int size=queue.size();
            for(int i=0;i<size;i++){
                int node= queue.poll();
                indegree[node]=0;
                outdegree[node]=0;
                int parent=adj.get(node).iterator().next();
                indegree[parent]--;
                outdegree[parent]--;
                adj.get(parent).remove(node);
                remainingNodes--;
                if(indegree[parent]==1 && outdegree[parent]==1)
                    queue.add(parent);
            }
        }
        return new ArrayList<>(queue);
    }

    public static void main(String[] args) {
        int[][] arr={{3,0},{3,1},{3,2},{3,4},{5,4}};
        int[][] arr2={{1,0},{1,2},{1,3}};
        int[][] arr3={};
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(6,arr));
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(4,arr2));
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(1,arr3));
    }
}
