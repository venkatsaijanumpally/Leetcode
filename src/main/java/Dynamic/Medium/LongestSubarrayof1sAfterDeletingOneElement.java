package Dynamic.Medium;

public class LongestSubarrayof1sAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        int[] onesOnLeft=new int[nums.length];
        int[] onesOnRight=new int[nums.length];

        onesOnLeft[0]=0;
        onesOnRight[nums.length-1]=0;

        for (int i=1;i< nums.length;i++){
            if(nums[i-1]==1)
                onesOnLeft[i]=onesOnLeft[i-1]+1;
            else onesOnLeft[i]=0;
        }
        for (int i= nums.length-2;i>=0;i--){
            if(nums[i+1]==1)
                onesOnRight[i]=onesOnRight[i+1]+1;
            else onesOnRight[i]=0;
        }

        int max=onesOnRight[0];
        for (int i=1;i< nums.length-1;i++){
            max=Math.max(onesOnLeft[i]+onesOnRight[i],max);
        }
        max=Math.max(onesOnLeft[nums.length-1],max);
        return max;
    }

    public static void main(String[] args) {
        int[] num={1,1,0,1};
        System.out.println(new LongestSubarrayof1sAfterDeletingOneElement().longestSubarray(num));
    }
}
