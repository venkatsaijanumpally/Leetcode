package Dynamic.Hard;

import java.util.*;

public class FrogJump {
    public boolean canCross(int[] stones) {
        HashMap<Integer,HashSet<Integer>> jump = new HashMap<>();

        int target = stones[stones.length-1];
        HashSet<Integer> availableStones = new HashSet<>();
        for (int i=0;i< stones.length;i++){
            jump.put(stones[i], new HashSet<>());
            availableStones.add(stones[i]);
        }
        jump.get(0).add(1);

        for (int i=0;i< stones.length;i++){
            int stone = stones[i];
            int nextStone;
            for (Integer jumpLength : jump.get(stone)){
                nextStone = stone+jumpLength;
                if(nextStone == target) return true;
                if(availableStones.contains(nextStone)){
                    jump.get(nextStone).add(jumpLength);
                    jump.get(nextStone).add(jumpLength+1);
                    if(jumpLength-1>0) jump.get(nextStone).add(jumpLength-1);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] stones1 ={0,1,3,5,6,8,12,17};
        int[] stones2 ={0,1,2,3,4,8,9,11};
        System.out.println(new FrogJump().canCross(stones1));
        System.out.println(new FrogJump().canCross(stones2));
    }
}
