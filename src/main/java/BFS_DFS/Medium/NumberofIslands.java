package BFS_DFS.Medium;

public class NumberofIslands {
    /*
     * Approach: Union Find
     * parent array saves the coordinates of a node's parent.
     * steps:
     * 1. Every coordinate with value '1' is considered as a separate island at the beegining.
     * 2. for first row union the nodes that have '1' to left of it.
     * 3. for the first column union the nodes that have '1' on top of it.
     * 4. for remaining nodes union node to left if left node is '1' and then union the same node to upside node if '1'.
     */
    int[][][] parent;
    int[][] rank;
    int islands;
    public int numIslands(char[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        parent=new int[n][m][2];
        rank=new int[n][m];
        islands=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]=='1'){
                    parent[i][j]=new int[]{i,j};
                    islands++;
                }
            }
        }
        for(int i=1;i<m;i++){
            if(grid[0][i]=='1' && grid[0][i-1]=='1')
                union(0,i,0,i-1);
        }
        for(int j=1;j<n;j++){
            if(grid[j][0]=='1' && grid[j-1][0]=='1')
                union(j,0,j-1,0);
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(grid[i][j]=='1'){
                    if(grid[i][j-1]=='1')
                        union(i,j,i,j-1);
                    if(grid[i-1][j]=='1')
                        union(i,j,i-1,j);
                }
            }
        }
        return islands;
    }
    public void union(int i1, int j1, int i2, int j2){
        int[] parent1=getParent(i1,j1);
        int[] parent2=getParent(i2,j2);

        if(parent1[0]==parent2[0] && parent1[1]==parent2[1])
            return;
        else {
            int parent1Rank=rank[parent1[0]][parent1[1]];
            int parent2Rank=rank[parent2[0]][parent2[1]];
            if(parent1Rank>parent2Rank)
                parent[parent2[0]][parent2[1]]=parent[parent1[0]][parent1[1]];
            else if(parent1Rank<parent2Rank)
                parent[parent1[0]][parent1[1]]=parent[parent2[0]][parent2[1]];
            else{
                parent[parent2[0]][parent2[1]]=parent[parent1[0]][parent1[1]];
                rank[parent1[0]][parent1[1]]++;
            }
            islands--;
        }
    }

    private int[] getParent(int i, int j) {
        int[] parents=parent[i][j];
        if(parents[0]==i && parents[1]==j)
            return parents;
        else return parent[i][j]=getParent(parents[0],parents[1]);
    }

    public static void main(String[] args) {
        char[][] arr={{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(new NumberofIslands().numIslands(arr));
    }
}
