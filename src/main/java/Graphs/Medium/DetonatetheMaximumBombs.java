package Graphs.Medium;

import java.util.ArrayList;
import java.util.HashMap;

public class DetonatetheMaximumBombs {
    boolean[] visited;
    int max=1;
    ArrayList<ArrayList<Integer>> adj;
    public int maximumDetonation(int[][] bombs) {
        adj=new ArrayList<>();
        for(int i=0;i< bombs.length;i++)
            adj.add(new ArrayList<>());
        for(int i=0;i< bombs.length;i++){
            long x1=bombs[i][0],y1=bombs[i][1],r1=bombs[i][2];
            for(int j=0;j< bombs.length;j++){
                if(j==i) continue;
                if(inRange(x1-bombs[j][0],y1-bombs[j][1],r1)){
                    adj.get(i).add(j);
                }
            }
        }

        for(int i=0;i< bombs.length;i++){
            visited=new boolean[bombs.length];
            max=Math.max(dfs(i),max);
        }
        return max;
    }

    private boolean inRange(long xdiff, long ydiff, long r1) {
        return xdiff*xdiff+ydiff*ydiff <= r1*r1;
    }

    private int dfs(int node) {
        visited[node]=true;
        int count=0;
        for(int neighbour: adj.get(node)){
            if(!visited[neighbour])
                count+=dfs(neighbour);
        }
        return 1+count;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        int[][] arr2={{1,1,5},{10,10,5}};
        int[][] arr3={{2,1,3},{6,1,4}};
        int[][] arr4={{1,1,100000},{100000,100000,1}};
        int[][] arr5={{56,80,2},{55,9,10},{32,75,2},{87,89,1},{61,94,3},{43,82,9},{17,100,6},{50,6,7},{9,66,7},{98,3,6},{67,50,2},{79,39,5},{92,60,10},{49,9,9},{42,32,10}};
        int[][] arr6={{37207,2653,5261},{40784,59523,20635},{16390,1426,39102},{42236,12,96855},{72839,62027,61667},{60691,58191,48447},{42932,46579,41248},{35868,43119,6870},{41693,98905,17374},{43441,1266,41621}};
        System.out.println(new DetonatetheMaximumBombs().maximumDetonation(arr));
        System.out.println(new DetonatetheMaximumBombs().maximumDetonation(arr2));
        System.out.println(new DetonatetheMaximumBombs().maximumDetonation(arr3));
        System.out.println(new DetonatetheMaximumBombs().maximumDetonation(arr4));
        System.out.println(new DetonatetheMaximumBombs().maximumDetonation(arr5));
        System.out.println(new DetonatetheMaximumBombs().maximumDetonation(arr6));

    }
}
