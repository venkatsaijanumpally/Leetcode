import java.io.IOException;
import java.util.Arrays;

public class FirstandLastElement34 {
    public static void main(String[] args) throws IOException {
        int[] arr={1,2,2,3,3,4,4,4,4,5,7,9,9,9,10,10,10};
        System.out.println(Arrays.toString(new int[]{FirstOccurence(arr, 10), LastOccurence(arr, 10)}));
    }

    private static int FirstOccurence(int[] arr, int target) {
        int low=0;
        int high=arr.length-1;
        int res=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(target==arr[mid]){
                res=mid;
                high=mid-1;
            }
            else if(target>arr[mid])
                low=mid+1;
            else
                high=mid-1;
        }
        //return new int[]{2,3};
        return res;
    }
    private static int LastOccurence(int[] arr, int target) {
        int low=0;
        int high=arr.length-1;
        int res=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(target==arr[mid]){
                res=mid;
                low=mid+1;
            }
            else if(target>arr[mid])
                low=mid+1;
            else
                high=mid-1;
        }
        //return new int[]{2,3};
        return res;
    }
}
