package Graphs.Medium;

import Graphs.Utils.CoordinateDistance;
import Graphs.Utils.CoordinateXY;

import java.util.*;

public class MinimumCostofaPathWithSpecialRoads {
    HashMap<CoordinateXY, HashMap<CoordinateXY, Integer>> adj = new HashMap<>();
    int[][] distance;
    int[] start;
    int[] target;
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int rows = target[0] - start[0] + 1;
        int cols = target[1] - start[1] + 1;
        distance = new int[rows][cols];
        this.start = start;
        this.target = start;
        PriorityQueue<CoordinateDistance> queue = new PriorityQueue<>();
        queue.add(new CoordinateDistance(start[0], start[1], 0));
        //queue.add(new CoordinateDistance(target[0], target[1], Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1])));

        adj.put(new CoordinateXY(start[0],start[1]),new HashMap<>());
        adj.put(new CoordinateXY(target[0],target[1]),new HashMap<>());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[0][0]=0;

        for (int[] road: specialRoads){
            adj.putIfAbsent(new CoordinateXY(road[0],road[1]), new HashMap<>());
            adj.putIfAbsent(new CoordinateXY(road[2],road[3]), new HashMap<>());
        }

        for (int[] road: specialRoads){
            if(Math.abs(road[0]-road[2])+Math.abs(road[1]-road[3]) <= road[4]) continue;
            adj.get(new CoordinateXY(road[0],road[1])).put(new CoordinateXY(road[2],road[3]),road[4]);
        }

        for (Map.Entry<CoordinateXY, HashMap<CoordinateXY,Integer>> coordinate : adj.entrySet()){
            for (CoordinateXY neighbour: adj.keySet()){
                if(coordinate.getKey() == neighbour || coordinate.getValue().containsKey(neighbour)) continue;

                coordinate.getValue().put(neighbour, Math.abs(coordinate.getKey().x - neighbour.x) + Math.abs(coordinate.getKey().y - neighbour.y));
            }
        }

        while (!queue.isEmpty()){
            CoordinateDistance node = queue.poll();
            if(node.x == target[0] && node.y == target[1]) return node.dist;
            if(getDistance(node.x,node.y)!=node.dist) continue;

            for (Map.Entry<CoordinateXY,Integer> neighbour : adj.get(new CoordinateXY(node.x, node.y)).entrySet()){
                int calcDistance = node.dist + neighbour.getValue();
                if(getDistance(neighbour.getKey().x, neighbour.getKey().y) > calcDistance){
                    setDistance(neighbour.getKey().x, neighbour.getKey().y,calcDistance);
                    queue.add(new CoordinateDistance(neighbour.getKey().x, neighbour.getKey().y, calcDistance));
                }
            }
        }

