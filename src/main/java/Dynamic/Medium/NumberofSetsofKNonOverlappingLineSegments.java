package Dynamic.Medium;

public class NumberofSetsofKNonOverlappingLineSegments {
    Integer[][][] memoize;
    public int numberOfSets(int n, int k) {
        memoize=new Integer[n+1][k+1][2];
        return recursive(n-1,k,0);
    }

    /*
     * https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments/discuss/901894/JavaPython-Top-Down-DP-Clean-and-Concise-O(4*n*k)
     *
     */
    private int recursive(int n, int k, int started) {
        if(k==0) return n+1;
        if(n==0) return 0;
        if(memoize[n][k][started]!=null) return memoize[n][k][started];

        int res=recursive(n-1,k,started); //If a segment is already started then expand segment else skip the segment
        if(started==1)
            res+=recursive(n,k,0); //End the segment here. Here we maintain segment position 'n' as it is so that a new segment can start at the same point in the next call.
        else
            res+=recursive(n-1,k-1,1); //Start a new segment here.

        return memoize[n][k][started]=res%1000000007;
    }

    public static void main(String[] args) {
        System.out.println(new NumberofSetsofKNonOverlappingLineSegments().numberOfSets(4,2));
        System.out.println(new NumberofSetsofKNonOverlappingLineSegments().numberOfSets(30,7));
    }
}
