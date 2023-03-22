package Graphs.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class RedundantConnectionII {

    /*
     * https://leetcode.com/problems/redundant-connection-ii/solutions/108045/c-java-union-find-with-explanation-o-n/?orderBy=most_relevant
     * There are two cases for the tree structure to be invalid.
     * 1) A node having two parents;
     * including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
     * 2) A circle exists
     *
     * For all parents of node with multiple parents run union find ignoring the parent edge and check if it is succesful
     * if successful return edge else run same for another parent edge.
     */

    int[] parentOfSet;
    ArrayList<ArrayList<Integer>> parentOfNode;

    private boolean union(int node, int child) {
        int parentofnode=getParent(node);
        int parentofChild=getParent(child);

        if(parentofnode == parentofChild)
            return false;

        parentOfSet[parentofChild]=parentofnode;
        return true;
    }

    private int getParent(int node) {
        if(parentOfSet[node]==node)
            return node;
        return parentOfSet[node]=getParent(parentOfSet[node]);
    }



    public int[] findRedundantDirectedConnection(int[][] edges) {
        parentOfNode=new ArrayList<>();
        parentOfSet =new int[edges.length+1];

        for(int i=0;i< edges.length+1;i++){
            parentOfNode.add(new ArrayList<>());
            parentOfSet[i]=i;
        }

        int multipleParentNode=-1;
        for(int[] edge: edges){
            parentOfNode.get(edge[1]).add(edge[0]);
            if(parentOfNode.get(edge[1]).size()>1)
                multipleParentNode=edge[1];
        }

        if(multipleParentNode!=-1){
            for(int j=parentOfNode.get(multipleParentNode).size()-1;j>=0;j--){
                int[] edge=new int[]{parentOfNode.get(multipleParentNode).get(j),multipleParentNode};
                if(performUnion(edges,edge))
                    return edge;
            }
        }

        for(int[] edge: edges){
            if(!union(edge[0],edge[1])){
                return edge;
            }
        }

        return new int[]{1,1};
    }

    public boolean performUnion(int[][] edges, int[] ignoreedge){
        parentOfSet =new int[edges.length+1];
        for(int i=1;i< edges.length+1;i++){
            parentOfSet[i]=i;
        }
        for(int[] edge: edges){
            if(edge[0]==ignoreedge[0] && edge[1]==ignoreedge[1]) continue;
            if(!union(edge[0],edge[1]))
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int[][] arr={{1,2},{1,3},{2,3}};
        int[][] arr2={{1,2},{2,3},{3,4},{4,1},{1,5}};
        int[][] arr3={{2,1},{3,1},{4,2},{1,4}};
        int[][] arr4={{4,2},{1,5},{5,2},{5,3},{2,4}};
        int[][] arr5={{5,2},{5,1},{3,1},{3,4},{3,5}};
        int[][] arr6={{2,1},{2,3},{2,4},{3,4}};


        System.out.println(Arrays.toString(new RedundantConnectionII().findRedundantDirectedConnection(arr5)));
        System.out.println(Arrays.toString(new RedundantConnectionII().findRedundantDirectedConnection(arr6)));
        System.out.println(Arrays.toString(new RedundantConnectionII().findRedundantDirectedConnection(arr)));
        System.out.println(Arrays.toString(new RedundantConnectionII().findRedundantDirectedConnection(arr2)));
        System.out.println(Arrays.toString(new RedundantConnectionII().findRedundantDirectedConnection(arr3)));
        System.out.println(Arrays.toString(new RedundantConnectionII().findRedundantDirectedConnection(arr4)));
    }
}
