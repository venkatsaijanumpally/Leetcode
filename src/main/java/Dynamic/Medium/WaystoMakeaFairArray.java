package Dynamic.Medium;

public class WaystoMakeaFairArray {
    public int waysToMakeFair(int[] nums) {
        int[] rightEvenSum=new int[nums.length];
        int[] leftEvenSum=new int[nums.length];
        int[] leftOddSum=new int[nums.length];
        int[] rightOddSum=new int[nums.length];
        rightEvenSum[nums.length-1]=0;
        rightOddSum[nums.length-1]=0;
        leftOddSum[0]=0;
        leftEvenSum[0]=0;

        for (int i=1;i< nums.length;i++){
            if((i-1)%2==0){
                leftEvenSum[i]+=nums[i-1]+leftEvenSum[i-1];
                leftOddSum[i]+=leftOddSum[i-1];
            }
            else {
                leftOddSum[i]+=nums[i-1]+leftOddSum[i-1];
                leftEvenSum[i]+=leftEvenSum[i-1];
            }
        }
        for (int i= nums.length-2;i>-1;i--){
            if((i+1)%2==0){
                rightEvenSum[i]+=nums[i+1]+rightEvenSum[i+1];
                rightOddSum[i]+=rightOddSum[i+1];
            }
            else{
                rightOddSum[i]+=nums[i+1]+rightOddSum[i+1];
                rightEvenSum[i]=rightEvenSum[i+1];
            }
        }
        int count=0;
        for (int i=0;i< nums.length;i++){
            int even=leftEvenSum[i]+rightOddSum[i];
            int odd=leftOddSum[i]+rightEvenSum[i];
            if(even==odd)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        //int[] arr={2,1,6,4};
        int[] arr={1,1,1,1,1};
        System.out.println(new WaystoMakeaFairArray().waysToMakeFair(arr));
    }
}
