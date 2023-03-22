package Dynamic.Medium;

public class ArithmeticSlices {

    /*
     * 
     */

    int[] t;
    int[] maxLengthEndingAtCurrentIndex;
    int[] prevDiff;
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums.length<=2) return 0;
        t=new int[nums.length];
        maxLengthEndingAtCurrentIndex = new int[nums.length];
        prevDiff = new int[nums.length];
        t[0]=0;
        t[1]=0;
        maxLengthEndingAtCurrentIndex[0]=1;
        maxLengthEndingAtCurrentIndex[1]=2;
        prevDiff[1]=nums[1]-nums[0];

        int count=0;
        for(int i=2;i<nums.length;i++){
            int diff=nums[i]-nums[i-1];
            if(prevDiff[i-1]==diff && maxLengthEndingAtCurrentIndex[i-1]>=2){
                t[i]=t[i-1]+1;
                count+=t[i];
            }
            prevDiff[i]=diff;
            maxLengthEndingAtCurrentIndex[i]=2;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        int[] nums2={1,2,3,4,1,5,8,3,5,7,9,10};
        System.out.println(new ArithmeticSlices().numberOfArithmeticSlices(nums));
        System.out.println(new ArithmeticSlices().numberOfArithmeticSlices(nums2));
    }
}
