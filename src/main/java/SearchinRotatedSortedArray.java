import java.io.IOException;

public class SearchinRotatedSortedArray {
    public static void main(String[] args) throws IOException {
        int[] arr={9,10,5,6,8};
        System.out.println(searchnew(arr,5));
    }

    public static int searchnew(int[] nums, int target){
        int low=0;
        int high=nums.length-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(target==nums[mid])
                return mid;
            if(nums[low]<=nums[mid]){
                if(target<nums[mid] && target>=nums[low])
                    high=mid-1;
                else
                    low=mid+1;
            }
            else {
                if(target<=nums[high] && target>nums[mid])
                    low=mid+1;
                else
                    high=mid-1;
            }
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        int pivot;
        if(nums[0]<=nums[nums.length-1])
            pivot=-1;
        else
            pivot=findPivot(nums,0,nums.length-1);
        int low,high;
        if(pivot==-1){
            low=0;
            high=nums.length-1;
        }
        else{
            if(target<=nums[pivot]&&target>=nums[0]){
                low=0;
                high=pivot;
            }
            else{
                low=pivot+1;
                high=nums.length-1;
            }
        }
        return binarySearch(nums,target,low,high);
        //return pivot;
    }

    public static int findPivot(int[] nums, int start, int end){
        if(start>end)
            return -1;
        if(start==end)
            return start;
        int mid=(start+end)/2;
        if(start<mid && nums[mid]<nums[mid-1])
            return mid-1;
        if(mid<end && nums[mid]>nums[mid+1])
            return mid;
        if(nums[start]>nums[mid])
            return findPivot(nums, start, mid-1);
        return findPivot(nums, mid, end);
    }

    public static int binarySearch(int[] nums, int target, int low, int high){
        if(low>high)
            return -1;
        int mid=(low+high)/2;
        if(target==nums[mid])
            return mid;
        if(target<nums[mid])
            return binarySearch(nums, target, low, mid-1);
        return binarySearch(nums, target, mid+1, high);
    }
}
