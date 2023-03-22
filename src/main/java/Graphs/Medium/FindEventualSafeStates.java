package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class FindEventualSafeStates {
    List<Integer> result;
    HashSet<Integer> hs;
    boolean[] visited;
    boolean[] dfsVisited;
    public List<Integer> eventualSafeNodes(int[][] graph) {
        result=new ArrayList<>();
        hs=new HashSet<>();
        visited=new boolean[graph.length];
        dfsVisited=new boolean[graph.length];
        for(int i=0;i< graph.length;i++)
            hs.add(i);
        for(int i=0;i< graph.length;i++){
            if(!visited[i] && hs.contains(i))
                dfs(graph,i,new ArrayList<>());
        }
        result.addAll(hs);
        return result;
    }

    private boolean dfs(int[][] graph, int curr, ArrayList<Integer> path) {
        boolean flag=false;
        if(dfsVisited[curr] || !hs.contains(curr)){
            //List<Integer> temp= path.subList(path.indexOf(curr),path.size());
            //hs.removeAll(path);
            /*path.clear();
            Arrays.fill(dfsVisited,Boolean.FALSE);*/
            flag=true;
        }
        else if(!visited[curr]){
            dfsVisited[curr]=true;
            visited[curr]=true;
            path.add(curr);
            for(int child: graph[curr]){
                if(dfs(graph,child,path)){
                    flag=true;
                    break;
                }
            }
            path.remove(path.size()-1);
        }

        if(flag)
            hs.remove(curr);
        dfsVisited[curr]=false;
        return flag;
    }

    public List<Integer> eventualSafeNodes2(int[][] graph){
        result=new ArrayList<>();
        int[] state=new int[graph.length];

        for(int i=0;i< graph.length;i++){
            if(dfs2(graph,i,state))
                result.add(i);
        }
        return result;
    }

    private boolean dfs2(int[][] graph, int curr, int[] state) {
        if(state[curr]>0)
            return state[curr]==2;

        state[curr]=1;

        for(int child: graph[curr]){
            if(state[child]==2)
                continue;
            else if(state[child]==1 || !dfs2(graph,child,state))
                return false;
        }

        state[curr]=2;
        return true;
    }

    public static void main(String[] args) {
        int[][] arr={{1,2},{2,3},{5},{0},{5},{},{}};
        int[][] arr1={{1,2,3,4},{1,2},{3,4},{0,4},{}};
        int[][] arr2={{0},{2,3,4},{3,4},{0,4},{}};
        System.out.println(new FindEventualSafeStates().eventualSafeNodes2(arr));
        System.out.println(new FindEventualSafeStates().eventualSafeNodes2(arr1));
        System.out.println(new FindEventualSafeStates().eventualSafeNodes2(arr2));
    }
}
