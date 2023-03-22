package Graphs.Medium;

import java.util.*;

public class CourseScheduleIV {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        ArrayList<HashSet<Integer>> prerequisiteSet=new ArrayList<>();
        int[] indegree=new int[numCourses];

        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
            prerequisiteSet.add(new HashSet<>());
        }
        for(int[] edge: prerequisites){
            adj.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<numCourses;i++)
            if(indegree[i]==0)
                queue.offer(i);

        while (!queue.isEmpty()){
            int node= queue.poll();
            for(int neighbour: adj.get(node)){
                prerequisiteSet.get(neighbour).add(node);
                prerequisiteSet.get(neighbour).addAll(prerequisiteSet.get(node));
                --indegree[neighbour];
                if(indegree[neighbour]==0)
                    queue.offer(neighbour);
            }
        }

        List<Boolean> result=new ArrayList<>();
        for(int[] query: queries){
            if(prerequisiteSet.get(query[1]).contains(query[0]))
                result.add(true);
            else result.add(false);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] query1={{0,1},{1,0}};
        int[][] pre={{1,0}};
        int[][] pre2={{1,2},{1,0},{2,0}};
        int[][] query2={{1,0},{1,2}};
        int[][] pre3={{0,1},{1,2},{2,3}};
        int[][] query3={{0,1},{0,2}};
        int[][] pre4={{2,3},{2,1},{2,0},{3,4},{3,6},{5,1},{5,0},{1,4},{1,0},{4,0},{0,6}};
        int[][] query4={{5,6}};
        int[][] pre5={{0,1},{2,1},{1,3}};
        int[][] query5={{2,3}};
        System.out.println(new CourseScheduleIV().checkIfPrerequisite(2,pre,query1));
        System.out.println(new CourseScheduleIV().checkIfPrerequisite(2,new int[][]{},query1));
        System.out.println(new CourseScheduleIV().checkIfPrerequisite(3,pre2,query2));
        System.out.println(new CourseScheduleIV().checkIfPrerequisite(4,pre3,query3));
        System.out.println(new CourseScheduleIV().checkIfPrerequisite(7,pre4,query4));
        System.out.println(new CourseScheduleIV().checkIfPrerequisite(4,pre5,query5));
    }
}
