package Graphs.Medium;

import Graphs.Utils.CoordinateDistance;
import Graphs.Utils.CoordinateXY;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {

    Queue<CoordinateDistance> queue=new LinkedList<>();
    int[] xPlus=new int[]{1,0,0,-1};
    int[] yPlus=new int[]{0,1,-1,0};
    boolean[][] visited;
    public int shortestBridge(int[][] grid) {
        visited=new boolean[grid.length][grid.length];
        boolean found=false;
        for(int i=0;i< grid.length;i++){
            for(int j=0;j< grid.length;j++){
                if(grid[i][j]==1){
                    firstIslandColouring(grid,i,j);
                    found=true;
                    break;
                }
            }
            if(found)
                break;
        }

        while (true){
            CoordinateDistance coordinates=queue.poll();
            for(int k=0;k<4;k++){
                int x=coordinates.x+xPlus[k];
                int y=coordinates.y+yPlus[k];
                if(x<0 || y<0 || x>= grid.length || y>= grid.length || visited[x][y])
                    continue;
                if(grid[x][y]==1)
                    return coordinates.dist;
                visited[x][y]=true;
                queue.add(new CoordinateDistance(x,y,coordinates.dist+1));
            }
        }
    }

    private void firstIslandColouring(int[][] grid, int i, int j) {
        queue.add(new CoordinateDistance(i,j,0));
        visited[i][j]=true;
        grid[i][j]=2;
        for(int k=0;k<4;k++){
            int x=i+xPlus[k];
            int y=j+yPlus[k];
            if(x<0 || y<0 || x>= grid.length || y>= grid.length || grid[x][y]!=1)
                continue;
            firstIslandColouring(grid,x,y);
        }
    }

    public static void main(String[] args) {
        int[][] grid1={{0,1},{1,0}};
        int[][] grid2={{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
        int[][] grid3={{0,1,0},{0,0,0},{0,0,1}};
        System.out.println(new ShortestBridge().shortestBridge(grid1));
        System.out.println(new ShortestBridge().shortestBridge(grid2));
        System.out.println(new ShortestBridge().shortestBridge(grid3));
    }
}
