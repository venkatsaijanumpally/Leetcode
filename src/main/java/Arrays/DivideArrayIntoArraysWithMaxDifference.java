package Arrays;

import java.util.Arrays;
import java.util.Collections;

public class DivideArrayIntoArraysWithMaxDifference {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        int[][] result = new int[nums.length / 3][3];
        int curr = 0;
        for (int i = 0; i < result.length; i++){
            if(nums[curr+1]-nums[curr]<=k && nums[curr+2]-nums[curr+1]<=k && nums[curr+2]-nums[curr]<=k){
                result[i][0] = nums[curr];
                result[i][1] = nums[curr+1];
                result[i][2] = nums[curr+2];
            }
            else return new int[][]{};
            curr+=3;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 8, 7, 9, 3, 5, 1};
        int[] nums2 = {1,3,3,2,7,3};
        System.out.println(Arrays.deepToString(new DivideArrayIntoArraysWithMaxDifference().divideArray(nums1, 2)));
        System.out.println(Arrays.deepToString(new DivideArrayIntoArraysWithMaxDifference().divideArray(nums2, 3)));
    }
}
