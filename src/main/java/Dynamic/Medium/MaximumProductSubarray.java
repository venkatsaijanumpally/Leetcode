package Dynamic.Medium;

public class MaximumProductSubarray {
    int maxProd;
    Integer[] memoize;
    public int maxProduct(int[] nums) {
        /*memoize=new Integer[nums.length];
        maxProd=nums[nums.length-1];
        recursivePlain(nums,0);
        return maxProd;*/

        //return maxProdKadaneAlgorithm(nums);
        //return recursivePlain(nums,nums.length-1);
        return test(nums, nums.length);
    }

    //!Optimal
    public int test(int[] A,int n){
        // store the result that is the max we have found so far
        int r = A[0];

        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for (int i = 1, imax = r, imin = r; i < n; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (A[i] < 0){
                int temp=imax;
                imax=imin;
                imin=temp;
            }

            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = Math.max(A[i], imax * A[i]);
            imin = Math.min(A[i], imin * A[i]);

            // the newly computed max value is a candidate for our global result
            r = Math.max(r, imax);
        }
        return r;
    }

    //Not Working
    private int recursivePlain(int[] nums, int index) {
        /*if(index==nums.length-1) return memoize[index]=nums[index];
        if(memoize[index]!=null) return memoize[index];

        int take=recursivePlain(nums,index+1);

        memoize[index]=Math.max(take*nums[index],nums[index]);
        if(maxProd<memoize[index]) maxProd=memoize[index];
        return memoize[index];*/

        if(index==-1) return 1;

        //return Math.max(nums[index]*recursivePlain(nums,index-1),Math.max(recursivePlain(nums,index-1),nums[index]));
        if(nums[index]>0) return nums[index]*recursivePlain(nums,index-1);
        else return Math.max(nums[index]*recursivePlain(nums,index-1),1);
    }

    public static void main(String[] args) {
        int[] arr={2,3,0,7,-2,4};
        int[] arr2={-2,0,-1};
        int[] arr3={-20};
        int[] arr4={2,3,0,7,-2,4,-2,5};
        int[] arr5={4,-1,3};
        System.out.println(new MaximumProductSubarray().maxProduct(arr));
        System.out.println(new MaximumProductSubarray().maxProduct(arr2));
        System.out.println(new MaximumProductSubarray().maxProduct(arr3));
        System.out.println(new MaximumProductSubarray().maxProduct(arr4));
        System.out.println(new MaximumProductSubarray().maxProduct(arr5));
    }
}
