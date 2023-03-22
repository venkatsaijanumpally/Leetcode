package Dynamic.MatrixChainMultiplication;

public class SuperEggDrop {
    /*
     * https://www.youtube.com/watch?v=S49zeUjeUL0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=42
     * https://leetcode.com/problems/super-egg-drop/discuss/159055/Java-DP-solution-from-O(KN2)-to-O(KNlogN)
     * eggDropUsingBinarySearch is optimal. Understand the question more clearly before going to solution.
     *
     * Question:
     * Given 'e' number of eggs and a building with 'f' floors.
     * Critical Floor: If 'C' is the critical floor then when a egg is thrown from the critical floor or below the
     * critical floor the egg wont break. When a egg is thrown from a floor more than the critical floor the egg will
     * break.
     *
     * If a egg is thrown from a floor and if it does not break then we can reuse the egg. else if it breaks then we
     * discard the egg.
     *
     * So our target is to find a
     *  - Best technique
     *  - Find the WORST CASE using this technique
     *  - Minimize the number of attempts
     *
     *
     * Example: When we are given 1 egg and 4 floors
     *      Throw egg from 1st floor if it breaks then floor 0 is the critical floor else go to next floor.
     *      Throw egg from 2nd floor if it breaks then floor 1 is the critical floor else go to next floor.
     *      Throw egg from 3rd floor if it breaks then floor 2 is the critical floor else go to next floor.
     *      Throw egg from 4th floor if it breaks then floor 3 is the critical floor else 4th floor is critical floor.
     *
     *      From the above example we see the BEST CASE SCENARIO require 1 attempt i.e if the critical floor is the 0th
     *      floor then if we throw the egg from 1st floor then it will break and we conclude 0th floor is the critical floor.
     *      The WORST CASE SCENARIO require 4 attempts i.e if the critical floor is 4th floor or 3rd floor we need to
     *      move from 1st floor till 4th floor throwing the egg from each floor and moving to above floor. If the egg
     *      breaks when thrown from 4th floor then the critical floor is 3rd floor else 4th floor.
     *
     * So our target is to find the WORST CASE attempts which in this case is 4.
     *
     * */
    Integer[][] memoize;
    public int superEggDrop(int k, int n) {
        memoize=new Integer[k+1][n+1];
        //return eggDrop(k,n);
        return eggDropUsingBinarySearch(k,n);
    }
    /*
     * Example 2:  2 eggs and 5 floors.
     * If the critical floor is 1st floor then
     *      Let us take a floor in between 1 and 5 say 3rd floor.
     *      If we throw the first egg from 3rd floor it will break then we understand that the critical floor can surely
     *      be below the 3rd floor since we have only one egg we start throwing the egg from 1 st floor till 2nd floor
     *      to find out the critical floor.
     *
     * In this approach we followed similar approach. We try to set a 'K' value using which we throw our first egg from
     * kth floor. If it breaks use eggdrop(e-1,k-1) else if it does not break then eggdrop(e,f-k). We try to find out
     * the worst case attempts for a technique. As well by changing the 'K' value we check the worst case attempts for
     * each technique and take minimum out of all the techniques.
     */
    private int eggDrop(int e, int f) {
        if(f==0 || f==1) return f;
        if(e==1) return f;
        if(memoize[e][f]!=null) return memoize[e][f];

        int result=Integer.MAX_VALUE;
        for(int k=1;k<=f;k++){
            int temp= 1+Math.max(eggDrop(e-1,k-1),eggDrop(e,f-k));
            result=Math.min(temp,result);
        }
        return memoize[e][f]=result;
    }


    /*
     * https://leetcode.com/problems/super-egg-drop/discuss/159055/Java-DP-solution-from-O(KN2)-to-O(KNlogN)
     * This approach is an optimization to eggDrop approach.
     * Notice that for the same 'e' when 'f' goes up, memoize[e][f] goes up.
     * Then for int left = eggDrop(e - 1, k - 1); int right = eggDrop(e, f - k); when k goes up, left goes up
     * and right goes down.
     * We can use Binary Search here to get the minimum Math.max(left, right) + 1, when left and right are as close as
     * possible. We come to this O(eflogf) solution:
     */
    //!Optimal
    private int eggDropUsingBinarySearch(int e, int f) {
        if(f==0 || f==1) return f;
        if(e==1) return f;
        if(memoize[e][f]!=null) return memoize[e][f];

        int result=Integer.MAX_VALUE;
        int low=1,high=f,mid;
        while (low<=high){
            mid=(high+low)/2;
            int left=eggDropUsingBinarySearch(e-1,mid-1);
            int right=eggDropUsingBinarySearch(e,f-mid);
            result=Math.min(result,1+Math.max(left,right));
            if(left==right)
                break;
            else if(left>right)
                high=mid-1;
            else low=mid+1;
        }

        return memoize[e][f]=result;
    }

    public static void main(String[] args) {
        System.out.println(new SuperEggDrop().superEggDrop(2,5));
        System.out.println(new SuperEggDrop().superEggDrop(1,2));
        System.out.println(new SuperEggDrop().superEggDrop(3,14));
        System.out.println(new SuperEggDrop().superEggDrop(2,6));
        System.out.println(new SuperEggDrop().superEggDrop(2,36));
    }
}
