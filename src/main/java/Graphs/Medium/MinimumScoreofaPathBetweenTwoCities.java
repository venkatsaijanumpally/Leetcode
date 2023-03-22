package Graphs.Medium;

public class MinimumScoreofaPathBetweenTwoCities {

    int[] parent;
    int[] minEdge;
    int[] rank;

    public int minScore(int n, int[][] roads) {
        n++;
        parent=new int[n];
        minEdge=new int[n];
        rank=new int[n];

        for(int i=1;i<n;i++){
            minEdge[i]=Integer.MAX_VALUE;
            parent[i]=i;
            rank[i]=1;
        }
        for(int[] edge:roads){
            union(edge);
        }
        return minEdge[getParent(1)];
    }

    private boolean union(int[] edge) {
        int parent1=getParent(edge[0]);
        int parent2=getParent(edge[1]);

        if(parent1==parent2) {
            //minedge[parent1] and minedge[parent2] is same since they are in the same set.
            minEdge[parent1]=Math.min(edge[2],minEdge[parent2]);
            return false;
        }
        else {
            if(rank[parent1]==rank[parent2]){
                rank[parent1]++;
                parent[parent2]=parent1;
                minEdge[parent1]=Math.min(edge[2],Math.min(minEdge[parent1],minEdge[parent2]));
            }
            else if(rank[parent1]<rank[parent2]){
                parent[parent1]=parent2;
                minEdge[parent2]=Math.min(edge[2],Math.min(minEdge[parent1],minEdge[parent2]));
            }
            else {
                parent[parent2]=parent1;
                minEdge[parent1]=Math.min(edge[2],Math.min(minEdge[parent1],minEdge[parent2]));
            }
        }
        return true;
    }

    private int getParent(int node) {
        int parentOfNode=parent[node];
        if(parentOfNode==node)
            return node;
        return parent[node]=getParent(parent[node]);
    }

    public static void main(String[] args) {
        int[][] roads1={{1,2,9},{2,3,6},{2,4,5},{1,4,7}};
        System.out.println(new MinimumScoreofaPathBetweenTwoCities().minScore(4,roads1));
    }
}
