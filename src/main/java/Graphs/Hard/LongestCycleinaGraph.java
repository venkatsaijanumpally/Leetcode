package Graphs.Hard;

import java.util.Arrays;
import java.util.HashMap;

public class LongestCycleinaGraph {
    int max=-1;
    boolean[] visited;
    HashMap<Integer,Integer> maxCycleAtANode;
    public int longestCycle(int[] edges) {
        maxCycleAtANode=new HashMap<>();
        int[][] dfsVisited=new int[edges.length][2];
        visited=new boolean[edges.length];
        for(int i=0;i< edges.length;i++){
            if(!visited[i] && edges[i]!=-1){
                dfsFindMaxCycle(i,edges,dfsVisited,0);
            }
        }
        return max;
    }

    private int dfsFindMaxCycle(int node, int[] edges, int[][] dfsVisited, int nodesVisited) {
        if(node==-1)
            return -1;
        if(maxCycleAtANode.containsKey(node))
            return maxCycleAtANode.get(node);
        nodesVisited++;
        if(dfsVisited[node][0]==1){
            max=Math.max(max,nodesVisited-dfsVisited[node][1]);
            return nodesVisited-dfsVisited[node][1];
        }
        visited[node]=true;
        dfsVisited[node][0]=1;
        dfsVisited[node][1]=nodesVisited;
        int res=dfsFindMaxCycle(edges[node],edges,dfsVisited,nodesVisited);
        dfsVisited[node][0]=0;
        maxCycleAtANode.put(node,res);
        return res;
    }

    public static void main(String[] args) {
        int[] edges={3,3,4,2,3};
        int[] edges4={3,3,4,2,3,0};
        int[] edges2={2,-1,3,1};
        int[] edges3={1,2,0,4,5,6,3,8,9,7};
        System.out.println(new LongestCycleinaGraph().longestCycle(edges));
        System.out.println(new LongestCycleinaGraph().longestCycle(edges2));
        System.out.println(new LongestCycleinaGraph().longestCycle(edges3));
        System.out.println(new LongestCycleinaGraph().longestCycle(edges4));
    }
}
