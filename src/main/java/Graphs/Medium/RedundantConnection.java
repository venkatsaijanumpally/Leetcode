package Graphs.Medium;

import Graphs.Basics.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RedundantConnection {
    /*
     * https://www.youtube.com/watch?v=wU6udHRIkcc
     * Approach: Union Find
     *
     *
     * Note: we cannot use DFS or BFS in this problem as the problem states "return the answer that occurs last in the
     * input" which means the last input edge that forms the cycle should be returned.
     *
     */
    public int[] findRedundantConnection(int[][] edges){
        parent=new int[edges.length+1];
        rank=new int[edges.length+1];

        for(int i=1;i<= edges.length;i++)
            parent[i]=i;

        for(int[] edge:edges){
            if(!union(edge[0],edge[1]))
                return edge;
        }
        return new int[]{1,1};
    }

    int[] parent;
    int[] rank;
    public boolean union(int a, int b){
        int parentA=getParent(a);
        int parentB=getParent(b);
        //This condition lets us know is there is a cycle or not
        // If 2 elements belonging to same component are connected then it forms a cycle
        if(parentA==parentB)
            return false;

        if(rank[parentA]>rank[parentB]){
            parent[parentB]=parentA;
        }
        else if(rank[parentA]<rank[parentB]){
            parent[parentA]=parentB;
        }
        else {
            parent[parentB]=parentA;
            rank[parentA]++;
        }
        return true;
    }

    public int getParent(int node){
        if(node==parent[node])
            return node;
        return parent[node]=getParent(parent[node]);
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{2,3},{3,4},{1,4},{1,5}};
        int[][] arr2={{1,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(arr2)));
        System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(arr)));
    }
}
