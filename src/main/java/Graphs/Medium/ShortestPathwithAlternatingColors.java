package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathwithAlternatingColors {
    ArrayList<ArrayList<Integer>> adjRed;
    ArrayList<ArrayList<Integer>> adjBlue;
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        //RUN variation of BFS to find the shortest path
        adjRed=new ArrayList<>();
        adjBlue=new ArrayList<>();
        for(int i=0;i<n;i++){
            adjRed.add(new ArrayList<>());
            adjBlue.add(new ArrayList<>());
        }

        for(int[] edge:redEdges){
            adjRed.get(edge[0]).add(edge[1]);
        }
        for(int[] edge:blueEdges){
            adjBlue.get(edge[0]).add(edge[1]);
        }


        /*
         * We track visited of each edge but not the nodes
         * visited[i][1] represents that node 'i' is visited from (1)Blue edge
         * visited[i][0] represents that node 'i' is visited from (0)Red edge
         * dist[i][1] represents shortest distance to node 'i' with last edge being a (1)Blue edge
         * We add two possibilities to the queue reaching node '0' from a blue edge and node '0' from Red edge.
         */
        boolean[][] visitedEdges=new boolean[n][2];
        visitedEdges[0][0]=true;
        visitedEdges[0][1]=true;
        Queue<Integer> queue=new LinkedList<>();
        Queue<Integer> queueNextColour=new LinkedList<>();
        queue.add(0);
        queue.add(0);
        queueNextColour.add(0);//0->consider Red edge to be first edge
        queueNextColour.add(1);
        int[][] dist=new int[n][2];
        for(int i=0;i<n;i++)
            Arrays.fill(dist[i],-1);
        dist[0][0]=0;
        dist[0][1]=0;
        //Run BFS which gives shortest paths as it is unweighted
        while (!queue.isEmpty()){
            int node= queue.poll();
            int edgeColour= queueNextColour.poll();
            ArrayList<ArrayList<Integer>> adj = edgeColour==0?adjRed:adjBlue;
            for(int neighbour: adj.get(node)){
                int nextColour=(edgeColour+1)%2;
                if(!visitedEdges[neighbour][edgeColour]){
                    dist[neighbour][edgeColour]=dist[node][(edgeColour+1)%2]+1; //Since the parent node is reached by opposite edge
                    visitedEdges[neighbour][edgeColour]=true;
                    queue.add(neighbour);
                    queueNextColour.add(nextColour);
                }
            }
        }


        for(int i=0;i<n;i++){
            for(int j=0;j<2;j++){
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }

        int[] minDist=new int[n];
        for(int i=0;i<n;i++){
            if(dist[i][0]!=-1 && dist[i][1]!=-1)
                minDist[i]=Math.min(dist[i][0],dist[i][1]);
            else minDist[i]=Math.max(dist[i][0],dist[i][1]);
        }
        return minDist;
    }

    public static void main(String[] args) {
        int[][] redEdges={{0,1},{1,2}};
        int[][] blueEdges={};
        int[][] redEdges2={{0,1}};
        int[][] blueEdges2={{2,1}};
        System.out.println(new ShortestPathwithAlternatingColors().shortestAlternatingPaths(3,redEdges,blueEdges));
        System.out.println(new ShortestPathwithAlternatingColors().shortestAlternatingPaths(3,redEdges2,blueEdges2));
    }
}
