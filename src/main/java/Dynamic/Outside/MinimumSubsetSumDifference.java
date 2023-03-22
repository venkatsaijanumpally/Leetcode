package Dynamic.Outside;

public class MinimumSubsetSumDifference {

    int totalSum=0;
    Integer[][] memoize;
    Boolean[][] memoizeBool;
    /*
     * https://www.youtube.com/watch?v=-GtpxG6l_Mc
     * Both approaches are good.
     * Approach 1: Explanation in video
     *
     *      Sum1+Sum2=Totalsum
     *      Sum2=Totalsum-sum1;
     *
     *  We need Sum1-Sum2 to be minimum
     *  ==> Sum1-(Totalsum-Sum1)  to be minimum
     *  ==> 2*Sum1-Totalsum to be min
     *
     * We take the last row of subset sum array which is calculated for totalsum
     * What does last row give-> last row gives array of full size calculated over various target sum whether it is possible
     * or not to get the sum.
     *
     * Now we create a "res" array that has only the first half of last row array and try to find the minimum we can get
     * for totalSum-2*Sum
     */
    public int minimum(int[] nums){
        for(int i=0;i<nums.length;i++)
            totalSum+=nums[i];
        memoize=new Integer[nums.length+1][totalSum+1];
        memoizeBool=new Boolean[nums.length+1][totalSum+1];
        //Top-Down approach for subset sum
        for(int i=0;i< nums.length+1;i++)
            for(int j=0;j<totalSum+1;j++){
                if(j==0) memoizeBool[i][j]=true;
                else if(i==0) memoizeBool[i][j]=false;
                else if(nums[i-1]<=j)
                    memoizeBool[i][j]=memoizeBool[i-1][j-nums[i-1]] || memoizeBool[i-1][j];
                else memoizeBool[i][j]=memoizeBool[i-1][j];
            }

        int[] res=new int[(totalSum+2)/2];
        for(int i=0;i<res.length;i++)
            res[i]=memoizeBool[nums.length][i]?i:-1;
        for(int i=res.length-1;i>=0;i--){
            if(res[i]!=-1)
                return totalSum-2*res[i];
        }
        return 1;
        //return recursiveMemoize(nums,0,0);
        //return recursive(nums,0,0,0);
    }

    /*
     * Approach 2: Own Solution
     * Approach is at a particular scenario take 2 possiblities "take the element into sum1" or "take the element into sum2"
     * Since sum2 depends on sum1 we can only have one dimension sum for the memoized array and other dimension is index.
     */
    private int recursiveMemoize(int[] nums, int index, int sum) {
        if(index==nums.length)
            return Math.abs(sum-(totalSum-sum));

        if(memoize[index][sum]!=null) return memoize[index][sum];
        int add1=recursiveMemoize(nums,index+1,sum+nums[index]);
        int add2=recursiveMemoize(nums,index+1,sum);

        return memoize[index][sum]=Math.min(add1, add2);
    }

    private int recursive(int[] nums, int index, int sum1, int sum2) {
        if(index==nums.length)
            return Math.abs(sum1-sum2);

        int add1=recursive(nums,index+1,sum1+nums[index],sum2);
        int add2=recursive(nums,index+1,sum1,sum2+nums[index]);

        return Math.min(add1, add2);
    }

    public static void main(String[] args) {
        int[] arr={1, 6, 11, 5};
        int[] arr1={3, 1, 4, 2, 2, 1};
        int[] arr2={3,4,2,7};
        int[] arr3={1,2,7};
        System.out.println(new MinimumSubsetSumDifference().minimum(arr));
        System.out.println(new MinimumSubsetSumDifference().minimum(arr1));
        System.out.println(new MinimumSubsetSumDifference().minimum(arr3));
    }
}
