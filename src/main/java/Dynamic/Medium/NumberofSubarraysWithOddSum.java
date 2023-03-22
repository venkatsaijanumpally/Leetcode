package Dynamic.Medium;

public class NumberofSubarraysWithOddSum {
    public int numOfSubarrays(int[] arr) {
        long count=0;
        int oddSumSubArrays=0,evenSumSubArrays=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]%2==0){
                count+=oddSumSubArrays;
                evenSumSubArrays++;
            }
            else {
                int prevOddSum=oddSumSubArrays;
                int prevEvenSum=evenSumSubArrays;
                oddSumSubArrays=prevEvenSum;
                oddSumSubArrays++;
                count+=oddSumSubArrays;
                evenSumSubArrays=prevOddSum;
            }
            count=count%1000000007;
        }
        return (int) count;
    }

    public static void main(String[] args) {
        int[] a={1,2,3,4};
        int[] arr={1,3,5};
        int[] arr2={2,4,6};
        int[] arr3={1,2,3,4,5,6,7};
        System.out.println(new NumberofSubarraysWithOddSum().numOfSubarrays(a));
        System.out.println(new NumberofSubarraysWithOddSum().numOfSubarrays(arr));
        System.out.println(new NumberofSubarraysWithOddSum().numOfSubarrays(arr2));
        System.out.println(new NumberofSubarraysWithOddSum().numOfSubarrays(arr3));
    }
}
