import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            int l=i+1;
            int r= nums.length-1;
            /** The condition nums[i]==nums[i-1] helps in
             eliminating the duplicates */
            if(i>0&&nums[i]==nums[i-1])
                continue;
            while(l<r){
                /** The condition nums[r]==nums[r+1] helps in
                 eliminating the duplicates */
                if(r<nums.length-1&&nums[r]==nums[r+1]){
                    r--;
                    continue;
                }
                if(nums[i]+nums[l]+nums[r]>0)
                    r--;
                else if(nums[i]+nums[l]+nums[r]<0)
                    l++;
                else{
                    ArrayList<Integer> arrayList=new ArrayList<>();
                    arrayList.add(nums[i]);
                    arrayList.add(nums[l]);
                    arrayList.add(nums[r]);
                    result.add(arrayList);
                    l++;
                    r--;
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] arr={-1,0,1,2,-1,-4};
        System.out.println(threeSum(arr));
    }
}
