package Dynamic.Medium;

import java.util.Collections;

public class MaximumSubarraySumwithOneDeletion {
    public int maximumSum(int[] arr) {
        int[] maxStartHere=new int[arr.length];
        int[] maxEndHere=new int[arr.length];
        int max=arr[0];
        maxEndHere[0]=arr[0];
        //finding maxEndHere and max
        for(int i=1;i<arr.length;i++){
            maxEndHere[i]=Math.max(arr[i],maxEndHere[i-1]+arr[i]);
            max=Math.max(max,maxEndHere[i]);
        }

        maxStartHere[arr.length-1]=arr[arr.length-1];
        //finding maxStartHere and we need not find max here since we already calculated max in maxEndHere loop
        for(int i=arr.length-2;i>-1;i--){
            maxStartHere[i]=Math.max(arr[i],maxStartHere[i+1]+arr[i]);
        }

        //finding max by removing element at index 'i' and adding max on the left side and right side of 'i'
        for(int i=1;i<arr.length-1;i++){
            max=Math.max(max,maxEndHere[i-1]+maxStartHere[i+1]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr={1,-2,0,3};
        int[] arr1={1,-2,-2,3};
        int[] arr2={-1,-1,-1,-1};
        System.out.println(new MaximumSubarraySumwithOneDeletion().maximumSum(arr));
        System.out.println(new MaximumSubarraySumwithOneDeletion().maximumSum(arr1));
        System.out.println(new MaximumSubarraySumwithOneDeletion().maximumSum(arr2));
    }
}
