package Graphs.Medium;

import Graphs.Utils.MinEffortPair;

import java.util.*;

public class PathWithMinimumEffort {

    public int minimumEffortPath(int[][] heights) {
        Queue<MinEffortPair> queue=new PriorityQueue<>();
        queue.add(new MinEffortPair(0,0,0));
        boolean[][] visited=new boolean[heights.length][heights[0].length];
        int[][] shortestAbsolute=new int[heights.length][heights[0].length];
        int[] xPlus=new int[]{1,0,-1,0};
        int[] yPlus=new int[]{0,1,0,-1};

        while (!queue.isEmpty() && !visited[heights.length-1][heights[0].length-1]){
            MinEffortPair pair=queue.poll();
            if(visited[pair.x2][pair.y2])
                continue;
            visited[pair.x2][pair.y2]=true;
            shortestAbsolute[pair.x2][pair.y2]=pair.pathDiff;
            for(int k=0;k<4;k++){
                int x=pair.x2+xPlus[k];
                int y=pair.y2+yPlus[k];
                if(x>-1 && x< heights.length && y>-1 && y<heights[0].length && !visited[x][y]){
                    int pathMaxDiff=Math.max(Math.abs(heights[pair.x2][pair.y2]-heights[x][y]),pair.pathDiff);
                    queue.offer(new MinEffortPair(x,y,pathMaxDiff));
                }
            }
        }
        return shortestAbsolute[heights.length-1][heights[0].length-1];
    }

    public static void main(String[] args) {
        int[][] heights1={{1,2,2},{3,8,2},{5,3,5}};
        int[][] heights2={{3}};
        int[][] heights3={{3,4,7}};
        int[][] heights4={{3},{4},{8}};
        System.out.println(new PathWithMinimumEffort().minimumEffortPath(heights1));
        System.out.println(new PathWithMinimumEffort().minimumEffortPath(heights2));
        System.out.println(new PathWithMinimumEffort().minimumEffortPath(heights3));
        System.out.println(new PathWithMinimumEffort().minimumEffortPath(heights4));
    }
}
