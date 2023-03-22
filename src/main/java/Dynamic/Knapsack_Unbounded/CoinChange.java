package Dynamic.Knapsack_Unbounded;

public class CoinChange {
    /*
     * https://www.youtube.com/watch?v=I-l6PBeERuc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=16
     * Solutions topDownFromVideo and topDownByMe both are optimal solutions.
     */
    Integer[][] memoize;
    int[][] memoizearr;
    public int coinChange(int[] coins, int amount) {
        memoize=new Integer[coins.length+1][amount+1];
        /*int a= recursiveMemoize(coins,amount,coins.length-1,0);
        if(a==2147483646) return -1;
        return a;*/

        /*int a=recursiveLoop(coins,amount,coins.length);
        if(a==Integer.MAX_VALUE) return -1;
        return a;*/

        memoizearr=new int[coins.length+1][amount+1];
        //topDownFromVideo(amount,coins);
        topDownByMe(amount,coins);

        if(memoizearr[coins.length][amount]==Integer.MAX_VALUE-1)
            return -1;
        return memoizearr[coins.length][amount];
    }

    //#Top Down Approach given in video
    //!Optimal Solution
    public void topDownFromVideo(int amount, int[] coins){
        for(int i=0;i<amount+1;i++)
            memoizearr[0][i]=Integer.MAX_VALUE-1;
        for(int i=1,j=1;j<amount+1;j++){
            if(j%coins[i-1]==0) memoizearr[i][j]=j/coins[i-1];
            else memoizearr[i][j]=Integer.MAX_VALUE-1;
        }
        for (int i=1;i<coins.length+1;i++){
            for(int j=1;j<amount+1;j++){
                if(coins[i-1]<=j)
                    memoizearr[i][j]=Math.min(1+memoizearr[i][j-coins[i-1]],memoizearr[i-1][j]);
                else memoizearr[i][j]=memoizearr[i-1][j];
            }
        }
    }

    //#Top Down Approach written by me
    //!Optimal Solution
    public void topDownByMe(int amount, int[] coins){
        for(int i=0;i<amount+1;i++)
            memoizearr[0][i]=Integer.MAX_VALUE-1;
        for (int i=1;i<coins.length+1;i++){
            for(int j=1;j<amount+1;j++){
                if(coins[i-1]<=j)
                    memoizearr[i][j]=Math.min(1+memoizearr[i][j-coins[i-1]],memoizearr[i-1][j]);
                else memoizearr[i][j]=memoizearr[i-1][j];
            }
        }
    }

    public int recursiveMemoize(int[] coins, int amount, int index, int count){
        if(index==-1){
            if(amount==0) return 0;
            return 2147483646;
        }
        //if(amount==0) return count;
        if(memoize[index][amount]!=null) return memoize[index][amount];

        if(coins[index]<=amount)
            return memoize[index][amount]=Math.min(1+recursiveMemoize(coins,amount-coins[index],index,count),
                    recursiveMemoize(coins,amount,index-1,count));
        else return memoize[index][amount]=recursiveMemoize(coins,amount,index-1,count);
    }

    public int recursiveLoop(int[] coins, int amount, int size){
        if(amount==0) return 0;

        int res=Integer.MAX_VALUE;
        for(int i=0;i<size;i++){
            if(coins[i]<=amount){
                int subres=recursiveLoop(coins,amount-coins[i],size);

                if(subres!=Integer.MAX_VALUE && subres+1<res)
                    res=subres+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr={1,2,5};
        int[] aa1={2,5,10,1};
        int[] arr2={2};
        System.out.println(new CoinChange().coinChange(aa1,27));
        System.out.println(new CoinChange().coinChange(arr,11));
        System.out.println(new CoinChange().coinChange(arr2,3));
    }
}
