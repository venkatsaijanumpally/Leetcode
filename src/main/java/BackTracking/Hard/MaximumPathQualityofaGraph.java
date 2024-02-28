package BackTracking.Hard;

import BackTracking.Utils.WeightedPair;

import java.util.ArrayList;

public class MaximumPathQualityofaGraph {
    int[] values;
    ArrayList<ArrayList<WeightedPair>> adj=new ArrayList<>();
    int maxTime;
    int maxQuality=0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        this.values=values;
        this.maxTime=maxTime;
        for(int i=0;i<= values.length;i++)
            adj.add(new ArrayList<>());
        for (int[] ede: edges){
            adj.get(ede[0]).add(new WeightedPair(ede[1],ede[2]));
            adj.get(ede[1]).add(new WeightedPair(ede[0],ede[2]));
        }
        int[] visited=new int[values.length];
        recursive(0,visited,0,0);
        return maxQuality;
    }

    private void recursive(int node, int[] visited, int quality, int time) {
        if (time > maxTime) return;
        if (visited[node] == 0) {
            quality += values[node];
        }
        if (node == 0) {
            maxQuality = Math.max(maxQuality, quality);
        }
        visited[node]++;

        for (WeightedPair neighbour : adj.get(node)) {
            recursive(neighbour.node, visited, quality, time + neighbour.time);
        }
        if (visited[node] == 1)
            quality -= values[node];
        visited[node]--;
    }

    public static void main(String[] args) {
        int[] values1={0,32,10,43};
        int[] values2={5,10,15,20};
        int[][] edges1={{0,1,10},{1,2,15},{0,3,10}};
        int[][] edges2={{0,1,10},{1,2,10},{0,3,10}};
        System.out.println(new MaximumPathQualityofaGraph().maximalPathQuality(values1,edges1,49));
        System.out.println(new MaximumPathQualityofaGraph().maximalPathQuality(values2,edges2,30));
    }
}
