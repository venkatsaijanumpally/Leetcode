package Dynamic.Medium;

public class LongestArithmeticSubsequence {
    Integer[][][] memoize;
    public int longestArithSeqLength(int[] nums) {
        memoize=new Integer[nums.length+1][501][1000];
        int max=0;
        for(int i=0;i<nums.length-1;i++)
            for (int j=i+1;j<nums.length;j++)
                max=Math.max(max,2+recursive(nums,j+1,nums[j],nums[j]-nums[i]));
        return max;
    }

    private int recursive(int[] nums, int index, int prevNum, int diff) {
        if(index== nums.length) return 0;
        if(memoize[index][prevNum][diff+500]!=null) return memoize[index][prevNum][diff+500];

        if(nums[index]==prevNum+diff)
            return memoize[index][prevNum][diff+500]=1+recursive(nums,index+1,nums[index],diff);
        else return memoize[index][prevNum][diff+500]=recursive(nums,index+1,prevNum,diff);
    }

    public static void main(String[] args) {
        int[] arr={3,6,9,12};
        int[] arr1={9,4,7,2,10};
        int[] arr2={20,1,15,3,10,5,8};
        System.out.println(new LongestArithmeticSubsequence().longestArithSeqLength(arr));
        System.out.println(new LongestArithmeticSubsequence().longestArithSeqLength(arr1));
        System.out.println(new LongestArithmeticSubsequence().longestArithSeqLength(arr2));
    }
}
