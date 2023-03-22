package Dynamic.Knapsack01;

public class CoinChange2 {
    Integer[][] memoize;
    public int change(int amount, int[] coins) {
        memoize=new Integer[coins.length+1][amount+1];
        return change(amount,coins,coins.length-1);
    }

    private int change(int amount, int[] coins, int index) {
        if(amount==0) return 1;
        if(index==-1) return 0;

        if(memoize[index][amount]!=null) return memoize[index][amount];
        if(coins[index]<=amount)
            return memoize[index][amount]=change(amount-coins[index],coins,index)+change(amount,coins,index-1);
        return memoize[index][amount]=change(amount,coins,index-1);
    }

    public static void main(String[] args) {
        int[] arr={1,2,5};
        System.out.println(new CoinChange2().change(5,arr));
        System.out.println(new CoinChange2().change(3,new int[]{2}));
    }
}
