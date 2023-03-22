package Graphs.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class NumberofEnclaves {
    boolean[][] visited;
    /*
     * Approach 1:
     * Run BFS on every node on the perimeter of the matrix with value '1' which removes all connected '1' nodes. Now count
     * all ones.
     *
     * Approach 2:
     * Not implemented
     * Add all '1' perimeter nodes into a queue and perform BFS at the end instead of running BFS for every '1' node.
     */
    public int numEnclaves(int[][] grid) {
        visited=new boolean[grid.length][grid[0].length];
        for(int i=0;i< grid[0].length;i++){
            if(grid[0][i]==1)
                bfs(0,i,grid);
        }
        for(int i=0;i< grid[0].length;i++){
            if(grid[grid.length-1][i]==1)
                bfs(grid.length-1, i,grid);
        }
        for(int i=0;i< grid.length;i++){
            if(grid[i][0]==1)
                bfs(i,0,grid);
        }
        for(int i=0;i< grid.length;i++){
            if(grid[i][grid[0].length-1]==1)
                bfs(i,grid[0].length-1,grid);
        }

        int ones=0;
        for(int i=1;i< grid.length-1;i++){
            for(int j=1;j<grid[0].length-1;j++)
                if(grid[i][j]==1)
                    ones++;
        }
        return ones;
    }

    private void bfs(int p, int q, int[][] mat) {
        Queue<int[]> queue=new LinkedList<>();
        int[] xPlus=new int[]{1,0,-1,0};
        int[] yPlus=new int[]{0,1,0,-1};
        mat[p][q]=0;
        queue.add(new int[]{p,q});
        while (!queue.isEmpty()){
            int[] node= queue.poll();
            for(int k=0;k<4;k++){
                int x=node[0]+xPlus[k];
                int y=node[1]+yPlus[k];
                if(x>=0 && x< mat.length && y>=0 && y<mat[0].length && !visited[x][y] && mat[x][y]==1){
                    visited[x][y]=true;
                    mat[x][y]=0;
                    queue.add(new int[]{x,y});
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid={{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        int[][] grid2={{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}};
        int[][] grid3={{0,0,0,1,1,1,0,1,0,0},{1,1,0,0,0,1,0,1,1,1},{0,0,0,1,1,1,0,1,0,0},{0,1,1,0,0,0,1,0,1,0},{0,1,1,1,1,1,0,0,1,0},{0,0,1,0,1,1,1,1,0,1},{0,1,1,0,0,0,1,1,1,1},{0,0,1,0,0,1,0,1,0,1},{1,0,1,0,1,1,0,0,0,0},{0,0,0,0,1,1,0,0,0,1}};
        System.out.println(new NumberofEnclaves().numEnclaves(grid));
        System.out.println(new NumberofEnclaves().numEnclaves(grid2));
        System.out.println(new NumberofEnclaves().numEnclaves(grid3));
    }
}
