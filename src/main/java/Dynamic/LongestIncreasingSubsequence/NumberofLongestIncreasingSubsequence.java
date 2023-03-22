package Dynamic.LongestIncreasingSubsequence;

public class NumberofLongestIncreasingSubsequence {
    //TODO Not Working
    public int findNumberOfLIS(int[] nums) {
        int[] LIS=new int[nums.length];
        int[] count=new int[nums.length+1];

        /*int maxCount=1;
        LIS[0]=1;
        count[1]=1;
        for(int i=1;i<nums.length;i++){
            LIS[i]=1;
            for (int j=0;j<i;j++){
                if(LIS[j]+1==LIS[i])
                    count[LIS[i]]++;
                else if(nums[j]<nums[i] && LIS[j]+1>LIS[i]){
                    LIS[i]=LIS[j]+1;
                    count[LIS[i]]++;
                }
            }
            if(maxCount<count[LIS[i]])
                maxCount=count[LIS[i]];
        }
        return maxCount;*/


        int max=1;
        LIS[0]=1;
        for(int i=1;i< nums.length;i++){
            //Every element has atleast itself as LIS. So we give 1 for every index.
            LIS[i]=1;
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    if(LIS[j]+1==LIS[i])
                        count[LIS[i]]++;
                    else if(LIS[j]+1>LIS[i]){
                        LIS[i]=LIS[j]+1;
                        count[LIS[i]]++;
                    }
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        int[] arr={1,3,5,4,7};
        int[] arr1={2,2,2,2,2};
        int[] arr2={1,2,4,3,5,4,7,2};
        //System.out.println(new NumberofLongestIncreasingSubsequence().findNumberOfLIS(arr));
        //System.out.println(new NumberofLongestIncreasingSubsequence().findNumberOfLIS(arr1));
        System.out.println(new NumberofLongestIncreasingSubsequence().findNumberOfLIS(arr2));
    }
}
