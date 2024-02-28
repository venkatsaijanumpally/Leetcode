package Dynamic.Medium;

public class MaximumAbsoluteSumofAnySubarray {
    public int maxAbsoluteSum(int[] nums) {
        int sum=0;
        int absolute_sum=0;
        int max=0;
        //For forwarding positive sum
        for (int i=0;i< nums.length;i++){
            if(sum<=0){
                sum=nums[i];
                absolute_sum=Math.abs(sum);
            }
            else {
                sum+=nums[i];
                absolute_sum=Math.abs(sum);
            }
            max=Math.max(max,absolute_sum);
        }

        sum=0;
        //For forwarding negative sum
        for (int i=0;i< nums.length;i++){
            if(sum>0){
                sum=nums[i];
                absolute_sum=Math.abs(sum);
            }
            else {
                sum+=nums[i];
                absolute_sum=Math.abs(sum);
            }
            max=Math.max(max,absolute_sum);
        }
        return max;
    }
}
