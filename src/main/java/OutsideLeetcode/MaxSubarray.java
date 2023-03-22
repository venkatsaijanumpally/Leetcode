package OutsideLeetcode;

public class MaxSubarray {
    /*
     * Divide and conquer: ADT(video)
     */

    public int maxSubarraySum(int[] arr, int p, int r){
        if(p==r)
            return arr[p];
        int q=(p+r)/2;
        int left=maxSubarraySum(arr,p,q);
        int right=maxSubarraySum(arr,q+1,r);
        int full=maxCrossingSubarray(arr,p,q,r);
        return Math.max(left,Math.max(right,full));
    }

    private int maxCrossingSubarray(int[] arr, int p, int q, int r) {
        int leftMax=0;
        int rightMax=0;
        int left=0,right=0;
        for(int i=q;i>=p;i--){
            left+=arr[i];
            if(leftMax<left)leftMax=left;
        }
        for(int i=q+1;i<=r;i++){
            right+=arr[i];
            if(rightMax<right)rightMax=right;
        }
        return leftMax+rightMax;
    }

    public static void main(String[] args) {
        int[] arr={13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        System.out.println(new MaxSubarray().maxSubarraySum(arr,0,15));
    }
}
