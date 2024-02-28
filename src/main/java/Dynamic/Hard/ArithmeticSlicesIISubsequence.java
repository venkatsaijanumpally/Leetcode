package Dynamic.Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArithmeticSlicesIISubsequence {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) return 0;

        List<HashMap<Long, Integer>> arithmeticSequences = new ArrayList<>();
        arithmeticSequences.add(new HashMap<>());
        arithmeticSequences.add(new HashMap<>());
        arithmeticSequences.get(1).put((long)nums[1]-nums[0],1);

        int totalSubsequences=0;
        for (int i = 2; i < nums.length; i++) {
            HashMap<Long, Integer> currMap = new HashMap<>();
            arithmeticSequences.add(currMap);
            for (int j = 1; j < i; j++) {
                long diff = (long)nums[i] - nums[j];
                int numOfSubsequencesWithDiff = arithmeticSequences.get(j).getOrDefault(diff, 0);
                currMap.put(diff, numOfSubsequencesWithDiff + 1 + currMap.getOrDefault(diff, 0));
                totalSubsequences += numOfSubsequencesWithDiff;
            }
            currMap.put((long) (nums[i] - nums[0]), 1 + currMap.getOrDefault((long)(nums[i] - nums[0]), 0));
        }
        return totalSubsequences;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,4,6,8,10};
        int[] nums2 = {0,2000000000,-294967296};
        int[] nums3 = {-2147483648,0,-2147483648};
        System.out.println(new ArithmeticSlicesIISubsequence().numberOfArithmeticSlices(nums3));
    }
}
