package Graphs.Hard;

import java.util.Arrays;

public class CheckingExistenceofEdgeLengthLimitedPaths {
    int[] rank,parent;
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries){
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++) parent[i]=i;
        int m= queries.length;
        int[][] sortedQueries = new int[m][4];
        for (int i = 0; i < m; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.sort(sortedQueries, (a,b) -> a[2] - b[2]);
        Arrays.sort(edgeList,(a,b)->a[2]-b[2]);
        int index=0;
        boolean[] result=new boolean[m];

        for(int[] query:sortedQueries){
            int src=query[0];
            int dest=query[1];
            int limit=query[2];
            int originalIndex=query[3];

            while (index< edgeList.length && edgeList[index][2]<limit){
                union(edgeList[index][0],edgeList[index][1]);
                index++;
            }

            if(getParent(src)==getParent(dest))
                result[originalIndex]=true;
        }
        return result;
    }

    private void union(int src, int dest) {
        int ParentSrc=getParent(src);
        int ParentDest=getParent(dest);
        if(ParentDest==ParentSrc)
            return;

        if(rank[ParentSrc]>rank[ParentDest]){
            parent[ParentDest]=parent[ParentSrc];
        } else if (rank[ParentSrc]<rank[ParentDest]) {
            parent[ParentSrc]=parent[ParentDest];
        }
        else {
            parent[ParentDest]=parent[ParentSrc];
            rank[ParentSrc]++;
        }
    }

    private int getParent(int src) {
        if(parent[src]==src)
            return src;
        return parent[src]=getParent(parent[src]);
    }

    public static void main(String[] args) {
        int[][] edges1={{0,1,2},{1,2,4},{2,0,8},{1,0,16}};
        int[][] edges2={{0,1,10},{1,2,5},{2,3,9},{3,4,13}};
        int[][] queries1={{0,1,2},{0,2,5}};
        int[][] queries2={{0,4,14},{1,4,13}};
        int[][] edges3={{9,1,53},{3,2,66},{12,5,99},{9,7,26},{1,4,78},{11,1,62},{3,10,50},{12,1,71},{12,6,63},{1,10,63},{9,10,88},{9,11,59},{1,4,37},{4,2,63},{0,2,26},{6,12,98},{9,11,99},{4,5,40},{2,8,25},{4,2,35},{8,10,9},{11,9,25},{10,11,11},{7,6,89},{2,4,99},{10,4,63}};
        int[][] queries3={{9,7,65},{9,6,1},{4,5,34},{10,8,43},{3,7,76},{4,2,15},{7,6,52},{2,0,50},{7,6,62},{1,0,81},{4,5,35},{0,11,86},{12,5,50},{11,2,2},{9,5,6},{12,0,95},{10,6,9},{9,4,73},{6,10,48},{12,0,91},{9,10,58},{9,8,73},{2,3,44},{7,11,83},{5,3,14},{6,2,33}};
        System.out.println(Arrays.toString(new CheckingExistenceofEdgeLengthLimitedPaths().distanceLimitedPathsExist(3, edges1, queries1)));
        System.out.println(Arrays.toString(new CheckingExistenceofEdgeLengthLimitedPaths().distanceLimitedPathsExist(5, edges2, queries2)));//{9,10,58},{9,8,73}
        System.out.println(Arrays.toString(new CheckingExistenceofEdgeLengthLimitedPaths().distanceLimitedPathsExist(13, edges3, queries3)));
    }
}
