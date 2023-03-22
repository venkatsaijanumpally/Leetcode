package Graphs.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CourseScheduleIIBFS {
    /*
     * https://www.youtube.com/watch?v=rZv_jHZva34&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=14
     * Read CourseScheduleIIDFS before this.
     * Approach: Kahn Algorithm
     * This algorithm is based on BFS approach and topological sort. The indegree[x] represents the number of nodes coming into the node 'x'.
     * So the approach works on a simple fact:
     * A DAG has at least one vertex with in-degree 0 and one vertex with out-degree 0.
     * Which means there are finite number of paths. In-degree 0 nodes are all start point and out-degree 0 are all
     * end points.
     *
     * Algorithm: Steps involved
     * Step-1: Compute in-degree (number of incoming edges) for each of the vertex present in the DAG and initialize the count of visited nodes as 0.
       Step-2: Pick all the vertices with in-degree as 0 and add them into a queue (Enqueue operation)
       Step-3: Remove a vertex from the queue (Dequeue operation) and then.


        1.Increment count of visited nodes by 1.
        2.Decrease in-degree by 1 for all its neighbouring nodes.
        3.If in-degree of a neighbouring nodes is reduced to zero, then add it to the queue.

      Step 4: Repeat Step 3 until the queue is empty.
      Step 5: If count of visited nodes is not equal to the number of nodes in the graph then the topological sort is not possible for the given graph.
      *
      *
      * If the graph contains a cycle then there occurs a situation where only the cycle is left and since
      * in a cycle none of the element has 0 in-degree the loop will end.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree=new int[numCourses];
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();

        for(int i=0;i<numCourses;i++)
            adj.add(new ArrayList<>());
        for(int[] edge: prerequisites){
            adj.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        int[] result=new int[numCourses];
        int index=numCourses-1;
        Queue<Integer> queue=new LinkedList<>();
        // Here we push all elements that have indegree 0 into the queue.
        for(int i=0;i<numCourses;i++){
            if(inDegree[i]==0)
                queue.offer(i);
        }

        while (!queue.isEmpty()){
            int node= queue.poll();
            result[index--]=node;
            for(int child: adj.get(node)){
                //decrement adjacent nodes in-degree
                --inDegree[child];
                if(inDegree[child]==0)
                    queue.offer(child);
            }
        }

        //If there is a cycle then result is not fully filled
        if(index!=-1) return new int[0];
        return result;
    }

    public static void main(String[] args) {
        int[][] arr={{1,0}};
        int[][] arr1={{1,0},{0,1}};
        int[][] arr2={{1, 0}, {2, 1}, {3, 2}};
        int[][] arr3={{0, 1}, {0, 2}, {1, 3}};
        int[][] arr4={{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(new CourseScheduleIIBFS().findOrder(2, arr)));
        System.out.println(Arrays.toString(new CourseScheduleIIBFS().findOrder(2, arr1)));
        System.out.println(Arrays.toString(new CourseScheduleIIBFS().findOrder(4, arr2)));
        System.out.println(Arrays.toString(new CourseScheduleIIBFS().findOrder(4, arr3)));
        System.out.println(Arrays.toString(new CourseScheduleIIBFS().findOrder(4, arr4)));
    }
}
