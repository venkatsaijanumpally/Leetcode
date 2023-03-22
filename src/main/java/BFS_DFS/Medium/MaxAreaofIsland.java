package BFS_DFS.Medium;

public class MaxAreaofIsland {
    /*
     * Both Solutions are good, one uses union find and another dfs
     * Approach 1: Union Find
     * form all the unions and track members of a union using groupMembers
     */
    int maxArea=0;
    int[][] rank;
    int[][] groupMembers;
    int[][][] parent;
    public int maxAreaOfIsland(int[][] grid) {
        int rows= grid.length;
        int columns=grid[0].length;
        rank=new int[rows][columns];
        groupMembers=new int[rows][columns];
        parent=new int[rows][columns][2];

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(grid[i][j]==1){
                    rank[i][j]=1;
                    groupMembers[i][j]=1;
                    parent[i][j]=new int[]{i,j};
                    maxArea=1;
                }
            }
        }
        for(int i=0;i<rows-1;i++){
            for(int j=0;j<columns-1;j++){
                if(grid[i][j]==1){
                    if(grid[i][j+1]==1)
                        union(i,j,i,j+1);
                    if(grid[i+1][j]==1)
                        union(i,j,i+1,j);
                }
            }
            //Last Column
            if(grid[i][columns-1]==1 && grid[i+1][columns-1]==1)
                union(i,columns-1,i+1,columns-1);
        }
        //Last Row
        for(int j=0;j<columns-1;j++){
            if(grid[rows-1][j]==1){
                if(grid[rows-1][j+1]==1)
                    union(rows-1,j,rows-1,j+1);
            }
        }
        return maxArea;
    }

    private void union(int i, int j, int i1, int j1) {
        int[] parent1=getParent(i,j);
        int[] parent2=getParent(i1,j1);

        if(parent1[0]==parent2[0] && parent1[1]==parent2[1]) return;

        if(rank[parent1[0]][parent1[1]]>rank[parent2[0]][parent2[1]]){
            parent[parent2[0]][parent2[1]]=parent1;
            groupMembers[parent1[0]][parent1[1]]+=groupMembers[parent2[0]][parent2[1]];
        }
        else if(rank[parent2[0]][parent2[1]] > rank[parent1[0]][parent1[1]]){
            parent[parent1[0]][parent1[1]]=parent2;
            groupMembers[parent2[0]][parent2[1]]+=groupMembers[parent1[0]][parent1[1]];
            if(groupMembers[parent2[0]][parent2[1]]>maxArea)
                maxArea=groupMembers[parent2[0]][parent2[1]];
            return;
        }
        else {
            parent[parent2[0]][parent2[1]]=parent1;
            rank[parent1[0]][parent1[1]]++;
            groupMembers[parent1[0]][parent1[1]]+=groupMembers[parent2[0]][parent2[1]];
        }

        if(groupMembers[parent1[0]][parent1[1]]>maxArea)
            maxArea=groupMembers[parent1[0]][parent1[1]];
    }

    private int[] getParent(int i, int j) {
        int[] parents=parent[i][j];
        if(parents[0]==i && parents[1]==j)
            return parents;
        return parent[i][j]=getParent(parents[0],parents[1]);
    }


    /*
     * Approach: DFS
     * while traversing whenever '1' is found then dfs all its neighbouring nodes and mark them '0' once traversed.
     */
    //!Optimal
    int[][] grid;
    int rows;
    int columns;
    public int maxAreaOfIslandDFS(int[][] grid){
        rows= grid.length;
        columns=grid[0].length;
        this.grid=grid;

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(grid[i][j]==1)
                    maxArea=Math.max(maxArea,dfs(i,j));
            }
        }
        return maxArea;
    }

    private int dfs(int i, int j) {
        if(grid[i][j]==1){
            grid[i][j]=0;
            int l=0,r=0,u=0,d=0;
            if(i+1<rows)
                u=dfs(i+1,j);
            if(i-1>-1)
                d=dfs(i-1,j);
            if(j+1<columns)
                r=dfs(i,j+1);
            if(j-1>-1)
                l=dfs(i,j-1);
            return 1+l+r+u+d;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] arr={{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(new MaxAreaofIsland().maxAreaOfIsland(arr));
        System.out.println(new MaxAreaofIsland().maxAreaOfIslandDFS(arr));
    }
}
