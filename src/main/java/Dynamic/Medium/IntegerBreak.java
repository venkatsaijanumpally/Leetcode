package Dynamic.Medium;

public class IntegerBreak {
    Integer[] memoize;
    public int integerBreak(int n) {
        memoize=new Integer[n+1];
        return recursive(n);
    }

    /*
     * Approach: Memoized Recursion
     * For a particular 'n' we try to divide the 'n' into 2 parts 'i','n-i' then we try all combinations as below
     *      - We try further breaking down 'i','n-i' through recursion
     *      - Take max of i*(n-i),i*recuN,recI*(n-i)
     */
    private int recursive(int n) {
        if(n<3) return 1;
        if(memoize[n]!=null) return memoize[n];

        int max=0;
        for(int i=1;i<=n/2;i++){
            int recI=recursive(i);
            int recuN=recursive(n-i);
            int subRes=Math.max(i*(n-i),Math.max(i*recuN,recI*(n-i)));
            if(max<subRes) max=subRes;
        }
        return memoize[n]=max;
    }

    public static void main(String[] args) {
        System.out.println(new IntegerBreak().integerBreak(10));
        System.out.println(new IntegerBreak().integerBreak(3));
        System.out.println(new IntegerBreak().integerBreak(8));
    }
}
