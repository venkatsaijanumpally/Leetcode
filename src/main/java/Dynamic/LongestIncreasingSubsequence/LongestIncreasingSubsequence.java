package Dynamic.LongestIncreasingSubsequence;

import java.util.TreeSet;

public class LongestIncreasingSubsequence {
    Integer[][] memoize;
    /*
     * Refer Karumanchi
     * https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
     * Both Method 1 and Method 2 are Optimal
     *
     * Method 1:TopDown with sorted array
     * First we create a sorted array from the given array removing duplicates, Treeset removes the duplicates when adding.
     * Then we have nums array and sorted array now we just need to find the longestcommonsubsequence between both the
     * arrays.
     *
     * Method 2: Using LIS array
     * LIS[i] represents the longest increasing subsequence from 0 till i index.
     * We iterate from 0 to nums length calculating the LIS[i].
     * Basic idea to find LIS[i] is we compare all the element that is less than nums[i] and is between 0 to i-1 and
     * provides the max value.
     */
    public int lengthOfLIS(int[] nums) {
        //Method 1:LCS TopDown
        /*memoize=new Integer[nums.length+1][nums.length+1];
        TreeSet<Integer> set=new TreeSet<>();
        for (int i=0;i< nums.length;i++)
            set.add(nums[i]);
        int[] sortedArr=new int[set.size()];
        int i=0;
        for(int val: set)
            sortedArr[i++]=val;
        //return longestCommonSubsequence(nums,sortedArr,nums.length,set.size());
        return longestCommonSubsequenceTopDown(nums,sortedArr, nums.length,set.size());*/


        //Method 2
        int max=1;
        int[] LIS=new int[nums.length];
        LIS[0]=1;
        for(int i=1;i< nums.length;i++){
            //Every element has atleast itself as LIS. So we give 1 for every index.
            LIS[i]=1;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i] && LIS[j]+1>LIS[i])
                    LIS[i]=LIS[j]+1;
            }
            if(LIS[i]>max) max=LIS[i];
        }
        return max;
    }

    private int longestCommonSubsequenceTopDown(int[] nums, int[] sortedArr, int indexnums, int indexSort){
        int[][] t=new int[nums.length+1][sortedArr.length+1];

        for(int i=1;i<t.length;i++){
            for (int j=1;j<=indexSort;j++){
                if(nums[i-1]!=sortedArr[j-1])
                    t[i][j]=Math.max(t[i-1][j],t[i][j-1]);
                else t[i][j]=1+t[i-1][j-1];
            }
        }
        return t[indexnums][indexSort];
    }

    //Recursive Memoize
    private int longestCommonSubsequence(int[] nums, int[] sortedArr, int indexnums, int indexSort) {
        if(indexnums==0 || indexSort==0) return 0;
        if(memoize[indexnums][indexSort]!=null) return memoize[indexnums][indexSort];

        if(nums[indexnums-1]!=sortedArr[indexSort-1])
            return memoize[indexnums][indexSort]=Math.max(longestCommonSubsequence(nums,sortedArr,indexnums-1,indexSort),
                    longestCommonSubsequence(nums,sortedArr,indexnums,indexSort-1));
        else return memoize[indexnums][indexSort]=1+longestCommonSubsequence(nums,sortedArr,indexnums-1,indexSort-1);
    }

    public static void main(String[] args) {
        int[] arr={10,9,2,5,3,7,101,18};
        int[] arr1={7,7,7,7,7};
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(arr));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(arr1));
    }
}
