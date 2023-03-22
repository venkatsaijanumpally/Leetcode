package Dynamic.Medium;

public class JumpGame {
    public static boolean canJump(int[] nums) {
        if(nums.length==1)
            return true;
        if(nums[0]==0)
            return false;
        int max=nums[0]-1;
        for(int i=1;i<nums.length-1;i++,--max){
            if(max==0&&nums[i]==0)
                return false;
            if(nums[i]>max)
                max=nums[i];
        }
        return true;
    }

    public static void main(String args[]) {
        int[] arr={1,0};
        System.out.println(canJump(arr));
    }
}
