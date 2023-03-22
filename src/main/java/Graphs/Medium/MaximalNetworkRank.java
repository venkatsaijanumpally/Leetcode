package Graphs.Medium;

import java.util.ArrayList;

public class MaximalNetworkRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] hasconnection=new boolean[n][n];
        int[] numconnections=new int[n];

        for(int[] edge:roads){
            numconnections[edge[0]]++;
            numconnections[edge[1]]++;
            hasconnection[edge[0]][edge[1]]=true;
            hasconnection[edge[1]][edge[0]]=true;
        }

        int max=0;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                max=Math.max(max, numconnections[i] + numconnections[j] - (hasconnection[i][j]?1:0) );
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] arr={{0,1},{0,3},{1,2},{1,3}};
        int[][] arr2={{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};
        System.out.println(new MaximalNetworkRank().maximalNetworkRank(4,arr));
        System.out.println(new MaximalNetworkRank().maximalNetworkRank(8,arr2));
    }
}
