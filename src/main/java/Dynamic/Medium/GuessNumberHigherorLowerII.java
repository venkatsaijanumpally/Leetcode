package Dynamic.Medium;

public class GuessNumberHigherorLowerII {
    public int getMoneyAmount(int n) {
        int[][] t=new int[n+1][n+1];
        /*int[] cummulative=new int[n+1];
        for (int i=1;i<=n;i++)
            cummulative[i]=cummulative[i-1]+i;*/
        for (int i=1;i<n;i++){
            t[i][i+1]=i;
        }

        for (int length=3;length<=n;length++){
            for (int i=1;i<=n-length+1;i++){
                int cost=Integer.MAX_VALUE;
                //int diff=Integer.MAX_VALUE;
                int j=i+length-1;
                for (int k=i+1;k<j;k++){
                    //int currDiff = (cummulative[j]-cummulative[k])-(cummulative[k-1]-cummulative[i-1]);
                    int currCost = k+ Math.max(t[i][k-1], t[k+1][j]);
                    if(currCost<cost) cost=currCost;
                }
                t[i][j]=cost;
            }
        }

        return t[1][n];
    }

    public static void main(String[] args) {
        System.out.println(new GuessNumberHigherorLowerII().getMoneyAmount(10));
    }
}
