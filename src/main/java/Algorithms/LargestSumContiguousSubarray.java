package Algorithms;

public class LargestSumContiguousSubarray {
    public static int KandaneAlgorithm(int[] nums){
        int max_so_far = Integer.MIN_VALUE;
        int max_ending_here = 0;
        for (int i=0;i<nums.length;i++){
            max_ending_here+=nums[i];
            if(max_so_far<max_ending_here)
                max_so_far=max_ending_here;
            if(max_ending_here<0)
                max_ending_here=0;
        }
        return max_so_far;
    }
    public static void main(String args[]){
        int[] arr={0,1,0,5};
        System.out.println(KandaneAlgorithm(arr));
    }
}
