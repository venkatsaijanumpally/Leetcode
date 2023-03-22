public class MaxConsecutiveOnesIII {
    /**
     * https://leetcode.com/discuss/interview-experience/1561125/Amazon-or-SDE-1-or-Offer
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int max=0;
        int count=0;
        if(nums.length==1){
            if(nums[0]==1 || k>=1)
                return 1;
            else
                return 0;
        }
        if(nums[0]==1)
            max=1;
        //else ++count;
        int ptr1=0,ptr2=0;
        while (ptr2<nums.length){
            if(nums[ptr2]==0)++count;
            if(count>k){
                if(nums[ptr1]==0 && count>0)
                    count--;
                ++ptr1;
                ++ptr2;

            }
            else {
                //if(nums[ptr2+1]==0) ++count;
                if(count<=k && ptr2-ptr1+1>max)
                    max=ptr2-ptr1+1;
                ptr2++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr={1,1,1,0,0,0,1,1,1,1,0};
        int[] arr1={0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        MaxConsecutiveOnesIII max=new MaxConsecutiveOnesIII();
        System.out.println(max.longestOnes(arr1,3));
    }
}
