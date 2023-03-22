package Graphs.Medium;

public class NumberofProvinces {
    /*
     * https://www.youtube.com/watch?v=3gbO7FDYNFQ&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=25
     * Approach: Union Find
     */
    int[] parent;
    int[] rank;
    int components;
    public int findCircleNum(int[][] isConnected) {
        int numNodes=isConnected.length;
        components=numNodes;
        parent=new int[numNodes+1];
        rank=new int[numNodes+1];

        for(int i=1;i<=numNodes;i++){
            parent[i]=i;
        }

        for(int i=0;i<numNodes;i++){
            for(int j=i+1;j<numNodes;j++){
                if(isConnected[i][j]==1){
                    if(findParent(i)!=findParent(j))
                        union(i,j);
                }
            }
        }
        return components;
    }

    public void union(int a, int b){
        int parentA=findParent(a);
        int parentB=findParent(b);

        if(rank[parentA]>rank[parentB])
            parent[parentB]=parentA;
        else if(rank[parentB]>rank[parentA])
            parent[parentA]=parentB;
        else {
            parent[parentB]=parentA;
            rank[parentA]++;
        }
        components--;
    }

    public int findParent(int node){
        if(node==parent[node])
            return node;
        return parent[node]=findParent(parent[node]);
    }

    public static void main(String[] args) {
        int[][] arr={{1,1,0},{1,1,0},{0,0,1}};
        int[][] arr2={{1,0,0},{0,1,0},{0,0,1}};
        System.out.println(new NumberofProvinces().findCircleNum(arr));
        System.out.println(new NumberofProvinces().findCircleNum(arr2));
    }
}
