package Dynamic.Medium;

import java.util.Arrays;

public class BestTimetoBuyandSellStockwithTransactionFee {
    int[][] memoize;
    public int maxProfit(int[] prices, int fee) {
        memoize=new int[prices.length+1][prices.length+1];
        for(int[] arr:memoize)
            Arrays.fill(arr,-1);

        /*int[] memoizeArr=new int[prices.length];
        for(int i=1;i< prices.length;i++){
            int maxValue=0;
            int prevMaxProfit=0;
            int max=0;
            for(int j=0;j<i;j++){
                if(prices[i]-prices[j]-fee>maxValue)
                    maxValue=prices[i]-prices[j]-fee;
                if(prevMaxProfit<memoizeArr[j])
                    prevMaxProfit=memoizeArr[j];
                if(max<maxValue+prevMaxProfit)
                    max=maxValue+prevMaxProfit;
            }
            memoizeArr[i]=max;
        }

        return memoizeArr[prices.length-1];*/
        return recursive(prices,fee,1,2);
    }

    private int recursive(int[] prices, int fee, int start, int curr) {
        if(curr>prices.length) return 0;
        if(memoize[start][curr]!=-1) return memoize[start][curr];

        if(prices[curr-1]>prices[start-1]){
            if(prices[curr-1]-prices[start-1]-fee>0)
                return memoize[start][curr]=Math.max(prices[curr-1]-prices[start-1]-fee+recursive(prices,fee,curr+1,curr+2),
                    recursive(prices,fee,start,curr+1));
            return memoize[start][curr]=recursive(prices,fee,start,curr+1);
        }
        else
            return memoize[start][curr]=recursive(prices,fee,curr,curr+1);
    }

    public int maxProfit2(int[] prices, int fee){
        int[] inclusive=new int[prices.length];
        int[] exclusive=new int[prices.length];
        inclusive[0]=0;
        exclusive[0]=0;
        /*for(int i=1;i< prices.length;i++){
            int maxInc=0;
            int maxEx=0;
            for(int j=0;j<i;j++){
                int val=prices[i]-prices[j]-fee;
                if(val>0){
                    maxInc=Math.max(maxInc,val+exclusive[j]);
                }
                maxEx=Math.max(maxEx,Math.max(exclusive[j],inclusive[j]));
            }
            exclusive[i]=maxEx;
            inclusive[i]=maxInc;
        }*/

        for(int i=1;i< prices.length;i++){
            int min=Integer.MAX_VALUE;
            int maxInc=0;
            for(int j=0;j<i;j++){
                if(min>prices[j]){
                    int val=prices[i]-prices[j]-fee;
                    if(val>0){
                        maxInc=Math.max(maxInc,val+exclusive[j]);
                    }
                }
            }
            exclusive[i]=Math.max(inclusive[i-1],exclusive[i-1]);
            inclusive[i]=maxInc;
        }
        return Math.max(inclusive[prices.length-1],exclusive[prices.length-1]);
    }

    public static void main(String[] args) {
        int[] prices1={1,3,2,8,4,9};
        int[] prices2={4,5,2,4,3,3,1,2,5,4};
        System.out.println(new BestTimetoBuyandSellStockwithTransactionFee().maxProfit(prices1,2));
        System.out.println(new BestTimetoBuyandSellStockwithTransactionFee().maxProfit(prices2,1));

        System.out.println(new BestTimetoBuyandSellStockwithTransactionFee().maxProfit2(prices1,2));
        System.out.println(new BestTimetoBuyandSellStockwithTransactionFee().maxProfit2(prices2,1));
    }
}
