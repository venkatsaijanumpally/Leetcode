package Graphs.Hard;

import java.util.*;

public class SortItemsbyGroupsRespectingDependencies {
    HashMap<Integer,Integer> groupMap;
    List<HashSet<Integer>> groupAdj;
    List<List<Integer>> groupInterAdj;
    List<List<Integer>> groupItems;

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems){
        for (int i=0;i<group.length;i++){
            if(group[i]==-1)
                group[i]=m++;
        }
        groupMap=new HashMap<>();
        groupAdj=new ArrayList<>();
        groupInterAdj=new ArrayList<>();
        groupItems=new ArrayList<>();
        for (int i=0;i<m;i++){
            groupAdj.add(new HashSet<>());
            groupItems.add(new ArrayList<>());
        }
        for (int i=0;i<group.length;i++){
            groupMap.put(i,group[i]);
            groupItems.get(group[i]).add(i);
            groupInterAdj.add(new ArrayList<>());
        }
        for (int i=0;i<n;i++){
            int groupA=groupMap.get(i);
            for (int j:beforeItems.get(i)){
                int groupB=groupMap.get(j);
                if(groupA==groupB){
                    groupInterAdj.get(i).add(j);
                }
                else {
                    groupAdj.get(groupA).add(groupB);
                }
            }
        }

        Stack<Integer> order=new Stack<>();
        boolean[] visited=new boolean[m];
        boolean[] dfsVisited=new boolean[m];
        for(int i=0;i<m;i++){
            if(!visited[i])
                if(!topologicalSort(order,visited,dfsVisited,i,groupAdj))
                    return new int[]{};
        }

        int[] result=new int[n];
        int index=n-1;
        while (!order.isEmpty()){
            int currGroup=order.pop();
            Stack<Integer> groupStack=new Stack<>();
            HashSet<Integer> groupVisited=new HashSet<>();
            HashSet<Integer> groupDfsVisited=new HashSet<>();
            for (int i:groupItems.get(currGroup)){
                if(groupVisited.contains(i)) continue;
                else if(!topologicalSort(groupStack,groupVisited,groupDfsVisited,i,groupInterAdj))
                    return new int[]{};
            }
            while (!groupStack.isEmpty())
                result[index--]=groupStack.pop();
        }

        return result;
    }

    /*public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems){
        groupMap=new HashMap<>();
        groupAdj=new ArrayList<>();
        groupInterAdj=new ArrayList<>();
        groupItems=new ArrayList<>();
        for (int i=0;i<=m;i++){
            groupAdj.add(new HashSet<>());
            groupItems.add(new ArrayList<>());
        }
        for (int i=0;i<group.length;i++){
            groupMap.put(i,group[i]+1);
            groupItems.get(group[i]+1).add(i);
            groupInterAdj.add(new ArrayList<>());
        }
        for (int i=0;i<n;i++){
            int groupA=groupMap.get(i);
            for (int j:beforeItems.get(i)){
                int groupB=groupMap.get(j);
                if(groupA==groupB){
                    groupInterAdj.get(i).add(j);
                }
                else {
                    groupAdj.get(groupA).add(groupB);
                }
            }
        }

        Stack<Integer> order=new Stack<>();
        boolean[] visited=new boolean[m+1];
        boolean[] dfsVisited=new boolean[m+1];
        for(int i=0;i<=m;i++){
            if(!visited[i])
                if(!topologicalSort(order,visited,dfsVisited,i,groupAdj))
                    return new int[]{};
        }

        int[] result=new int[n];
        int index=n-1;
        while (!order.isEmpty()){
            int currGroup=order.pop();
            Stack<Integer> groupStack=new Stack<>();
            HashSet<Integer> groupVisited=new HashSet<>();
            HashSet<Integer> groupDfsVisited=new HashSet<>();
            for (int i:groupItems.get(currGroup)){
                if(groupVisited.contains(i)) continue;
                else if(!topologicalSort(groupStack,groupVisited,groupDfsVisited,i,groupInterAdj))
                    return new int[]{};
            }
            while (!groupStack.isEmpty())
                result[index--]=groupStack.pop();
        }

        return result;
    }*/

    private boolean topologicalSort(Stack<Integer> order, boolean[] visited, boolean[] dfsVisited, int i, List<HashSet<Integer>> groupAdj) {
        visited[i] = true;
        dfsVisited[i] = true;

        for (int neighbour:groupAdj.get(i)){
            if(!visited[neighbour]){
                if(!topologicalSort(order,visited,dfsVisited,neighbour,groupAdj))
                    return false;
            }
            else if (dfsVisited[neighbour]) {
                return false;
            }
        }

        dfsVisited[i]=false;
        order.push(i);
        return true;
    }

    private boolean topologicalSort(Stack<Integer> order, HashSet<Integer> visited, HashSet<Integer> dfsVisited, int i, List<List<Integer>> groupInterAdj) {
        visited.add(i);
        dfsVisited.add(i);

        for (int neighbour:groupInterAdj.get(i)){
            if(!visited.contains(neighbour)){
                if(!topologicalSort(order,visited,dfsVisited,neighbour,groupInterAdj))
                    return false;
            }
            else if (dfsVisited.contains(neighbour)) {
                return false;
            }
        }

        dfsVisited.remove(i);
        order.push(i);
        return true;
    }

    public static void main(String[] args) {
        int[][] before1={{},{6},{5},{6},{3},{},{4},{}};
        int[] group1={-1,-1,1,0,0,1,0,-1};
        System.out.println(Arrays.toString(new SortItemsbyGroupsRespectingDependencies().sortItems(8, 2, group1, createList(before1))));
    }

    public static List<List<Integer>> createList(int[][] before1){
        List<List<Integer>> after = new ArrayList<>();
        for (int[] array : before1) {
            List<Integer> sublist = new ArrayList<>();
            for (int value : array) {
                sublist.add(value);
            }
            after.add(sublist);
        }
        return after;
    }
}
