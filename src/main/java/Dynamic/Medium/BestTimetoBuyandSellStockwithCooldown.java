package Dynamic.Medium;

public class BestTimetoBuyandSellStockwithCooldown {
    Integer[][] memoize;
    public int maxProfit(int[] prices) {
        memoize=new Integer[prices.length+1][1000+2];
        return recursive(prices,1,null);
    }

    private int recursive(int[] prices, int index, Integer prev) {
        if(index>prices.length) return 0;
        if(memoize[index][prev==null?1001:prev]!=null) return memoize[index][prev==null?1001:prev];

        if(prev==null)
            return memoize[index][1001]=recursive(prices,index+1,prices[index-1]);
        else if(prices[index-1]>prev)
            return memoize[index][prev]=Math.max(prices[index-1]-prev+recursive(prices,index+2,null),
                    recursive(prices,index+1,prev));
        else return memoize[index][prev]=recursive(prices,index+1,prices[index-1]);
    }

    public static void main(String[] args) {
        int[] arr={1,2,3,0,2};
        int[] arr2={1};
        System.out.println(new BestTimetoBuyandSellStockwithCooldown().maxProfit(arr));
        System.out.println(new BestTimetoBuyandSellStockwithCooldown().maxProfit(arr2));
    }
}
