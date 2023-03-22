package Dynamic.LongestCommonSubsequence;

public class MaximumLengthofRepeatedSubarray {
    /*
     * This problem is exactly same as longestCommonSubstring
     */
    public int findLength(int[] nums1, int[] nums2) {
        int max=0;
        int[][] memoize=new int[nums1.length+1][nums2.length+1];
        for(int i=0;i< memoize.length;i++){
            for (int j=0;j<memoize[0].length;j++){
                if(i==0 || j==0) memoize[i][j]=0;
                else {
                    if(nums1[i-1]==nums2[j-1]){
                        memoize[i][j]=1+memoize[i-1][j-1];
                        if(max<memoize[i][j]) max=memoize[i][j];
                    }
                    else memoize[i][j]=0;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] one1={1,2,3,2,1};
        int[] two1={3,2,1,4,7};
        int[] one2={0,0,0,0,0};
        int[] two2={0,0,0,0,0};
        System.out.println(new MaximumLengthofRepeatedSubarray().findLength(one1,two1));
        System.out.println(new MaximumLengthofRepeatedSubarray().findLength(one2,two2));
    }
}
