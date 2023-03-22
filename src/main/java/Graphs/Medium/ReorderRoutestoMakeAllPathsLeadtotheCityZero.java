package Graphs.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ReorderRoutestoMakeAllPathsLeadtotheCityZero {
    int result=0;
    public int minReorder(int n, int[][] connections) {
        ArrayList<ArrayList<Integer>> incoming=new ArrayList<>();
        ArrayList<ArrayList<Integer>> outgoing=new ArrayList<>();

        for(int i=0;i<n;i++){
            incoming.add(new ArrayList<>());
            outgoing.add(new ArrayList<>());
        }
        for(int[] edge:connections){
            outgoing.get(edge[0]).add(edge[1]);
            incoming.get(edge[1]).add(edge[0]);
        }

        boolean[] visited=new boolean[n];
        visited[0]=true;
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()){
            int node=queue.poll();
            for(int child: outgoing.get(node)){
                if(!visited[child]){
                    visited[child]=true;
                    result++;
                    queue.offer(child);
                }
            }
            for(int child: incoming.get(node)){
                if(!visited[child]){
                    if(!visited[child]){
                        visited[child]=true;
                        queue.offer(child);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr={{0,1},{1,3},{2,3},{4,0},{4,5}};
        System.out.println(new ReorderRoutestoMakeAllPathsLeadtotheCityZero().minReorder(6,arr));
    }
}
