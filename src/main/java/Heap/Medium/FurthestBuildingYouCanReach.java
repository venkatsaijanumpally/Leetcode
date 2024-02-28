package Heap.Medium;

import Heap.Utils.Building;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FurthestBuildingYouCanReach {
    /*public int furthestBuilding(int[] heights, int bricks, int ladders) {
        if(heights.length<=ladders) return heights.length-1;
        PriorityQueue<Building> queue = new PriorityQueue<>();

        queue.add(new Building(0,bricks,ladders));
        int max=0;

        while (!queue.isEmpty()){
            PriorityQueue<Building> nextQueue = new PriorityQueue<>();
            while (!queue.isEmpty()){
                Building building = queue.poll();
                int nextBuilding = building.building+1;
                int diff = heights[nextBuilding] - heights[building.building];
                if(diff>0){
                    if(building.bricks>=diff){
                        nextQueue.add(new Building(nextBuilding,building.bricks-diff,building.ladders));
                        max = nextBuilding;
                    }
                    if(building.ladders>0){
                        nextQueue.add(new Building(nextBuilding,building.bricks,building.ladders-1));
                        max = nextBuilding;
                    }
                }
                else {
                    nextQueue.add(new Building(nextBuilding,building.bricks,building.ladders));
                    max = nextBuilding;
                }
                if(max == heights.length-1) return max;
            }
            queue = nextQueue;
        }
        return max;
    }*/

    /*public int furthestBuilding(int[] heights, int bricks, int ladders) {
        if(heights.length<=ladders) return heights.length-1;
        PriorityQueue<Building> queue = new PriorityQueue<>();

        queue.add(new Building(0,bricks,ladders));
        int max=0;

        while (!queue.isEmpty()){
            Building building = queue.poll();
            int nextBuilding = building.building+1;
            int diff = heights[nextBuilding] - heights[building.building];
            if(diff>0){
                if(building.bricks>=diff){
                    queue.add(new Building(nextBuilding,building.bricks-diff,building.ladders));
                    max = Math.max(nextBuilding,max);
                }
                if(building.ladders>0){
                    queue.add(new Building(nextBuilding,building.bricks,building.ladders-1));
                    max = Math.max(nextBuilding,max);
                }
            }
            else {
                queue.add(new Building(nextBuilding,building.bricks,building.ladders));
                max = Math.max(nextBuilding,max);
            }
            if(max == heights.length-1) return max;
        }
        return max;
    }*/

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        if(heights.length<=ladders) return heights.length-1;

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        int max= 0;
        for (int i=1;i<heights.length;i++){
            int diff = heights[i] - heights[i-1];
            if(diff>0){
                queue.add(diff);
                bricks-=diff;
            }
            if(bricks<0){
                if(ladders==0){
                    max = i-1;
                    break;
                }
                else {
                    int removed = queue.poll();
                    bricks+=removed;
                    ladders--;
                }
            }
            max = i;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] heights = {4,2,7,6,9,14,12};
        System.out.println(new FurthestBuildingYouCanReach().furthestBuilding(heights,5,1));
    }
}
