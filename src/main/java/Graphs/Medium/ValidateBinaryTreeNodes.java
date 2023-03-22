package Graphs.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class ValidateBinaryTreeNodes {
    /*
     * Note: Root need not be node '0', It can be any node
     *
     * Rules:
     * 1. If a node is re visited then return false since 2 nodes are connecting to same child.
     * 2. When making union of 2 nodes if both nodes belong to same parent then return false
     * 3. Before performing the union we make visited[child]=true but not the parent and so the root remains unvisited
     *    till the end until another node takes the root as it child.
     *    e.g int[] left3={3,-1,1,-1} right3={-1,-1,0,-1};
     */
    int[] parent;
    int components;
    //#WORKING
    public boolean validateBinaryTreeNodesUnion(int n, int[] leftChild, int[] rightChild) {
        parent=new int[n];
        components=n;
        boolean[] visited=new boolean[n];

        for(int i=1;i<n;i++)
            parent[i]=i;

        for(int i=0;i<n;i++){
            if(leftChild[i]!=-1){
                if(visited[leftChild[i]] || getParent(i)==getParent(leftChild[i]))
                    return false;
                visited[leftChild[i]]=true;
                union(i,leftChild[i]);
            }
            if(rightChild[i]!=-1){
                if(visited[rightChild[i]] || getParent(i)==getParent(rightChild[i]))
                    return false;
                visited[rightChild[i]]=true;
                union(i,rightChild[i]);
            }
        }
        if(components>1) return false;
        return true;
    }

    private void union(int Parent, int child) {
        int parentofParent=getParent(Parent);
        int childParent=getParent(child);

        parent[childParent]=parentofParent;
        components--;
    }

    private int getParent(int x) {
        if(x==parent[x])
            return x;
        return parent[x]=getParent(parent[x]);
    }

    public static void main(String[] args) {
        int[] left={1,-1,3,-1};
        int[] right={2,-1,-1,-1};
        int[] left2={1,-1,3,-1};
        int[] right2={2,3,-1,-1};
        int[] left3={3,-1,1,-1};
        int[] right3={-1,-1,0,-1};
        System.out.println(new ValidateBinaryTreeNodes().validateBinaryTreeNodesUnion(4,left,right));
        System.out.println(new ValidateBinaryTreeNodes().validateBinaryTreeNodesUnion(4,left2,right2));
        System.out.println(new ValidateBinaryTreeNodes().validateBinaryTreeNodesUnion(4,left3,right3));
    }
}
