public class strStr {
    public static int strStr(int[] nums, int target){
        int low=0,high=nums.length-1,mid=1;
        while(low<=high){
            mid=low+(high-low)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]>target)
                high=mid-1;
            else
                low=mid+1;
        }
        return high+1;
    }
    public static void main(String args[]){
        int[] arr={0,1,3,5};
        System.out.println(strStr(arr,5));
    }
}
