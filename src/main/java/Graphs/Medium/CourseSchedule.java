package Graphs.Medium;

import java.util.ArrayList;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();

        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        /*for(int[] edge:prerequisites){
            adj.get(edge[0]).add(edge[1]);
        }*/

        for(int i=0;i< prerequisites.length;i++){
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        boolean[] visited=new boolean[numCourses];
        boolean[] dfsVisited=new boolean[numCourses];

        for(int i=0;i<numCourses;i++){
            if(!visited[i]){
                if(detectCycle(adj,visited,dfsVisited,i))
                    return false;
            }
        }
        return true;
    }

    private boolean detectCycle(ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] dfsVisited, int currnode) {
        visited[currnode]=true;
        dfsVisited[currnode]=true;

        for(int child: adj.get(currnode)){
            if(!visited[child]){
                if(detectCycle(adj,visited,dfsVisited,child))
                    return true;
            }
            else if(dfsVisited[child])
                return true;
        }
        dfsVisited[currnode]=false;
        return false;
    }

    public static void main(String[] args) {
        int[][] arr={{1,0}};
        int[][] arr1={{1,0},{0,1}};
        int[][] arr2={{1, 0}, {2, 1}, {3, 2}};
        System.out.println(new CourseSchedule().canFinish(2,arr));
        System.out.println(new CourseSchedule().canFinish(2,arr1));
        System.out.println(new CourseSchedule().canFinish(4,arr2));
    }
}
