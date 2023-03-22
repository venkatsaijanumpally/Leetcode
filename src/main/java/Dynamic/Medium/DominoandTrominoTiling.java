package Dynamic.Medium;

public class DominoandTrominoTiling {
    Double[][] memoize;
    Long[][] memoizelong;
    public int numTilings(int n) {
        memoize=new Double[n+1][4];
        memoizelong=new Long[n+1][4];
        /*double s=recursive(n,3)%(1000000007);
        int res=(int)s;*/
        long l=recursivelong(n,3)%1000000007;
        int res=(int)l;
        return res;
    }

    private double recursive(int n, int empty) {
        if(n==0){
            if(empty==3) return 1d;
            return 0d;
        }
        if(memoize[n][empty]!=null) return memoize[n][empty];

        if(empty==3)
            return memoize[n][empty]=recursive(n-1,3)+recursive(n-1,1)+
                    recursive(n-1,2)+recursive(n-1,0);
        else if(empty==2) return memoize[n][empty]=recursive(n-1,0)+recursive(n-1,2);
        else if(empty==1) return memoize[n][empty]=recursive(n-1,0)+recursive(n-1,1);
        else return memoize[n][empty]=recursive(n-1,3);
    }

    private long recursivelong(int n, int empty) {
        if(n==0){
            if(empty==3) return 1;
            return 0;
        }
        if(memoizelong[n][empty]!=null) return memoizelong[n][empty];

        if(empty==3)
            return memoizelong[n][empty]=recursivelong(n-1,3)+recursivelong(n-1,1)+
                    recursivelong(n-1,2)+recursivelong(n-1,0);
        else if(empty==2) return memoizelong[n][empty]=recursivelong(n-1,0)+recursivelong(n-1,2);
        else if(empty==1) return memoizelong[n][empty]=recursivelong(n-1,0)+recursivelong(n-1,1);
        else return memoizelong[n][empty]=recursivelong(n-1,3);
    }

    private long recursiveOld(int n, int empty) {
        if(n==0){
            if(empty==2) return 1;
            return 0;
        }
        if(memoizelong[n][empty]!=null) return memoizelong[n][empty];

        if(empty==2)
            return memoizelong[n][empty]=recursiveOld(n-1,2)+2*recursiveOld(n-1,1)+
                    recursiveOld(n-1,0);
        else if(empty==1) return memoizelong[n][empty]=recursiveOld(n-1,0)+recursiveOld(n-1,1);
        else return memoizelong[n][empty]=recursiveOld(n-1,2);
    }

    public static void main(String[] args) {
        long l=(int)882347204;
        System.out.println(l);
        System.out.println(new DominoandTrominoTiling().numTilings(3));
        System.out.println(new DominoandTrominoTiling().numTilings(4));
        System.out.println(new DominoandTrominoTiling().numTilings(30));
    }
}
