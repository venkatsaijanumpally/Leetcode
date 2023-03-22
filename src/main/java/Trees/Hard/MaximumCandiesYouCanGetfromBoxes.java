package Trees.Hard;

import java.sql.Array;
import java.util.*;

public class MaximumCandiesYouCanGetfromBoxes {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Queue<Integer> available=new LinkedList<>();
        for(int box: initialBoxes)
            available.offer(box);
        int TotalCandies=0;
        //HashSet<Integer> availableKeys=new HashSet<>();
        HashSet<Integer> unopenedBoxes=new HashSet<>();
        while (available.size()!=0){
            int box= available.poll();
            TotalCandies+=candies[box];
            for (int key: keys[box]){
                if(unopenedBoxes.contains(key))
                    available.add(key);
                else
                    status[key]=1;
            }
            for (int newBox:containedBoxes[box]){
                if(status[newBox]==1)
                    available.offer(newBox);
                else
                    unopenedBoxes.add(newBox);
            }
        }
        return TotalCandies;
    }

    //!Optimal
    public int maxCandies2(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Queue<Integer> available=new LinkedList<>();
        int TotalCandies=0;
        boolean[] opened=new boolean[status.length];
        boolean[] found=new boolean[status.length];
        for(int x: initialBoxes){
            found[x]=true;
            available.offer(x);
        }
        while (!available.isEmpty()){
            int box=available.poll();
            if(!opened[box] && status[box]==1){
                opened[box]=true;
                TotalCandies+=candies[box];
                for (int key:keys[box]){
                    status[key]=1;
                    if(found[key]) available.add(key);
                }
                for(int newBox: containedBoxes[box]){
                    found[newBox]=true;
                    if(status[newBox]==1) available.add(newBox);
                }
            }
        }
        return TotalCandies;
    }



    public static void main(String[] args) {
        int[] status1={1,0,1,0},candies1={7,5,4,100},initialBoxes1={0};
        int[][] keys1={{},{},{1},{}},containedBoxes1={{1,2},{3},{},{}};
        System.out.println(new MaximumCandiesYouCanGetfromBoxes().maxCandies(status1,candies1,keys1,containedBoxes1,initialBoxes1));
        System.out.println(new MaximumCandiesYouCanGetfromBoxes().maxCandies2(status1,candies1,keys1,containedBoxes1,initialBoxes1));
    }
}
