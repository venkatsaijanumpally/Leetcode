package Graphs.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ProcessRestrictedFriendRequests {
    int[] rank,parent;
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        rank=new int[n];
        parent=new int[n];
        for (int i=0;i<n;i++){
            parent[i]=i;
        }
        /*for (int[] restriction:restrictions){
            union(restriction[0],restriction[1]);
        }*/
        boolean[] result=new boolean[requests.length];
        for (int i=0;i< requests.length;i++){
            result[i]=true;
            int parentX=getParent(requests[i][0]);
            int parentY=getParent(requests[i][1]);
            boolean completed=false;
            for (int j=0;j< restrictions.length;j++){
                int x=getParent(restrictions[j][0]);
                int y=getParent(restrictions[j][1]);
                if((x==parentX && y==parentY) || (x==parentY && y==parentX)){
                    result[i]=false;
                    completed=true;
                    break;
                }
            }
            if(!completed)
                union(requests[i][0],requests[i][1]);
        }
        return result;
    }

    private boolean union(int x, int y) {
        int ParentX=getParent(x);
        int ParentY=getParent(y);
        if(ParentY==ParentX)
            return false;

        if(rank[ParentX]>rank[ParentY]){
            parent[ParentY]=parent[ParentX];
        } else if (rank[ParentX]<rank[ParentY]) {
            parent[ParentX]=parent[ParentY];
        }
        else {
            parent[ParentY]=parent[ParentX];
            rank[ParentX]++;
        }
        return true;
    }

    private int getParent(int src) {
        if(parent[src]==src)
            return src;
        return parent[src]=getParent(parent[src]);
    }

    public static void main(String[] args) {
        int[][] restrictions={{0,1}};
        int[][] restrictions2={{0,1},{1,2},{2,3}};
        int[][] requests1={{0,2},{2,1}};
        int[][] requests2={{1,2},{0,2}};
        int[][] requests3={{0,4},{1,2},{3,1},{3,4}};
        System.out.println(Arrays.toString(new ProcessRestrictedFriendRequests().friendRequests(3, restrictions, requests1)));
        System.out.println(Arrays.toString(new ProcessRestrictedFriendRequests().friendRequests(3, restrictions, requests2)));
        System.out.println(Arrays.toString(new ProcessRestrictedFriendRequests().friendRequests(5, restrictions2, requests3)));
    }
}
