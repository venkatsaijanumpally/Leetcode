package Graphs.Medium;

public class NumberofOperationstoMakeNetworkConnected {
    int[] parent;
    int[] rank;
    int components;
    /*
     * Approach: Union Find
     * Main idea: To connect a graph containing 'N' nodes we require N-1 edges
     * So if the graph has more than N-1 edges then the remaining edges are redundant which we can organize.
     *
     * So we calculate the number of components in the graph and number of edges using which we can find number of
     * redundant edges which can be used for connecting the components.
     */
    public int makeConnected(int n, int[][] connections) {
        parent=new int[n];
        rank=new int[n];
        components=n;

        for(int i=1;i<n;i++)
            parent[i]=i;
        for(int[] edge: connections){
            union(edge[0],edge[1]);
        }
        int edges=connections.length;
        if(edges<n-1)
            return -1;
        return components-1;
    }

    private void union(int a, int b) {
        int parentA=getParent(a);
        int parentB=getParent(b);

        if(parentA == parentB)
            return;
        else if(rank[parentA]==rank[parentB]){
            rank[parentA]++;
            parent[parentB]=parentA;
        }
        else if(rank[parentA]<rank[parentB])
            parent[parentA]=parentB;
        else parent[parentB]=parentA;
        components--;
    }

    private int getParent(int x) {
        if(x==parent[x])
            return x;
        return parent[x]=getParent(parent[x]);
    }

    public static void main(String[] args) {
        int[][] arr={{0,1},{0,2},{1,2}};
        int[][] arr2={{1,5},{1,7},{1,2},{1,4},{3,7},{4,7},{3,5},{0,6},{0,1},{0,4},{2,6},{0,3},{0,2}};
        //System.out.println(new NumberofOperationstoMakeNetworkConnected().makeConnected(4,arr));
        System.out.println(new NumberofOperationstoMakeNetworkConnected().makeConnected(12,arr2));
    }
}
