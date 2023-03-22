package Dynamic.Medium;

public class JumpGameII {
    public static int canJump(int[] nums) {
        /**
         * https://www.youtube.com/watch?v=dJ7sWiOoK7g
         */
        int result=0;
        int l=0,r=0;
        while(r<nums.length-1){
            result++;
            int farthest = 0;
            for(int i=l;i<=r;i++)
                farthest=Math.max(farthest,i+nums[i]);
            l=r+1;
            r=farthest;
        }
        return result;
    }

    public static int canJumpDP(int[] nums){
        int n = nums.length;
        int[] ans=new int[n];
        ans[0] = 0;

        int i=0, j=1;
        while(i<nums.length){
            j=i+1;
            while(j-i<=nums[i] && j<nums.length){
                ans[j] = Math.min(ans[i]+1, ans[j]);
                j++;
            }
            i++;
        }
        return ans[n-1];
    }


    /*public static int recursiveCaller(int[] nums){
        return recursivePlain(nums,0);
    }

    private static int recursivePlain(int[] nums, int index) {
        if(index>=nums.length-1) return 0;

        int
    }*/

    public static void main(String args[]) {
        int[] arr={2,3,1,1,4};
        System.out.println(canJump(arr));
    }
}
