package Graphs.Basics;

import java.util.ArrayList;

public class GraphDFS {

    public String dfs(int nodes, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[nodes + 1];
        StringBuilder sb = new StringBuilder();

        //This loop runs for each component of a graph
        for (int i = 1; i <= nodes; i++) {
            if (!visited[i]) {
                recursive(visited, sb, adj, i);
            }
        }
        return sb.toString();
    }

    private void recursive(boolean[] visited, StringBuilder sb, ArrayList<ArrayList<Integer>> adj, int node) {
        sb.append(node).append(" ");
        visited[node] = true;
        for (int child : adj.get(node)) {
            if(!visited[child])
                recursive(visited, sb, adj, child);
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {1, 3}, {1, 5}, {2, 3}, {3, 5}, {2, 4}, {3, 4}};
        ArrayList<ArrayList<Integer>> adj = new GraphRepresentation().graphAdjacencyListUndirected(5, 7, arr);
        int[][] arr1 = {{1, 2}, {1, 3}, {1, 5}, {2, 3}, {3, 5}, {2, 4}, {3, 4}, {6, 7}};
        ArrayList<ArrayList<Integer>> adj1 = new GraphRepresentation().graphAdjacencyListUndirected(7, 8, arr1);
        System.out.println(new GraphDFS().dfs(5, adj));
        System.out.println(new GraphDFS().dfs(7, adj1));

        int[][] arr2 = {{1, 2}, {2, 7}, {2, 4}, {7, 6}, {4, 6}, {3, 5}};
        ArrayList<ArrayList<Integer>> adj2 = new GraphRepresentation().graphAdjacencyListUndirected(7, 6, arr2);
        System.out.println(new GraphDFS().dfs(7, adj2));
        System.out.println(new GraphBFS().bfs(7, adj2));
    }
}
