package BFS_DFS.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {
    List<List<Integer>> result=new ArrayList<>();
    boolean[][] pacific;
    boolean[][] atlantic;
    Queue<int []> pacificQueue=new LinkedList<>();
    Queue<int []> atlanticQueue=new LinkedList<>();
    int rows;
    int columns;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        rows= heights.length;
        columns =heights[0].length;
        pacific=new boolean[rows][columns];
        atlantic=new boolean[rows][columns];
        for(int i = 0; i< columns; i++){
            pacific[0][i]=true;
            pacificQueue.add(new int[]{0,i});
        }
        for(int j=1;j<rows;j++){
            pacificQueue.add(new int[]{j,0});
            pacific[j][0]=true;
        }
        for(int i=0;i<rows;i++){
            atlantic[i][columns -1]=true;
            atlanticQueue.add(new int[]{i, columns -1});
        }
        for(int j = 0; j< columns -1; j++){
            atlantic[rows-1][j]=true;
            atlanticQueue.add(new int[]{rows-1,j});
        }

        bfs(pacificQueue, heights, pacific);
        bfs(atlanticQueue, heights, atlantic);

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(pacific[i][j] && atlantic[i][j]){
                    List<Integer> list=new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    result.add(list);
                }
            }
        }
        return result;
    }

    private void bfs(Queue<int[]> queue, int[][] heights, boolean[][] visited) {
        while (!queue.isEmpty()){
            int[] node= queue.poll();
            addNode(queue, heights, node[0], node[1], node[0]-1,node[1], visited);
            addNode(queue, heights, node[0], node[1], node[0]+1,node[1], visited);
            addNode(queue, heights, node[0], node[1], node[0],node[1]+1, visited);
            addNode(queue, heights, node[0], node[1], node[0],node[1]-1, visited);
        }
    }

    private void addNode(Queue<int[]> queue, int[][] heights, int previ, int prevj, int i, int j, boolean[][] array) {
        if(i<0 || i>=rows || j<0 || j>=columns || array[i][j] || heights[previ][prevj]>heights[i][j])
            return;
        array[i][j]=true;
        queue.offer(new int[]{i,j});
    }

    public static void main(String[] args) {
        int[][] height={{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        int[][] height2={{3,3,3},{3,1,3},{0,2,4}};
        //System.out.println(new PacificAtlanticWaterFlow().pacificAtlantic(height));
        System.out.println(new PacificAtlanticWaterFlow().pacificAtlantic(height2));
    }
}
