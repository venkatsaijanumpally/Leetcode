package Dynamic.Easy;

import java.util.HashMap;

public class MinCostClimbingStairs {

    public static int minCostClimbingStairs(int[] cost) {
        int[] min_cost=new int[cost.length+1];
        min_cost[0]=0;
        min_cost[1]=cost[0];
        min_cost[2]=cost[0];
        for(int i=3;i<min_cost.length;i++){
            min_cost[i]=Math.min(min_cost[i-1]+cost[i-1],Math.min(min_cost[i-2]+cost[i-2], min_cost[i-3]+cost[i-3]));
        }
        return min_cost[cost.length];
    }

    //!Optimal
    public static int minCostClimbingStairsTwoStep(int[] cost) {
        int[] min_cost=new int[cost.length+2];
        min_cost[0]=0;
        min_cost[1]=0;
        min_cost[2]=0;
        for(int i=3;i<min_cost.length;i++){
            min_cost[i]=Math.min(min_cost[i-1]+cost[i-2],min_cost[i-2]+cost[i-3]);
        }
        return min_cost[cost.length+1];
    }


    static Integer[] memoize;
    static HashMap<Integer,Integer> hm=new HashMap<>();
    public static int recursiveCaller(int[] cost){
        memoize=new Integer[cost.length];
        /*int firstIndex=recursivePlain(cost,0,0);
        int secondIndex=recursivePlain(cost,1,0);
        return firstIndex<secondIndex?firstIndex:secondIndex;*/

        memoize[0]=cost[0];
        memoize[1]=cost[1];
        int firstIndex=recursiveMemoize(cost,cost.length-1,0);
        return firstIndex<memoize[cost.length-2]?firstIndex:memoize[cost.length-2];
    }

    public static int recursivePlain(int[] cost, int index, int sum){
        if(index>cost.length-1) return sum;

        int oneStep=recursivePlain(cost,index+1,sum+cost[index]);
        int twoStep=recursivePlain(cost,index+2,sum+cost[index]);
        return oneStep<twoStep?oneStep:twoStep;
    }

    //!Memoized Solution
    public static int recursiveMemoize(int[] cost, int index, int sum){
        if(index==0 || index==1) return cost[index];
        if(memoize[index]!=null) return memoize[index];
        int oneStep=recursiveMemoize(cost,index-1,sum)+cost[index];
        int twoStep=recursiveMemoize(cost,index-2,sum)+cost[index];
        memoize[index]=oneStep<twoStep?oneStep:twoStep;
        return memoize[index];
    }

    public static void main(String args[]) {
        int[] arr={10,15,20};
        //int[] arr={1,100,1,1,1,100,1,1,100,1};
        System.out.println(minCostClimbingStairsTwoStep(arr));


        System.out.println();
        System.out.println(recursiveCaller(arr));
    }
}
