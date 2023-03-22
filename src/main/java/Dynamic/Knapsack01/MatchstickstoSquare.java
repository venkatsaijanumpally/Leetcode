package Dynamic.Knapsack01;

public class MatchstickstoSquare {
    public boolean makesquare(int[] matchsticks) {
        //TODO not working learn BITMASKING and BACKTRACKING this problem is same as Partiton K equal sum subsets.
        int sum=0;
        for(int i: matchsticks)
            sum+=i;
        if(sum%4!=0)//sum is odd then not possible else if sum/4 is fraction not possible
            return false;
        int target=sum/4;
        //return recursive(matchsticks,matchsticks.length-1,target)==4;
        return countSubsetSum(matchsticks, target);
    }

    private int recursive(int[] matchsticks, int i, int target) {
        if(target==0) return 1;
        if(i<0) return 0;

        if(matchsticks[i]<=target)
            return recursive(matchsticks,i-1,target-matchsticks[i])+recursive(matchsticks,i-1,target);
        return recursive(matchsticks,i-1,target);
    }

    private boolean countSubsetSum(int[] matchsticks, int target){
        int n=matchsticks.length;
        int[][] t=new int[target+1][n+1];
        for(int i=0;i<=n;i++)
            t[0][i]=1;
        for(int i=1;i<=target;i++){
            for(int j=1;j<=n;j++){
                if(matchsticks[j-1]<=i)
                    t[i][j]=t[i-matchsticks[j-1]][j-1]+t[i][j-1];
                else t[i][j]=t[i][j-1];;
            }
        }
        return t[target][n]==4;
    }

    public static void main(String[] args) {
        int[] arr={1,1,2,2,2};
        int[] arr2={3,3,3,3,4};
        int[] arr3={5,5,5,5,4,4,4,4,3,3,3,3};
        //System.out.println(new MatchstickstoSquare().makesquare(arr));
        //System.out.println(new MatchstickstoSquare().makesquare(arr2));
        System.out.println(new MatchstickstoSquare().makesquare(arr3));
    }
}
