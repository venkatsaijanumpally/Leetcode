package Graphs.Medium;

import java.util.HashSet;

public class FindChampionII {
    public int findChampion(int n, int[][] edges) {
        /*HashSet<Integer> hs = new HashSet<>();
        HashSet<Integer> removed = new HashSet<>();
        for (int[] edge: edges){
            if(!removed.contains(edge[0])){
                hs.add(edge[0]);
                removed.add(edge[1]);
            }
            else {
                removed.add(edge[1]);
            }
        }*/
        /*HashSet<Integer> hs = new HashSet<>();
        for (int i=0;i<n;i++) hs.add(i);
        for (int[] edge: edges){
            hs.remove(edge[1]);
        }

        return hs.size()>1?-1:hs.iterator().next();*/

        int[] inDegree = new int[n];
        int count = n;
        for (int[] edge: edges){
            if(inDegree[edge[1]]==0)
                count--;
            inDegree[edge[1]]++;
        }

        if (count>1) return -1;
        for (int i=0;i<n;i++){
            if(inDegree[i]==0)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2}};
        int[][] edges2 = {{0,2},{1,3},{1,2}};
        int[][] edges3 = {{0,1},{1,2},{3,2}};
        System.out.println(new FindChampionII().findChampion(3,edges));
        System.out.println(new FindChampionII().findChampion(4,edges2));
        System.out.println(new FindChampionII().findChampion(4,edges3));
    }
}