        return distance[target[0]-start[0]][target[1]-start[1]];
    }

    HashMap<CoordinateXY,Integer> distanceMap;
    public int minimumCost3(int[] start, int[] target, int[][] specialRoads) {
        distanceMap = new HashMap<>();
        PriorityQueue<CoordinateDistance> queue = new PriorityQueue<>();
        queue.add(new CoordinateDistance(start[0], start[1], 0));
        //queue.add(new CoordinateDistance(target[0], target[1], Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1])));

        adj.put(new CoordinateXY(start[0],start[1]),new HashMap<>());
        adj.put(new CoordinateXY(target[0],target[1]),new HashMap<>());

        distanceMap.put(new CoordinateXY(start[0],start[1]),0);
        //distanceMap.put(new CoordinateXY(target[0],target[1]),Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1]));

        for (int[] road: specialRoads){
            adj.putIfAbsent(new CoordinateXY(road[0],road[1]), new HashMap<>());
            adj.putIfAbsent(new CoordinateXY(road[2],road[3]), new HashMap<>());
        }

        for (int[] road: specialRoads){
            if(Math.abs(road[0]-road[2])+Math.abs(road[1]-road[3]) <= road[4]) continue;
            CoordinateXY left = new CoordinateXY(road[0],road[1]);
            CoordinateXY right = new CoordinateXY(road[2],road[3]);
            int max = road[4];
            if(adj.get(left).containsKey(right)){
                max = adj.get(left).get(right);
                max = Math.min(max, road[4]);
            }
            adj.get(left).put(right,max);
            //adj.get(new CoordinateXY(road[0],road[1])).put(new CoordinateXY(road[2],road[3]),road[4]);
        }

        for (Map.Entry<CoordinateXY, HashMap<CoordinateXY,Integer>> coordinate : adj.entrySet()){
            for (CoordinateXY neighbour: adj.keySet()){
                if(coordinate.getKey() == neighbour || coordinate.getValue().containsKey(neighbour)) continue;

                coordinate.getValue().put(neighbour, Math.abs(coordinate.getKey().x - neighbour.x) + Math.abs(coordinate.getKey().y - neighbour.y));
            }
        }

        while (!queue.isEmpty()){
            CoordinateDistance node = queue.poll();
            if(node.x == target[0] && node.y == target[1]) return node.dist;
            if(getDistanceMap(node.x,node.y)!=node.dist) continue;

            for (Map.Entry<CoordinateXY,Integer> neighbour : adj.get(new CoordinateXY(node.x, node.y)).entrySet()){
                int calcDistance = node.dist + neighbour.getValue();
                if(getDistanceMap(neighbour.getKey().x, neighbour.getKey().y) > calcDistance){
                    setDistanceMap(neighbour.getKey().x, neighbour.getKey().y,calcDistance);
                    queue.add(new CoordinateDistance(neighbour.getKey().x, neighbour.getKey().y, calcDistance));
                }
            }
        }

        return distanceMap.get(new CoordinateXY(target[0]-start[0], target[1]-start[1]));
    }

    private int getDistanceMap(int x, int y) {
        CoordinateXY coordinate = new CoordinateXY(x,y);
        distanceMap.putIfAbsent(coordinate,Integer.MAX_VALUE);
        return distanceMap.get(coordinate);
    }

    private void setDistanceMap(int x, int y, int dist) {
        distanceMap.put(new CoordinateXY(x,y),dist);
    }
    public int minimumCost2(int[] start, int[] target, int[][] specialRoads) {
        int rows = target[0] - start[0] + 1;
        int cols = target[1] - start[1] + 1;
        distance = new int[rows][cols];
        this.start = start;
        this.target = start;
        PriorityQueue<CoordinateDistance> queue = new PriorityQueue<>();
        queue.add(new CoordinateDistance(start[0], start[1], 0));
        queue.add(new CoordinateDistance(target[0], target[1], Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1])));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        distance[0][0]=0;
        setDistance(target[0],target[1],Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1]));

        while (!queue.isEmpty()){
            CoordinateDistance node = queue.poll();
            if(node.x == target[0] && node.y == target[1]) return node.dist;
            if(getDistance(node.x,node.y)!=node.dist) continue;

            for(int i=0;i<specialRoads.length;i++){

                int x=specialRoads[i][0];
                int y=specialRoads[i][1];
                int x2=specialRoads[i][2];
                int y2=specialRoads[i][3];
                int ct=specialRoads[i][4];

                int distancenew = node.dist + Math.abs(node.x-x)+Math.abs(node.y-y) + ct;
                if(distancenew < getDistance(x2,y2)){
                    setDistance(x2,y2, distancenew);
                    queue.add(new CoordinateDistance(x2,y2,distancenew));
                }
            }
        }

        return distance[target[0]-start[0]][target[1]-start[1]];
    }

    private int getDistance(int x, int y) {
        return distance[x - start[0]][y - start[1]];
    }

    private void setDistance(int x, int y, int dist) {
        distance[x - start[0]][y - start[1]] = dist;
    }
    public static void main(String[] args) {
        int[][] specialRoads1 = {{1,2,3,3,2},{3,4,4,5,1}};
        int[][] specialRoads2 = {{1,6,1,2,4},{1,5,1,6,3},{1,3,1,6,1},{1,3,1,2,2},{1,5,1,1,2}};
        int[][] specialRoads3 = {{3,1,5,1,6},{2,1,6,1,2},{2,1,6,1,3},{6,1,6,1,2},{5,1,6,1,7},{4,1,6,1,6},{4,1,4,1,6},{2,1,2,1,1}};
        //System.out.println(new MinimumCostofaPathWithSpecialRoads().minimumCost(new int[]{1,1},new int[]{4,5},specialRoads1));
        //System.out.println(new MinimumCostofaPathWithSpecialRoads().minimumCost3(new int[]{1,1},new int[]{4,5},specialRoads1));
        //System.out.println(new MinimumCostofaPathWithSpecialRoads().minimumCost3(new int[]{1,1},new int[]{1,6},specialRoads2));
        System.out.println(new MinimumCostofaPathWithSpecialRoads().minimumCost3(new int[]{2,1},new int[]{6,1},specialRoads3));
    }
}
