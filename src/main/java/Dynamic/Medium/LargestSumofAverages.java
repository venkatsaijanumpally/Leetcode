package Dynamic.Medium;

public class LargestSumofAverages {
    Double[][][] memoize;
    Double[][] memoize2;
    public double largestSumOfAverages(int[] nums, int k) {
        memoize=new Double[k][nums.length+1][nums.length+1];
        memoize2=new Double[k][nums.length+1];
        //return recursive(nums,k-1,0, nums.length);
        return recursiveMemoize(nums,k-1,0);
    }

    private double recursive(int[] nums, int k, int start, int end) {
        if(start==end) return 0;
        if(memoize[k][start][end]!=null) return memoize[k][start][end];

        double max=0;
        int sum=0;
        if(k==0){
            for(int i=start;i<end;i++){
                sum+=nums[i];
            }
            return memoize[k][start][end]=(double) sum/(end-start);
        }
        for(int i=start;i<end;i++){
            sum+=nums[i];
            double avg=(double) sum/(i-start+1);
            double rightavg=0;
            if(k>0) rightavg=recursive(nums,k-1,i+1,end);
            if(max<avg+rightavg)
                max=avg+rightavg;
        }
        return memoize[k][start][end]=max;
    }

    /*
     * Approach: Similar to Catalan number
     * For a given range divide the range into 2 parts and divide the 2nd part further.
     */
    //!Optimal
    private double recursiveMemoize(int[] nums, int k, int start){
        if(start==nums.length) return 0;
        if(memoize2[k][start]!=null) return memoize2[k][start];

        double max=0;
        int sum=0;
        if(k==0){
            for(int i=start;i<nums.length;i++){
                sum+=nums[i];
            }
            return memoize2[k][start]=(double) sum/(nums.length-start);
        }
        for(int i=start;i<nums.length;i++){
            sum+=nums[i];
            double avg=(double) sum/(i-start+1);
            double rightavg=0;
            if(k>0) rightavg=recursiveMemoize(nums,k-1,i+1);
            if(max<avg+rightavg)
                max=avg+rightavg;
        }
        return memoize2[k][start]=max;
    }

    public static void main(String[] args) {
        int[] arr={9,1,2,3,9};
        int[] arr1={1,2,3,4,5,6,7};
        int[] arr2={4,1,7,5,6,2,3};
        System.out.println(new LargestSumofAverages().largestSumOfAverages(arr,3));
        System.out.println(new LargestSumofAverages().largestSumOfAverages(arr1,4));
        System.out.println(new LargestSumofAverages().largestSumOfAverages(arr2,4));
    }
}
