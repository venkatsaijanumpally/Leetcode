package Graphs.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix01 {
    /*
     * Add all zero nodes into a queue and run BFS this will give shortest distance for every '1' node to its zero node.
     */
    public int[][] updateMatrix(int[][] mat) {
        boolean[][] visited=new boolean[mat.length][mat[0].length];
        int[][] dist=new int[mat.length][mat[0].length];
        int[] xPlus=new int[]{1,0,-1,0};
        int[] yPlus=new int[]{0,1,0,-1};
        Queue<int[]> queue=new LinkedList<>();


        for(int i=0;i< mat.length;i++){
            for (int j=0;j< mat[0].length;j++){
                if(mat[i][j]==0){
                    queue.add(new int[]{i,j});
                    visited[i][j]=true;
                }
            }
        }

        while (!queue.isEmpty()){
            /*int size= queue.size();
            for(int i=0;i<size;i++){*/
                int[] node= queue.poll();
                for(int k=0;k<4;k++){
                    int x=node[0]+xPlus[k];
                    int y=node[1]+yPlus[k];
                    if(x>=0 && x< mat.length && y>=0 && y<mat[0].length && !visited[x][y] && mat[x][y]==1){
                        visited[x][y]=true;
                        dist[x][y]=dist[node[0]][node[1]]+1;
                        queue.add(new int[]{x,y});
                    }
                }
            //}
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] mat1={{0,0,0},{0,1,0},{0,0,0}};
        int[][] mat2={{0,0,0},{0,1,0},{1,1,1}};
        System.out.println(Arrays.deepToString(new Matrix01().updateMatrix(mat1)));
        System.out.println(Arrays.deepToString(new Matrix01().updateMatrix(mat2)));
    }
}
