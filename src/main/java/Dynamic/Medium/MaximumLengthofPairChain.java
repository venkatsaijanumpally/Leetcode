package Dynamic.Medium;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumLengthofPairChain {
    Integer[][] memoize;
    public int findLongestChain(int[][] pairs) {
        memoize=new Integer[pairs.length][2000+2];
        /*Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return o1[1]-o2[1];
                return o1[0]-o2[0];
            }
        });*/
        Arrays.sort(pairs, (int[] a, int[] b) -> a[1] - b[1]);

        int count=0;
        //!Optimal
        int leftmax=Integer.MIN_VALUE;
        for(int i=0;i< pairs.length;i++){
            if(pairs[i][0]>leftmax){
                leftmax=pairs[i][1];
                count++;
            }
        }
        return count;
        //return recursive(pairs,0,-1001);
    }

    private int recursive(int[][] pairs, int index, int prev) {
        if(index==pairs.length) return 0;
        if(memoize[index][prev+1001]!=null) return memoize[index][prev+1001];

        if(pairs[index][0]>prev)
            return memoize[index][prev+1001]=Math.max(1+recursive(pairs,index+1,pairs[index][1]),
                    recursive(pairs,index+1,prev));
        else return memoize[index][prev+1001]=recursive(pairs,index+1,prev);
    }

    public static void main(String[] args) {
        int[][] pairs={{1,2},{7,8},{4,5},{1,3},{2,3},{3,4}};
        int[][] pairs1={{1,2},{2,3},{3,4}};
        int[][] pairs2={{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};
        System.out.println(new MaximumLengthofPairChain().findLongestChain(pairs));
        System.out.println(new MaximumLengthofPairChain().findLongestChain(pairs1));
        System.out.println(new MaximumLengthofPairChain().findLongestChain(pairs2));
    }
}
