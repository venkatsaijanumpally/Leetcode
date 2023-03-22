package Graphs.Basics;

import Graphs.Utils.Node;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimsMST {

    ArrayList<ArrayList<WeightedPair>> adjList=new ArrayList<>();
    public void minimumSpanningTree(int[][] edges, int n){
        boolean[] visited=new boolean[n];
        for(int i=0;i< n;i++)
            adjList.add(new ArrayList<>());
        for(int[] edge: edges){
            adjList.get(edge[0]).add(new WeightedPair(edge[1],edge[2]));
            adjList.get(edge[1]).add(new WeightedPair(edge[0],edge[2]));
        }


        int sum=0;
        int[][] resultEdges=new int[n][3];
        int k=0;
        PriorityQueue<PrimsPair> queue=new PriorityQueue<>();
        queue.add(new PrimsPair(0,0,0));
        while (!queue.isEmpty()){
            PrimsPair pair=queue.poll();
            if(visited[pair.nodeB]) continue;

            sum+=pair.distance;
            visited[pair.nodeB]=true;
            resultEdges[k][0]= pair.nodeA;
            resultEdges[k][1]=pair.nodeB;
            resultEdges[k++][2]=pair.distance;
            for(WeightedPair neighbour: adjList.get(pair.nodeB)){
                if(!visited[neighbour.node])
                    queue.offer(new PrimsPair(pair.nodeB, neighbour.node, neighbour.dist));
            }
        }
        System.out.println(sum);
        for(int i=0;i<k;i++){
            System.out.println(resultEdges[i][0]+ " "+resultEdges[i][1]+" "+resultEdges[i][2]);
        }
    }

    private int findMinimumNeighbouringIndex(int node, boolean[] visited) {
        int minIndex=-1;
        int min= Integer.MAX_VALUE;
        for(WeightedPair neighbour: adjList.get(node)){
            if(!visited[neighbour.node] && min>neighbour.dist){
                min=neighbour.dist;
                minIndex=neighbour.node;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        new PrimsMST().minimumSpanningTree(new int[][]{{0,1,3},{0,2,2},{2,1,1},{1,3,4},{2,3,3}},4);
        new PrimsMST().minimumSpanningTree(new int[][]{{0,1,4},{0,7,8},{1,7,11},{7,8,7},{1,2,8},{7,6,1},{6,8,6},{8,2,2},{6,5,2},{2,5,4},{2,3,7},{3,5,14},{5,4,10},{3,4,9}},9);
    }
}
