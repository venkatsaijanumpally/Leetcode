package Trees.Hard;

import Trees.Utils.FrogJumpNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FrogPositionAfterTSeconds {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if(n==1 && target==1) return 1;
        Queue<FrogJumpNode> queue=new LinkedList<>();
        queue.add(new FrogJumpNode(0,1,1d));
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<=n;i++)
            adj.add(new ArrayList<>());
        for(int[] edge: edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited=new boolean[n+1];
        visited[0]=true;
        adj.get(1).add(0);
        visited[1]=true;
        while (!visited[target] && t>0){
            int size=queue.size();
            for(int i=0;i<size;i++){
                FrogJumpNode node= queue.poll();
                int neighbourSize=adj.get(node.node).size()-1;
                for(int neighbour: adj.get(node.node)){
                    if(neighbour==target){
                        if(t-1!=0 && adj.get(neighbour).size()>1) return 0d;
                        return node.probability/neighbourSize;
                    }
                    else if(!visited[neighbour]){
                        visited[neighbour]=true;
                        queue.add(new FrogJumpNode(node.node, neighbour,node.probability/neighbourSize));
                    }
                }
            }
            t--;
        }
        return 0d;
    }

    public static void main(String[] args) {
        int[][] edges={{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};
        System.out.println(new FrogPositionAfterTSeconds().frogPosition(7,edges,2,4));
        System.out.println(new FrogPositionAfterTSeconds().frogPosition(7,edges,1,7));
        System.out.println(new FrogPositionAfterTSeconds().frogPosition(7,edges,2,5));
    }
}
