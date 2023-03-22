package Graphs.Hard;

import Graphs.Basics.WeightedPair;
import Graphs.Utils.CoordinateDistance;
import Graphs.Utils.WeightedPairString;

import java.util.*;

public class MinimumCosttoMakeatLeastOneValidPathinaGrid {
    /*
     * Build a graph where grid[i][j] is connected to all the four side-adjacent cells with weighted edge. the weight
     * is 0 if the sign is pointing to the adjacent cell or 1 otherwise.
     * Both solutions are optimal and have same complexity.
     */
    public int minCost2(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        ArrayList<ArrayList<ArrayList<CoordinateDistance>>> adj=new ArrayList<>();

        int[] xPlus = new int[]{0, 0, 1, -1};
        int[] yPlus = new int[]{1, -1, 0, 0};
        for (int i = 0; i < rows; i++) {
            adj.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                adj.get(i).add(new ArrayList<>());
                for (int k = 0; k < 4; k++) {
                    int x = i + xPlus[k];
                    int y = j + yPlus[k];
                    if (x < rows && x >= 0 && y >= 0 && y < cols) {
                        //Here we check if the movement is in the same direction by grid[i][j]==k+1?0:1. We ordered the
                        // xPlus and yPlus array in such as way this works.
                        adj.get(i).get(j).add(new CoordinateDistance(x,y,grid[i][j]==k+1?0:1));
                    }
                }
            }
        }

        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];
        Queue<CoordinateDistance> queue = new PriorityQueue<>();
        queue.add(new CoordinateDistance(0,0, 0));
        while (!visited[rows - 1][cols - 1]) {
            CoordinateDistance node = queue.poll();
            if(visited[node.x][node.y]) continue;
            visited[node.x][node.y] = true;
            distance[node.x][node.y]=node.dist;
            for (CoordinateDistance neighbour : adj.get(node.x).get(node.y)) {
                if (!visited[neighbour.x][neighbour.y]) {
                    queue.add(new CoordinateDistance(neighbour.x, neighbour.y, node.dist + neighbour.dist));
                }
            }
        }

        return distance[rows-1][cols-1];
    }



    //!Optimal
    //Here we don't use adj list but traverse directly by expanding through the shortest path.
    public int minCost3(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[] xPlus = new int[]{0, 0, 1, -1};
        int[] yPlus = new int[]{1, -1, 0, 0};

        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];
        Queue<CoordinateDistance> queue = new PriorityQueue<>();
        queue.add(new CoordinateDistance(0,0, 0));
        while (!visited[rows - 1][cols - 1]) {
            CoordinateDistance node = queue.poll();
            if(visited[node.x][node.y]) continue;
            visited[node.x][node.y] = true;
            distance[node.x][node.y]=node.dist;
            for (int k = 0; k < 4; k++) {
                int x = node.x + xPlus[k];
                int y = node.y + yPlus[k];
                if (x >= rows || x < 0 || y < 0 || y >= cols || visited[x][y]) continue;

                queue.add(new CoordinateDistance(x,y,node.dist+(grid[node.x][node.y]==k+1?0:1)));
            }
        }

        return distance[rows-1][cols-1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1, 1, 1, 1}, {2, 2, 2, 2}, {1, 1, 1, 1}, {2, 2, 2, 2}};
        int[][] grid2 = {{1,1,3}, {3,2,2}, {1,1,4}};
        int[][] grid3 = {{4,3,3,4,4,3,3,3,2,1,1,2,3},{4,2,2,3,2,2,4,2,1,2,3,2,2},{1,2,4,3,1,4,4,3,1,1,3,2,3},{1,1,3,4,4,3,3,2,3,3,2,1,1},{4,3,2,2,4,2,1,4,4,1,1,3,4},{1,1,1,4,2,3,2,2,3,2,4,3,3},{4,4,1,1,4,3,3,4,2,3,3,1,2},{1,4,4,2,3,4,3,4,4,3,2,3,2},{4,1,1,1,1,1,4,3,4,4,1,3,2},{2,2,2,1,2,1,1,2,1,4,3,4,3},{1,4,1,2,4,2,2,1,1,1,1,1,2},{3,3,2,3,4,2,4,4,3,4,4,2,3},{2,4,3,2,2,4,1,2,1,4,2,3,2},{1,3,1,2,3,3,1,2,2,3,4,2,3},{2,3,1,1,2,3,1,2,1,4,4,1,3},{3,3,2,4,3,2,4,2,4,1,4,4,1},{1,4,3,3,3,2,4,3,1,3,4,3,2},{3,1,1,2,4,3,1,2,2,4,1,1,4},{2,1,4,2,4,4,4,2,4,2,2,1,2},{1,2,2,2,2,2,2,3,1,3,2,2,2},{3,4,3,1,3,1,1,1,2,1,4,3,4},{1,3,3,2,4,1,3,1,4,2,2,2,1},{1,1,3,2,3,3,4,1,3,1,2,1,3},{1,1,3,1,4,4,3,3,1,2,3,2,3},{1,2,2,2,4,3,1,4,1,2,2,2,4},{1,2,4,3,4,3,1,1,2,4,4,3,1},{2,1,1,3,1,4,3,4,3,2,2,1,3},{1,1,1,1,1,3,3,1,2,2,3,1,2},{2,3,3,1,4,3,3,1,4,2,3,2,4},{2,4,3,2,2,1,2,2,3,1,1,2,4},{1,3,2,4,2,4,2,2,3,4,2,1,4},{3,4,4,2,2,4,1,4,3,2,1,4,4},{4,3,1,4,3,4,2,1,3,3,1,2,2},{2,1,4,3,2,3,1,1,4,4,1,4,4},{3,1,2,2,2,4,2,3,3,2,4,1,1},{2,1,1,3,2,2,2,2,4,3,4,1,1},{2,2,3,2,2,1,3,3,4,2,1,4,1},{2,1,3,2,1,1,1,4,2,4,3,1,2},{1,2,1,3,1,3,1,4,2,2,1,3,3},{3,4,3,3,3,2,2,4,4,2,2,1,2},{2,3,3,2,3,4,1,3,4,4,1,4,2},{4,3,4,2,3,4,4,4,1,3,2,1,3},{4,1,3,4,4,2,2,2,3,4,3,4,2},{1,3,3,3,4,3,3,2,3,3,1,4,3},{3,3,3,2,4,2,2,2,3,2,4,3,4},{2,1,4,1,4,3,1,4,4,2,4,2,3},{2,2,3,1,2,4,3,3,1,2,2,3,3},{2,3,4,4,1,1,1,2,3,4,3,3,4},{4,1,4,3,3,4,4,1,1,3,2,3,4}};
        System.out.println(new MinimumCosttoMakeatLeastOneValidPathinaGrid().minCost3(grid3));
        //System.out.println(new MinimumCosttoMakeatLeastOneValidPathinaGrid().minCost(grid2));
    }
}
