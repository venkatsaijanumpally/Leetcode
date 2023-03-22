package Graphs.Medium;

import java.util.*;

public class MinimumNumberofVerticestoReachAllNodes {
    int[] parent;
    int components;
    ArrayList<ArrayList<Integer>> adj;
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        parent=new int[n];
        adj=new ArrayList<>();

        for(int i=0;i<n;i++)
            parent[i]=i;
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        for(List<Integer> edge: edges){
            adj.get(edge.get(0)).add(edge.get(1));
        }

        components=n;
        boolean[] visited=new boolean[n];
        for(int i=0;i<n;i++){
            visited[i]=true;
            Queue<Integer> queue=new LinkedList<>();
            queue.addAll(adj.get(i));
            while (!queue.isEmpty()){
                int node= queue.poll();
                if(!visited[node]){
                    visited[node]=true;
                    union(i,node);
                    queue.addAll(adj.get(node));
                }
                else if(getParent(node)==node){
                    union(i,node);
                }
            }
        }

        HashSet<Integer> hs=new HashSet<>();
        List<Integer> result=new ArrayList<>();
        for(int i:parent){
            int parent=getParent(i);
            if(!hs.contains(parent)){
                hs.add(parent);
                result.add(parent);
            }
        }
        return result;
    }

    private int getParent(int node) {
        if(parent[node]==node)
            return node;
        return parent[node]=getParent(parent[node]);
    }

    private void union(int componentParent, int node) {
        parent[node]=componentParent;
        components--;
    }

    /*
     * Method 2: Finding all nodes with ZERO incoming edges
     * If there is a incoming edge which means that node can be reached from its parent.
     * If there is no incoming edge then we need to start a component from this node.
     */
    //!Optimal
    public List<Integer> findSmallestSetOfVertices2(int n, List<List<Integer>> edges) {
        boolean[] incoming=new boolean[n];
        for(List<Integer> edge: edges){
            incoming[edge.get(1)]=true;
        }

        List<Integer> result=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(!incoming[i])
                result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> arr=new ArrayList<>();
        for(int i=0;i<5;i++)
            arr.add(new ArrayList<>());
        arr.get(0).add(0);
        arr.get(0).add(1);
        arr.get(1).add(0);
        arr.get(1).add(2);
        arr.get(2).add(2);
        arr.get(2).add(5);
        arr.get(3).add(3);
        arr.get(3).add(4);
        arr.get(4).add(4);
        arr.get(4).add(2);
        System.out.println(new MinimumNumberofVerticestoReachAllNodes().findSmallestSetOfVertices2(6,arr));
    }
}
