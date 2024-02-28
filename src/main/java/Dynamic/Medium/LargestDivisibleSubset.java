package Dynamic.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int[] parent = new int[nums.length];
        for (int i=0; i< nums.length;i++){
            parent[i]=i;
        }

        Arrays.sort(nums);
        int[] count = new int[nums.length];
        int maxCount=0;
        int maxNode=0;
        for (int i=1;i<nums.length;i++){
            for (int j=i-1;j>=0;j--){
                if((nums[i]%nums[j]==0) && (count[j]+1 > count[i])){
                    count[i] = count[j] + 1;
                    parent[i] = j;
                    if(count[i]>maxCount){
                        maxCount = count[i];
                        maxNode = i;
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (true){
            result.add(0,nums[maxNode]);
            if(parent[maxNode] == maxNode) break;

            maxNode = parent[maxNode];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,4,8};
        int[] nums2 = {1,2,4,6,12};

        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(nums1));
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(nums2));
    }
}
