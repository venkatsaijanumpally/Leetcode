package Graphs.Medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumStarSumofaGraph {
    List<PriorityQueue<Integer>> maximumSum = new ArrayList<>();
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        for (int i=0; i< vals.length; i++){
            maximumSum.add(new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            }));
            //maximumSum.get(i).add(vals[i]);
        }

        for (int[] edge: edges){
            add(edge[0], vals[edge[1]],k);
            add(edge[1], vals[edge[0]],k);
            //maximumSum.get(edge[0]).add(vals[edge[1]]);
            //maximumSum.get(edge[1]).add(vals[edge[0]]);
        }

        return getMaximumSum(vals);
    }

    private int getMaximumSum(int[] vals) {
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < vals.length; i++) {
            int sum = vals[i]; // Include the value of the current node

            for (int priority : maximumSum.get(i)) {
                sum += priority;
            }

            // Update max if the current sum is greater
            max = Math.max(max, sum);
        }
        return max;
    }

    private void add(int node, int val, int k) {
        if(val<1) return;
        PriorityQueue<Integer> queue = maximumSum.get(node);
        queue.add(val);
        if(queue.size()>k)
            queue.poll();
    }

    public static void main(String[] args) {
        int[][] edges1 = {{0,1},{1,2},{1,3},{3,4},{3,5},{3,6}};
        int[] vals1= {1,2,3,4,10,-10,-20};
        System.out.println(new MaximumStarSumofaGraph().maxStarSum(new int[]{-5},new int[][]{},0));
        //System.out.println(new MaximumStarSumofaGraph().maxStarSum(vals1,edges1,2));
    }
}
