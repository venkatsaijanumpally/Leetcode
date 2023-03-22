package Dynamic.Medium;

public class BestTimetoBuyandSellStockII {
    public int maxProfit(int[] prices) {
        //return recursive(prices,0,null);

        //Solution 1 and 2 both are good solutions.
        //Solution 1
        int val=prices[0];
        int sum=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]<val)
                val=prices[i];
            else{
                sum+=prices[i]-val;
                val=prices[i];
            }
        }
        return sum;

        //Solution 2
        /*int sum=0;
        for(int i=1;i< prices.length;i++)
            if(prices[i]>prices[i-1])
                sum+=prices[i]-prices[i-1];
        return sum;*/
    }

    private int recursive(int[] prices, int index, Integer prevPurchase){
        if(index> prices.length-1) return 0;

        if(prevPurchase==null){
            int buy=recursive(prices,index+1,prices[index]);
            int skip=recursive(prices,index+1,null);
            return Math.max(buy,skip);
        }
        else {
            //int sellandBuy=recursive2(prices,index+1,prices[index])+prices[index]-prevPurchase;
            int sellandSkip=recursive(prices,index+1,null)+prices[index]-prevPurchase;
            int skip=recursive(prices, index+1, prevPurchase);
            return Math.max(sellandSkip,skip);
        }
    }

    public static void main(String[] args) {
        int[] arr={7,1,5,3,6,4};
        int[] arr1={7,6,4,3,1};

        System.out.println();
        System.out.println(new BestTimetoBuyandSellStockII().maxProfit(arr));
    }
}
