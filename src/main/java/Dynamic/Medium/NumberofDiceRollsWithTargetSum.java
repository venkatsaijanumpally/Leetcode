package Dynamic.Medium;

public class NumberofDiceRollsWithTargetSum {
    Long[][] memoize;
    public int numRollsToTarget(int n, int k, int target) {
        memoize=new Long[n+1][target+1];
        //9223372036854775807
        //3192732988669888983
        long result=recursive(n,k,target)%(1000000007);
        return (int) result;
    }

    private long recursive(int n, int k, int target) {
        if(target<0) return 0;
        if(n==0)
            return target==0?1:0;
        if(memoize[n][target]!=null) return memoize[n][target];

        long count=0;
        for(int i=1;i<=k;i++){
            count+=recursive(n-1,k,target-i);
        }
        return memoize[n][target]=count%1000000007;
    }

    public static void main(String[] args) {
        //System.out.println(new NumberofDiceRollsWithTargetSum().numRollsToTarget(1,6,3));
        //System.out.println(new NumberofDiceRollsWithTargetSum().numRollsToTarget(2,6,7));
        System.out.println(new NumberofDiceRollsWithTargetSum().numRollsToTarget(30,30,500));
    }
}
