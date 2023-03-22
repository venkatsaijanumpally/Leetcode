package Graphs.Medium;

public class MostStonesRemovedwithSameRoworColumn {
    int[] parent=new int[1000];
    int[] rank=new int[1000];
    int components;
    public int removeStones(int[][] stones) {
        for(int i=0;i<1000;i++)
            parent[i]=i;
        components=stones.length;

        for(int i=0;i<stones.length;i++){
            for(int j=i+1;j<stones.length;j++){
                if(stones[i][0]==stones[j][0] || stones[i][1]==stones[j][1])
                    union(i,j);
            }
        }
        return stones.length-components;
    }

    public void union(int a, int b){
        int parentA=getParent(a);
        int parentB=getParent(b);

        if(parentA==parentB)
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
        int[][] arr={{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println(new MostStonesRemovedwithSameRoworColumn().removeStones(arr));
    }
}
