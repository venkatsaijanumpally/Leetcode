package Graphs.Medium;

import java.util.HashMap;
import java.util.Map;

public class CounttheNumberofCompleteComponents {
    int[] parent;
    int[] rank;
    HashMap<Integer, Integer> groupCount;
    HashMap<Integer, Integer> edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];
        groupCount = new HashMap<>();
        edgeCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            groupCount.put(i, 1);
            edgeCount.put(i, 0);
            parent[i] = i;
        }
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        int result = groupCount.size();
        for (Map.Entry<Integer, Integer> component: groupCount.entrySet()) {
            int componentEdges = edgeCount.get(component.getKey());
            int nodes = component.getValue();
            if(nodes*(nodes-1)/2 != componentEdges)
                result--;
        }
        return result;
    }

    private void union(int a, int b) {
        int parentA = getParent(a);
        int parentB = getParent(b);

        if (parentA == parentB) {
            edgeCount.put(parentA, edgeCount.get(parentA) + 1);
        }
        else if (rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
            groupCount.put(parentB, groupCount.get(parentB) + groupCount.get(parentA));
            edgeCount.put(parentB, edgeCount.get(parentA) + edgeCount.get(parentB) + 1);
            groupCount.remove(parentA);
        }
        else if (rank[parentA] > rank[parentB]) {
            parent[parentB] = parentA;
            groupCount.put(parentA, groupCount.get(parentB) + groupCount.get(parentA));
            edgeCount.put(parentA, edgeCount.get(parentA) + edgeCount.get(parentB) + 1);
            groupCount.remove(parentB);
        }
        else {
            rank[parentB]++;
            parent[parentA] = parentB;
            groupCount.put(parentB, groupCount.get(parentB) + groupCount.get(parentA));
            edgeCount.put(parentB, edgeCount.get(parentA) + edgeCount.get(parentB) + 1);
            groupCount.remove(parentA);
        }
    }

    private int getParent(int node) {
        int parentNode = parent[node];
        if (parentNode == node) {
            return node;
        }
        return parent[node] = getParent(parentNode);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {3, 4}};
        int[][] edges2 = {{0,1},{0,2},{1,2},{3,4},{3,5}};
        System.out.println(new CounttheNumberofCompleteComponents().countCompleteComponents(6, edges));
        System.out.println(new CounttheNumberofCompleteComponents().countCompleteComponents(6, edges2));
    }
}
