package Graphs.Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FindClosestNodetoGivenTwoNodes {
    /*
     * Approach: Compare BFS paths for both node1 & node2
     * Run BFS and store distance of each node from node2 in dist1
     * Run BFS and store distance of each node from node1 in dist2
     * Now compare both distance arrays and find common node index with minimum distance. While comparing we traverse
     * from node '0' to edges.length so that node with lesser index gets higher priority.
     */
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(node2);
        int[] dist1=new int[edges.length];
        int[] dist2=new int[edges.length];
        Arrays.fill(dist1,-1);
        Arrays.fill(dist2,-1);
        int level=0;
        while (!queue.isEmpty()){
            int node= queue.poll();
            dist1[node]=level++;
            if(edges[node]!=-1 && dist1[edges[node]]==-1)
                queue.add(edges[node]);
        }

        level=0;
        queue.offer(node1);
        while (!queue.isEmpty()){
            int node= queue.poll();
            dist2[node]=level++;
            if(edges[node]!=-1 && dist2[edges[node]]==-1)
                queue.add(edges[node]);
        }

        System.out.println(Arrays.toString(dist1));
        System.out.println(Arrays.toString(dist2));

        int minDist=Integer.MAX_VALUE;
        int minIndex=-1;

        for(int i=0;i< edges.length;i++){
            int temp=Math.max(dist1[i],dist2[i]);
            if(dist1[i]!=-1 && dist2[i]!=-1 && minDist>temp){
                minDist=temp;
                minIndex=i;
            }
        }


        return minIndex;
    }

    public static void main(String[] args) {
        int[] edges={2,2,3,-1};
        int[] edges2={1,2,-1};
        int[] edges3={2,0,0};
        System.out.println(new FindClosestNodetoGivenTwoNodes().closestMeetingNode(edges,0,1));
        System.out.println(new FindClosestNodetoGivenTwoNodes().closestMeetingNode(edges2,0,2));
        System.out.println(new FindClosestNodetoGivenTwoNodes().closestMeetingNode(edges3,2,0));
    }
}
