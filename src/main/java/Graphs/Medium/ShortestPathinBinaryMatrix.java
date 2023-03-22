package Graphs.Medium;

import Graphs.Utils.CoordinateXY;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathinBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0]==1) return -1;
        Queue<CoordinateXY> queue=new LinkedList<>();
        queue.add(new CoordinateXY(0,0));
        boolean[][] visited=new boolean[grid.length][grid.length];
        int[][] shortestDistance=new int[grid.length][grid.length];
        int[] xPlus=new int[]{1,0,-1,0,1,1,-1,-1};
        int[] yPlus=new int[]{0,1,0,-1,1,-1,1,-1};
        shortestDistance[0][0]=1;
        if(grid.length!=1)
            shortestDistance[grid.length-1][grid.length-1]=-1;
        visited[0][0]=true;

        while (!queue.isEmpty() && !visited[grid.length-1][grid.length-1]){
            CoordinateXY node= queue.poll();
            for(int k=0;k<8;k++){
                int x= node.x+xPlus[k];
                int y=node.y+yPlus[k];
                if(x>-1 && x< grid.length && y>-1 && y<grid.length && grid[x][y]==0 && !visited[x][y]){
                    visited[x][y]=true;
                    shortestDistance[x][y]=shortestDistance[node.x][node.y]+1;
                    queue.offer(new CoordinateXY(x,y));
                }
            }
        }
        return shortestDistance[grid.length-1][grid.length-1];
    }

    public static void main(String[] args) {
        int[][] grid=new int[][]{{0,1},{1,0}};
        int[][] grid2=new int[][]{{0,0,0},{1,1,0},{1,1,0}};
        int[][] grid3=new int[][]{{1}};
        int[][] grid4=new int[][]{{1,0,0},{1,1,0},{0,0,0}};
        int[][] grid5=new int[][]{{0}};
        System.out.println(new ShortestPathinBinaryMatrix().shortestPathBinaryMatrix(grid));
        System.out.println(new ShortestPathinBinaryMatrix().shortestPathBinaryMatrix(grid2));
        System.out.println(new ShortestPathinBinaryMatrix().shortestPathBinaryMatrix(grid3));
        System.out.println(new ShortestPathinBinaryMatrix().shortestPathBinaryMatrix(grid4));
        System.out.println(new ShortestPathinBinaryMatrix().shortestPathBinaryMatrix(grid5));
    }
}
