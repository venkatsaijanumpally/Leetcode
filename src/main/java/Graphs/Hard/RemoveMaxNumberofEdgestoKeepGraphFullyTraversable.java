package Graphs.Hard;

import java.util.*;

public class RemoveMaxNumberofEdgestoKeepGraphFullyTraversable {

    int[] parent;
    int[] rank;

    int[] rankRed;
    int[] rankGreen;

    int[] parentRed;
    int[] parentGreen;
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        return unionFindAlgo2(n,edges);
    }
    private int unionFindAlgo2(int n, int[][] edges){
        parent=new int[n+1];
        rank=new int[n+1];
        ArrayList<HashSet<Integer>> green=new ArrayList<>();
        ArrayList<HashSet<Integer>> red=new ArrayList<>();
        int e1=0,e2=0,r=0;
        for (int i=0;i<=n;i++) {
            parent[i]=i;
            rank[i]=1;
            green.add(new HashSet<>());
            red.add(new HashSet<>());
        }
        for (int[] edge:edges){
            if(edge[0]==3){
                if (union(edge[1],edge[2],parent,rank)){
                    red.get(edge[1]).add(edge[2]);
                    red.get(edge[2]).add(edge[1]);
                    green.get(edge[1]).add(edge[2]);
                    green.get(edge[2]).add(edge[1]);
                    e1++;
                    e2++;
                }
                else r++;
            }
        }

        rankRed=new int[n+1];
        rankGreen=new int[n+1];
        parentRed=new int[n+1];
        parentGreen=new int[n+1];
        System.arraycopy(rank,0,rankRed,0,n+1);
        System.arraycopy(rank,0,rankGreen,0,n+1);
        System.arraycopy(parent,0,parentRed,0,n+1);
        System.arraycopy(parent,0,parentGreen,0,n+1);
        for (int[] edge:edges){
            if(edge[0]==1){
                if(union(edge[1],edge[2],parentRed,rankRed)){
                    red.get(edge[1]).add(edge[2]);
                    red.get(edge[2]).add(edge[1]);
                    e1++;
                }
                else r++;
            }
        }
        for (int[] edge:edges){
            if(edge[0]==2){
                if(union(edge[1],edge[2],parentGreen,rankGreen)){
                    green.get(edge[1]).add(edge[2]);
                    green.get(edge[2]).add(edge[1]);
                    e2++;
                }
                else r++;
            }
        }

        if(e1==n-1 && e2==n-1) return r;
        return -1;
    }


    private boolean union(int x, int y, int[] parent, int[] rank) {
        int PX=getParent(x,parent);
        int PY=getParent(y,parent);
        if(PX==PY){
            return false;
        }

        if(rank[PX]<rank[PY]){
            parent[PX]=parent[PY];
        }
        else if(rank[PX]>rank[PY]){
            parent[PY]=parent[PX];
        }
        else {
            parent[PY]=parent[PX];
            rank[PX]++;
        }
        return true;
    }
    private int getParent(int node, int[] parent) {
        if(parent[node]==node)
            return node;
        else return parent[node]=getParent(parent[node], parent);
    }

    public static void main(String[] args) {
        int[][] edges1={{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
        int[][] edges2={{3,1,2},{3,2,3},{1,1,4},{2,1,4}};
        int[][] edges3={{3,2,3},{1,1,2},{2,3,4}};
        System.out.println(new RemoveMaxNumberofEdgestoKeepGraphFullyTraversable().maxNumEdgesToRemove(4,edges1));
        System.out.println(new RemoveMaxNumberofEdgestoKeepGraphFullyTraversable().maxNumEdgesToRemove(4,edges2));
        System.out.println(new RemoveMaxNumberofEdgestoKeepGraphFullyTraversable().maxNumEdgesToRemove(4,edges3));
    }
}
