package Dynamic.Easy;

public class BestTimetoBuyandSellStock {
    public static void main(String args[]) {
        int[] arr={7,1,5,3,6,4};
        System.out.println(maxProfit(arr));
    }
    public static int maxProfit(int[] prices) {
        int[] r=new int[prices.length];
        int[] r1=new int[prices.length];
        r[0]=prices[0];
        for(int i=1;i<prices.length;i++){
            /*if(r[i-1]<prices[i])
                r[i]=r[i-1];
            else
                r[i]=prices[i];*/
            r[i]=Math.min(r[i-1],prices[i]);
        }
        int max=0;
        int currmin=Integer.MAX_VALUE;
        /*for(int i=1;i<prices.length;i++){
            if(prices[i]-r[i]>max)
                max=prices[i]-r[i];
        }*/
        for(int i=0;i<prices.length;i++){
            if(prices[i]<currmin)
                currmin=prices[i];
            max=Math.max(max, prices[i]-currmin);
        }
        return max;
    }
}
