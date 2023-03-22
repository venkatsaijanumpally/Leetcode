package Dynamic.Medium;

import java.util.ArrayList;
import java.util.Arrays;

public class FindTwoNonoverlappingSubarraysEachWithTargetSum {

    /*
     * prefix arrays says the minimum length subarray from 0 till i, if target sum not available then Integer.MAX is set
     * suffix arrays says the minimum length subarray from i till arr.length, if target sum not available then Integer.MAX is set
     * then we traverse from left to right and check prefix[i-1]+suffix[i] and take minimum value.
     */
    public int minSumOfLengths(int[] arr, int target) {
        int[] prefix=new int[arr.length];
        int[] suffix=new int[arr.length];
        int start=0,end=0;
        int sum=arr[0];
        while (true){
            if(sum==target){
                prefix[end]=end-start+1;
                suffix[start]=end-start+1;
                end++;
                if(end== arr.length)
                    break;
                sum+=arr[end];
            }
            else if (sum>target) {
                if(start==end){
                    end++;
                    if(end== arr.length)
                        break;
                    sum+=arr[end];
                }
                else{
                    sum-=arr[start];
                    start++;
                }
            }
            else {
                end++;
                if(end==arr.length)
                    break;
                sum+=arr[end];
            }
        }

        suffix[arr.length-1]=suffix[arr.length-1]==0?Integer.MAX_VALUE:suffix[arr.length-1];
        for(int i=arr.length-2;i>=0;i--){
            suffix[i]=Math.min(suffix[i]==0?Integer.MAX_VALUE:suffix[i],suffix[i+1]);
        }

        prefix[0]=prefix[0]==0?Integer.MAX_VALUE:prefix[0];
        for(int i=1;i<arr.length;i++){
            prefix[i]=Math.min(prefix[i-1]==0?Integer.MAX_VALUE:prefix[i-1],prefix[i]==0?Integer.MAX_VALUE:prefix[i]);
        }

        int min=Integer.MAX_VALUE;
        for(int i=1;i<arr.length;i++){
            if(prefix[i-1]!=Integer.MAX_VALUE && suffix[i]!=Integer.MAX_VALUE){
                min=Math.min(prefix[i-1]+suffix[i],min);
            }
        }


        return min==Integer.MAX_VALUE?-1:min;
    }

    public static void main(String[] args) {
        int[] arr6={2,2,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] arr5={1,6,1};
        int[] arr={3,2,2,4,3};
        int[] arr2={7,3,4,7};
        int[] arr3={2,2,3,3,4,7};
        int[] arr4={4,3,2,6,2,3,4};
        System.out.println(new FindTwoNonoverlappingSubarraysEachWithTargetSum().minSumOfLengths(arr6,20));
        System.out.println(new FindTwoNonoverlappingSubarraysEachWithTargetSum().minSumOfLengths(arr5,7));
        System.out.println(new FindTwoNonoverlappingSubarraysEachWithTargetSum().minSumOfLengths(arr,3));
        System.out.println(new FindTwoNonoverlappingSubarraysEachWithTargetSum().minSumOfLengths(arr2,7));
        System.out.println(new FindTwoNonoverlappingSubarraysEachWithTargetSum().minSumOfLengths(arr3,7));
        System.out.println(new FindTwoNonoverlappingSubarraysEachWithTargetSum().minSumOfLengths(arr4,6));
    }
}
