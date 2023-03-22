package Dynamic.Knapsack01;

public class MinimumCostForTickets {
    Integer[][] memoize;
    public int mincostTickets(int[] days, int[] costs) {
        memoize=new Integer[days.length+1][days[days.length-1]+2];

        return mincostTickets(days,costs,days.length-1,days[days.length-1]+1);
    }

    private int mincostTickets(int[] days, int[] costs, int dayIndex, int daysLeft) {
        if(dayIndex==-1 || daysLeft<0) return 0;

        if(memoize[dayIndex][daysLeft]!=null) return memoize[dayIndex][daysLeft];
        if(days[dayIndex]<daysLeft){
            int oneday=mincostTickets(days,costs,dayIndex-1,days[dayIndex]);
            int sevendays=mincostTickets(days,costs,dayIndex-1,days[dayIndex]-6);
            int thirtydays=mincostTickets(days,costs,dayIndex-1,days[dayIndex]-29);
            return memoize[dayIndex][daysLeft]=Math.min(oneday+costs[0],Math.min(sevendays+costs[1],thirtydays+costs[2]));
        }
        else return memoize[dayIndex][daysLeft]=mincostTickets(days,costs,dayIndex-1,daysLeft);
    }

    public static void main(String[] args) {
        int[] arr={1,4,6,7,8,20};
        int[] cost={2,7,15};
        int[] arr1={1,2,3,4,5,6,7,8,9,10,30,31};
        System.out.println(new MinimumCostForTickets().mincostTickets(arr,cost));
        System.out.println(new MinimumCostForTickets().mincostTickets(arr1,cost));
    }
}
