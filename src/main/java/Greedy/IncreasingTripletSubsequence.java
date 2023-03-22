package Greedy;

public class IncreasingTripletSubsequence {
    /**
     * https://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if(nums.length<3) return false;
        Integer a=nums[0],b=null,c=null;
        /*for(int i=1;i<nums.length;i++){
            if (b==null){
                if(a>nums[i])
                    a=nums[i];
                else b=nums[i];
            }
            else if(c==null){
                if(nums[i]<b && nums[i]>a)
                    b=nums[i];
                else if(nums[i]>b) {
                    c=nums[i];
                    return true;
                }
            }
        }*/
        int min_num=nums[0];
        int max_seq=Integer.MIN_VALUE;
        int store_min=min_num;
        int seq=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==min_num)
                continue;
            else if(nums[i]<min_num){
                min_num=nums[i];
                continue;
            }
            else if(nums[i]<max_seq){
                max_seq=nums[i];
                store_min=min_num;
            }
            else if(nums[i]>max_seq){
                seq++;
                if(seq==3) return true;
                max_seq=nums[i];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums={20,100,10,12,5,13};
        System.out.println(new IncreasingTripletSubsequence().increasingTriplet(nums));
    }
}
