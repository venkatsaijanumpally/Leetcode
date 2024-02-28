package Graphs.Medium;

import Graphs.Utils.WeightedTwoPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class NumberofRestrictedPathsFromFirsttoLastNode {

    ArrayList<ArrayList<WeightedTwoPair>> adj;
    public int countRestrictedPaths(int n, int[][] edges) {
        adj=new ArrayList<>();
        for (int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }

        for (int[] edge: edges){
            adj.get(edge[0]).add(new WeightedTwoPair(edge[0],edge[1],edge[2]));
            adj.get(edge[1]).add(new WeightedTwoPair(edge[1],edge[0],edge[2]));
        }

        int[] shortestDistance = new int[n+1];
        shortestDistance[n]=-1;
        dijstra(shortestDistance, n);

        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return shortestDistance[o1]-shortestDistance[o2];
            }
        });

        queue.add(n);
        long[] count=new long[n+1];
        count[n]=1;
        boolean[]  visited=new boolean[n+1];
        while (!queue.isEmpty()){
            int node= queue.poll();
            if(visited[node]) continue;
            visited[node]=true;
            for (WeightedTwoPair neighbour: adj.get(node)){
                if(!visited[neighbour.neighbour] && (shortestDistance[neighbour.neighbour] > shortestDistance[node])){
                    queue.offer(neighbour.neighbour);
                    count[neighbour.neighbour] = (count[neighbour.neighbour]+count[node])%1000000007;
                }
                else if(visited[neighbour.neighbour] && (shortestDistance[neighbour.neighbour] > shortestDistance[node]))
                    count[neighbour.neighbour] = (count[neighbour.neighbour]+count[node])%1000000007;
            }
        }

        System.out.println(Arrays.toString(shortestDistance));
        return (int) count[1];
    }

    private void dijstra(int[] shortestDistance, int n) {
        PriorityQueue<WeightedTwoPair> queue = new PriorityQueue<>(new Comparator<WeightedTwoPair>() {
            @Override
            public int compare(WeightedTwoPair o1, WeightedTwoPair o2) {
                return o1.dist-o2.dist;
            }
        }) ;
        boolean[] visited = new boolean[n+1];
        queue.offer(new WeightedTwoPair(n,n,0));
        while (!queue.isEmpty()){
            WeightedTwoPair pair=queue.poll();
            if(visited[pair.neighbour]) continue;
            visited[pair.neighbour]=true;
            shortestDistance[pair.neighbour]=pair.dist;
            for (WeightedTwoPair neighbourOfNeighbour: adj.get(pair.neighbour)){
                if(visited[neighbourOfNeighbour.neighbour]) continue;
                queue.add(new WeightedTwoPair(neighbourOfNeighbour.node, neighbourOfNeighbour.neighbour, pair.dist + neighbourOfNeighbour.dist));
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges={{1,2,3},{1,3,3},{2,3,1},{1,4,2},{5,2,2},{3,5,1},{5,4,10}};
        int[][] edges2={{1,3,1},{4,1,2},{7,3,4},{2,5,3},{5,6,1},{6,7,2},{7,5,3},{2,6,4}};
        System.out.println(new NumberofRestrictedPathsFromFirsttoLastNode().countRestrictedPaths(5, edges));
        System.out.println(new NumberofRestrictedPathsFromFirsttoLastNode().countRestrictedPaths(7, edges2));
    }
}
