package Dynamic.LongestCommonSubsequence;

public class UncrossedLines {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] memoize=new int[nums1.length+1][nums2.length+1];

        for(int i=1;i< memoize.length;i++){
            for(int j=1;j<memoize[0].length;j++){
                if(nums1[i-1]==nums2[j-1]) memoize[i][j]=1+memoize[i-1][j-1];
                else memoize[i][j]=Math.max(memoize[i-1][j],memoize[i][j-1]);
            }
        }
        return memoize[nums1.length][nums2.length];
    }

    public static void main(String[] args) {
        int[] arr1={2,5,1,2,5};
        int[] arr2={10,5,2,1,5,2};
        System.out.println(new UncrossedLines().maxUncrossedLines(arr1,arr2));
    }
}
