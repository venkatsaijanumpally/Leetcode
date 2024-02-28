package Dynamic.Medium;

public class NumberofSmoothDescentPeriodsofaStock {
    public long getDescentPeriods(int[] prices) {
        int[] t=new int[prices.length];
        t[0]=1;
        long count =1;
        for (int i=1;i< prices.length;i++){
            count++;
            t[i]=1;
            if(prices[i-1]-prices[i]==1){
                t[i]=t[i-1]+1;
                count+=t[i-1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr={3,2,1,4};
        int[] arr2={8,6,7,7};
        System.out.println(new NumberofSmoothDescentPeriodsofaStock().getDescentPeriods(arr));
        System.out.println(new NumberofSmoothDescentPeriodsofaStock().getDescentPeriods(arr2));
    }
}
