package Graphs.Medium;

import java.util.*;

public class MaximumTotalImportanceofRoads {
    List<List<Integer>> adj = new ArrayList<>();

    public long maximumImportance(int n, int[][] roads) {
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for (int[] road : roads) {
            adj.get(road[0]).add(road[1]);
            adj.get(road[1]).add(road[0]);
        }

        int[] mapping = new int[n];
        PriorityQueue<Integer> sort = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return adj.get(o2).size() - adj.get(o1).size();
            }
        });
        for (int i = 0; i < n; i++)
            sort.add(i);

        long sum = 0;
        int index = n;
        while (!sort.isEmpty()) {
            int node = sort.poll();
            mapping[node] = index--;
            sum += (long) mapping[node] * adj.get(node).size();
        }

        return sum;
    }

    public long maximumImportance2(int n, int[][] roads) {
        int[] degree = new int[n];
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
        }

        int[] mapping = new int[n];
        PriorityQueue<Integer> sort = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return degree[o2] - degree[o1];
            }
        });
        for (int i = 0; i < n; i++)
            sort.add(i);

        long sum = 0;
        int index = n;
        while (!sort.isEmpty()) {
            int node = sort.poll();
            mapping[node] = index;
            sum += (long) index * degree[node];
            index--;
        }

        return sum;
    }

    public long maximumImportance3(int n, int[][] roads) {
        int[] degree = new int[n];
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
        }

        Arrays.sort(degree);
        long sum = 0;
        int index = 1;
        for (int i = 0; i < n; i++) {
            sum += (long) index * degree[i];
            index++;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] roads1 = {{0, 1}, {1, 2}, {2, 3}, {0, 2}, {1, 3}, {2, 4}};
        System.out.println(new MaximumTotalImportanceofRoads().maximumImportance3(5, roads1));
    }
}
