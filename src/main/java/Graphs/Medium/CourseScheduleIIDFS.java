package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CourseScheduleIIDFS {
    /*
     * https://www.youtube.com/watch?v=Yh6EFazXipA&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=13
     * Approach: Topological sort
     * Topological sort:
     * Topological sort works for DAG only.
     * in topological sort for an edge 1-->2 the topological sort is 1,2 . Parent comes on the left and child comes
     * on the right and if there are multiple children then child order does not matter. Consider below example
     * For directed edges (1,2),(1,3),(2,4) the topological sort is 1,2,4,3
     *
     * Note: In topological sorting there can be many solutions.
     *
     * For our question we require reverse of topological sort.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();

        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge:prerequisites){
            adj.get(edge[0]).add(edge[1]);
        }


        boolean[] visited=new boolean[numCourses];
        boolean[] dfsVisited=new boolean[numCourses];
        Stack<Integer> stack=new Stack<>();

        boolean flag=true;
        for(int i=0;i<numCourses;i++){
            if(!visited[i]){
                if(detectCycle(adj,visited,dfsVisited,i,stack)){
                    //System.out.println(stack);
                    flag=false;
                    break;
                }
            }
        }

        if(!flag)
            return new int[0];
        int[] result=new int[numCourses];
        int index=0;
        //result is reverse of topological sort
        for(int i: stack)
            result[index++]=i;
        return result;
    }

    private boolean detectCycle(ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] dfsVisited, int currnode, Stack<Integer> stack) {
        visited[currnode]=true;
        dfsVisited[currnode]=true;

        for(int child: adj.get(currnode)){
            if(!visited[child]){
                if(detectCycle(adj,visited,dfsVisited,child,stack))
                    return true;
            }
            else if(dfsVisited[child])
                return true;
        }
        stack.push(currnode);
        dfsVisited[currnode]=false;
        return false;
    }

    public static void main(String[] args) {
        int[][] arr={{1,0}};
        int[][] arr1={{1,0},{0,1}};
        int[][] arr2={{1, 0}, {2, 1}, {3, 2}};
        int[][] arr3={{0, 1}, {0, 2}, {1, 3}};
        int[][] arr4={{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(new CourseScheduleIIDFS().findOrder(2, arr)));
        System.out.println(Arrays.toString(new CourseScheduleIIDFS().findOrder(2, arr1)));
        System.out.println(Arrays.toString(new CourseScheduleIIDFS().findOrder(4, arr2)));
        System.out.println(Arrays.toString(new CourseScheduleIIDFS().findOrder(4, arr3)));
        System.out.println(Arrays.toString(new CourseScheduleIIDFS().findOrder(4, arr4)));
    }
}
