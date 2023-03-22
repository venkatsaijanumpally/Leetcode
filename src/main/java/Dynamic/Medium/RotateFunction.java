package Dynamic.Medium;

public class RotateFunction {
    /*
     * The idea is that when we rotate an array and calculate the rotate sum, we are basically adding all the numbers to
     * the existing rotate sum, with special handling to the current last number. For e.g.
     * input array = [4 ,3 ,2 ,6]
     * The sum of the numbers is = 4 + 3 + 2 + 6 = 15
     * The rotate sum = 4 * 0 + 3 * 1 + 2 * 2 + 6 * 3 = 25

     * After 1st rotation, the array becomes [6, 4, 3, 2]. If you carefully observe, when we calculate the new rotate
     * sum, we are adding each number once more. Like 3 gets added once more, 2 gets added once more, since the entire
     * array is moving to the right, Except the last number, whose contribution becomes zero, since it moves back to
     * position 0.
     */
    public int maxRotateFunction(int[] nums) {
        int sum=0;
        int n= nums.length;
        int prevSum=0;
        for(int i=0;i< n;i++){
            sum+=nums[i];
            prevSum+=i*nums[i];
        }

        int rem=n-1;
        int max=prevSum;
        for(int i=1;i< n;i++){
            prevSum=prevSum - (n-1)*nums[rem] + sum-nums[rem];
            rem--;
            if(max<prevSum) max=prevSum;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums={4,3,2,6};
        System.out.println(new RotateFunction().maxRotateFunction(nums));
    }
}
