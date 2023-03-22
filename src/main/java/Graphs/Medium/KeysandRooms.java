package Graphs.Medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KeysandRooms {
    //BFS approach
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited=new boolean[rooms.size()];
        Queue<Integer> queue=new LinkedList<>();
        visited[0]=true;
        queue.offer(0);

        while (!queue.isEmpty()){
            int node= queue.poll();
            for(int neighbour: rooms.get(node)){
                if(!visited[neighbour]){
                    visited[neighbour]=true;
                    queue.offer(neighbour);
                }
            }
        }
        for(int i=1;i< rooms.size();i++){
            if(!visited[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] arr={{1},{2},{3},{}};
    }
}
