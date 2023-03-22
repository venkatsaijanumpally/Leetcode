package Graphs.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class CouplesHoldingHands {

    /*
     * https://leetcode.com/problems/couples-holding-hands/solutions/2350137/c-union-find/
     * In this logic we do not perform entire swapping but we just use a workaround
     * e.g  0,4,1,2,3,5
     *
     * with the first loop we get the below unions
     * 0 <- 4  which means 0 is parent of 4
     * 1 <- 2
     * 3 <- 5
     *
     * so when we union in second loop
     * we perform union of 0 to 1 which gives
     *      0 <- 4
     *      0 <- 1
     *      0 <- 2
     *      3 <- 5
     *
     * next union 2 and 3 which gives
     *      0 <- 4
     *      0 <- 1
     *      0 <- 2
     *      0 <- 3
     *      0 <- 5
     * next union of 4,5 fails as 4 and 5 are under same parent '0' so total swaps = 2 but in reality to optimally swap
     * these three pairs we use 2 swaps and the third pair is set while performing the 2 swaps.
     */
    int[] rank;
    int[] parent;
    public int minSwapsCouples(int[] row) {
        rank=new int[row.length];
        parent=new int[row.length];
        Arrays.fill(rank,1);
        for(int i=0;i< row.length;i++)
            parent[i]=i;

        for(int i=0;i<row.length;i+=2){
            union(row[i],row[i+1]);
        }

        int ans=0;
        for(int i=0;i< row.length;i+=2){
            if(union(i,i+1))
                ans++;
        }
        return ans;
    }

    private boolean union(int a, int b) {
        int parentA=getParent(a);
        int parentB=getParent(b);

        if(parentA==parentB)
            return false;
        else {
            if(rank[parentA]>rank[parentB])
                parent[parentB]=parentA;
            else if(rank[parentB]>rank[parentA])
                parent[parentA]=parentB;
            else {
                rank[parentA]++;
                parent[parentB]=parentA;
            }
        }
        return true;
    }

    private int getParent(int a) {
        if(parent[a]==a)
            return a;
        return parent[a]=getParent(parent[a]);
    }

    public static void main(String[] args) {
        int[] row1={0,2,1,3};
        int[] row2={0,4,1,2,3,5};
        int[] row3={0,2,1,4,3,5};
        int[] row4={5,4,2,6,3,1,0,7};
        System.out.println(new CouplesHoldingHands().minSwapsCouples(row4));
        System.out.println(new CouplesHoldingHands().minSwapsCouples(row1));
        System.out.println(new CouplesHoldingHands().minSwapsCouples(row2));
        System.out.println(new CouplesHoldingHands().minSwapsCouples(row3));
    }
}
