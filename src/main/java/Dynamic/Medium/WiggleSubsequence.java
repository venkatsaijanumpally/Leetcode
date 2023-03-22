package Dynamic.Medium;

public class WiggleSubsequence {
    int[][] t;
    public int wiggleMaxLength(int[] nums) {
        t=new int[nums.length][2];
        int max=1;
        for(int i=0;i< nums.length;i++){
            int count=1;
            t[i][0]=1;
            t[i][1]=1;
            int temp=1;
            for(int j=i-1;j>=0;j--){
                if(nums[j]<nums[i] && t[j][1]+1>count){
                    count=t[j][1]+1;
                    temp=0;
                }
                else if(nums[j]>nums[i] && t[j][0]+1>count){
                    count=t[j][0]+1;
                    temp=1;
                }
            }
            t[i][temp]=count;
            if(max<count)
                max=count;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] sequence1={1,7,4,9,2,5};
        int[] sequence2={1,17,5,10,13,15,10,5,16,8};
        System.out.println(new WiggleSubsequence().wiggleMaxLength(sequence1));
        System.out.println(new WiggleSubsequence().wiggleMaxLength(sequence2));
    }
}
