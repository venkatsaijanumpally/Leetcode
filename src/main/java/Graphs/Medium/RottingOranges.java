package Graphs.Medium;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    public int orangesRotting(int[][] grid){
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        Queue<int[]> queue=new LinkedList<>();
        int[] xPlus=new int[]{1,0,-1,0};
        int[] yPlus=new int[]{0,1,0,-1};
        int maxTime=0;
        int ones=0;
        //Add all '2' nodes to queue
        for(int i=0;i< grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==2){
                    visited[i][j]=true;
                    queue.add(new int[]{i,j});
                }
                else if(grid[i][j]==1)
                    ones++;
            }
        }

        //BFS
        while (!queue.isEmpty()){
            int queueSize=queue.size();
            for(int l=0;l< queueSize;l++){
                int[] pair= queue.poll();
                for(int k=0;k<4;k++){
                    int x=pair[0]+xPlus[k];
                    int y=pair[1]+yPlus[k];
                    if(x>-1 && x< grid.length && y>-1 && y<grid[0].length && grid[x][y]==1 && !visited[x][y]){
                        ones--;
                        grid[x][y]=2;
                        visited[x][y]=true;
                        queue.offer(new int[]{x,y});
                    }
                }
            }
            if(queue.size()>0) maxTime++;
        }

        return ones!=0?-1:maxTime;
    }

    public static void main(String[] args) {
        int[][] grid1={{2,1,1},{1,1,0},{0,1,1}};
        int[][] grid2={{1,1,2}};
        int[][] grid3={{2,1,1},{0,1,1},{1,0,1}};
        System.out.println(new RottingOranges().orangesRotting(grid1));
        System.out.println(new RottingOranges().orangesRotting(grid2));
        System.out.println(new RottingOranges().orangesRotting(grid3));
    }
}
