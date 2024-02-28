package Graphs.Medium;

import java.util.*;

public class CountUnreachablePairsofNodesinanUndirectedGraph {
    int[] parent;
    int[] rank;
    HashMap<Integer,Integer> groupCount;

    public long countPairs(int n, int[][] edges) {
        if(edges.length==0)
            return (long) n*(n-1)/2;
        long result = 0;
        parent = new int[n];
        rank = new int[n];
        groupCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            groupCount.put(i,1);
            parent[i]=i;
        }
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        int totalNode = n;
        /*while (groupCount.size()!=1){
            Iterator<Map.Entry<Integer, Integer>> iterator = groupCount.entrySet().iterator();
            Map.Entry<Integer, Integer> entry1 = iterator.next();
            int value1 = entry1.getValue();
            result += (long) value1 * (totalNode - value1);
            totalNode -= value1;
            groupCount.remove(entry1.getKey());
        }*/
        Iterator<Map.Entry<Integer, Integer>> iterator = groupCount.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> entry1 = iterator.next();
            int value1 = entry1.getValue();
            result += (long) value1 * (totalNode - value1);
            totalNode -= value1;
        }


        return result;
    }

    private void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if (parentA == parentB)
            return;
        if (rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
            groupCount.put(parentB, groupCount.get(parentB) + groupCount.get(parentA));
            groupCount.remove(parentA);
        }
        else if (rank[parentA] > rank[parentB]) {
            parent[parentB] = parentA;
            groupCount.put(parentA, groupCount.get(parentB) + groupCount.get(parentA));
            groupCount.remove(parentB);
        }
        else {
            rank[parentB]++;
            parent[parentA] = parentB;
            groupCount.put(parentB, groupCount.get(parentB) + groupCount.get(parentA));
            groupCount.remove(parentA);
        }
    }

    private int getParent(int node) {
        int parentNode = parent[node];
        if ( parentNode == node) {
            return node;
        }
        return parent[node] = getParent(parentNode);
    }

    public static void main(String[] args) {
        int[][] edges1 = {{0,1},{0,2},{1,2}};
        int[][] edges2 = {{0,2},{0,5},{2,4},{1,6},{5,4}};
        System.out.println(new CountUnreachablePairsofNodesinanUndirectedGraph().countPairs(3, edges1));
        System.out.println(new CountUnreachablePairsofNodesinanUndirectedGraph().countPairs(7, edges2));
    }
}
