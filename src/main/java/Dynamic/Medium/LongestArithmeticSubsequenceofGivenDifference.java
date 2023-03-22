package Dynamic.Medium;

import java.util.HashMap;

public class LongestArithmeticSubsequenceofGivenDifference {
    public int longestSubsequence(int[] arr, int difference) {
        int max=1;
        /*Integer[][] memoize=new Integer[arr.length][arr.length];
        for(int i=0;i<arr.length;i++){
            max=Math.max(max, 1+recursive(arr,difference,i+1,i));
        }*/

        //!Optimal
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            hm.put(arr[i],1+hm.getOrDefault(arr[i]-difference,0));
            max=Math.max(hm.get(arr[i]),max);
        }
        return max;
    }

    private int recursive(int[] arr, int difference, int index, int prevIndex) {
        if(index==arr.length) return 0;

        if(arr[index]-arr[prevIndex]==difference)
            return 1+recursive(arr,difference,index+1,index);
        else return recursive(arr,difference,index+1,prevIndex);
    }

    public static void main(String[] args) {
        int[] arr={1,2,3,4};
        int[] arr1={1,3,5,7};
        int[] arr2={1,5,7,8,5,3,4,2,1};
        System.out.println(new LongestArithmeticSubsequenceofGivenDifference().longestSubsequence(arr,1));
        System.out.println(new LongestArithmeticSubsequenceofGivenDifference().longestSubsequence(arr1,1));
        System.out.println(new LongestArithmeticSubsequenceofGivenDifference().longestSubsequence(arr2,-2));
    }
}
