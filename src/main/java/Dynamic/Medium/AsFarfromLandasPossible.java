package Dynamic.Medium;

import Graphs.Utils.CoordinateXY;

import java.util.LinkedList;
import java.util.Queue;

public class AsFarfromLandasPossible {
    public int maxDistance(int[][] grid) {
        int rows= grid.length, columns=grid[0].length;
        boolean[][] visited=new boolean[rows][columns];
        Queue<CoordinateXY> queue=new LinkedList<>();
        for(int i=0;i< rows;i++){
            for(int j=0;j<columns;j++){
                if(grid[i][j]==1){
                    queue.add(new CoordinateXY(i,j));
                    visited[i][j]=true;
                }
            }
        }
        int dist=-1;
        int[] xPlus=new int[]{1,0,-1,0};
        int[] yPlus=new int[]{0,1,0,-1};
        while (!queue.isEmpty()){
            dist++;
            int size= queue.size();
            while (size>0){
                size--;
                CoordinateXY xy=queue.poll();
                for(int i=0;i<4;i++){
                    int x=xy.x+xPlus[i];
                    int y=xy.y+yPlus[i];
                    if(x>-1 && y>-1 && x<rows && y<columns && !visited[x][y]){
                        visited[x][y]=true;
                        queue.offer(new CoordinateXY(x,y));
                    }
                }
            }
        }
        if(dist<1)
            return -1;
        return dist;
    }

    public static void main(String[] args) {
        int[][] grid={{1,0,1},{0,0,0},{1,0,1}};
        System.out.println(new AsFarfromLandasPossible().maxDistance(grid));
    }
}
