package Heap.Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class StoneGameVI {
    /*public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        HashSet<Integer> removedValues = new HashSet<>();
        PriorityQueue<Integer> bobQueue = new PriorityQueue<>((o1, o2) -> bobValues[o2]-bobValues[o1]);
        PriorityQueue<Integer> aliceQueue = new PriorityQueue<>((o1, o2) -> (aliceValues[o2]+bobValues[o2])-(aliceValues[o1]+bobValues[o1]));

        int remaining = aliceValues.length;
        int aliceScore = 0;
        int bobScore = 0;

        for (int i=0;i<aliceValues.length;i++){
            bobQueue.add(i);
            aliceQueue.add(i);
        }

        boolean turn = true;
        while (remaining>0){
            PriorityQueue<Integer> queue = turn?aliceQueue:bobQueue;
            int valIndex = queue.poll();
            while (removedValues.contains(valIndex)){
                valIndex = queue.poll();
            }

            if(turn)
                aliceScore+=aliceValues[valIndex];
            else
                bobScore+=bobValues[valIndex];

            removedValues.add(valIndex);
            remaining--;
            turn=!turn;
        }
        if(aliceScore==bobScore) return 0;
        return aliceScore>bobScore?1:-1;
    }*/

    /**
     * Both solutions stoneGameVI and stoneGameVIUsingArrays are optimal
     *
     */
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> (aliceValues[o2]+bobValues[o2])-(aliceValues[o1]+bobValues[o1]));

        int aliceScore = 0;
        int bobScore = 0;

        for (int i=0;i<aliceValues.length;i++){
            queue.add(i);
        }

        boolean turn = true;
        while (!queue.isEmpty()){
            int valIndex = queue.poll();

            if(turn)
                aliceScore+=aliceValues[valIndex];
            else
                bobScore+=bobValues[valIndex];

            turn=!turn;
        }
        if(aliceScore==bobScore) return 0;
        return aliceScore>bobScore?1:-1;
    }

    public int stoneGameVIUsingArrays(int[] aliceValues, int[] bobValues) {
        int[][] sums = new int[aliceValues.length][3];
        int aliceScore = 0;
        int bobScore = 0;

        for (int i = 0; i < aliceValues.length; i++) {
            sums[i] = new int[] {aliceValues[i] + bobValues[i], aliceValues[i], bobValues[i]};
        }
        Arrays.sort(sums,(a, b) -> Integer.compare(b[0], a[0]));

        for (int i=0;i<aliceValues.length;i++){
            if(i%2==0)
                aliceScore+=sums[i][1];
            else
                bobScore+=sums[i][2];
        }
        if(aliceScore==bobScore) return 0;
        return aliceScore>bobScore?1:-1;
    }

    public static void main(String[] args) {
        int[] alice1={1,3};
        int[] alice2={1,2};
        int[] bob1={2,5};
        int[] bob2={3,1};
        //System.out.println(new StoneGameVI().stoneGameVI(alice1,bob1));
        System.out.println(new StoneGameVI().stoneGameVI(alice2,bob2));
    }
}
