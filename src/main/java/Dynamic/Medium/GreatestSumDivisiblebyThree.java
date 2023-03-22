package Dynamic.Medium;

public class GreatestSumDivisiblebyThree {
    public int maxSumDivThree(int[] nums) {
        int[][] t=new int[nums.length][3];
        t[0][nums[0]%3]=nums[0];
        for(int i=1;i< nums.length;i++){
            //for(int j=0;j<3;j++){
                int mod=nums[i]%3;
                //for(int k=i-1;k>=0;k--){
                    /*t[i][(mod+3)%3]=Math.max(nums[i]+t[k][Math.abs((mod-3)%3)],t[i][(mod+3)%3]);
                    t[i][(mod+2)%3]=Math.max(nums[i]+t[k][Math.abs((mod-2)%3)], t[i][(mod+2)%3]);
                    t[i][(mod+1)%3]=Math.max(nums[i]+t[k][Math.abs((mod-1)%3)], t[i][(mod+1)%3]);*/

                    /*t[i][(mod+3)%3]=t[k][Math.abs((mod-3)%3)]!=0?Math.max(nums[i]+t[k][Math.abs((mod-3)%3)],t[i][(mod+3)%3]):t[i][(mod+3)%3];
                    t[i][(mod+2)%3]=t[k][Math.abs((mod-2)%3)]!=0?Math.max(nums[i]+t[k][Math.abs((mod-2)%3)],t[i][(mod+2)%3]):t[i][(mod+2)%3];
                    t[i][(mod+1)%3]=t[k][Math.abs((mod-1)%3)]!=0?Math.max(nums[i]+t[k][Math.abs((mod-1)%3)],t[i][(mod+1)%3]):t[i][(mod+1)%3];*/
                //}
            if(mod==0){
                t[i][0]=nums[i]+t[i-1][0];
                t[i][1]=t[i-1][1]!=0?t[i-1][1]+nums[i]:0;
                t[i][2]=t[i-1][2]!=0?t[i-1][2]+nums[i]:0;
            }
            else if(mod==1){
                t[i][0]=Math.max(t[i-1][0], t[i-1][2]!=0?t[i-1][2]+nums[i]:0);
                t[i][1]=Math.max(nums[i]+t[i-1][0],t[i-1][1]);
                t[i][2]=Math.max(t[i-1][1]!=0?nums[i]+t[i-1][1]:0,t[i-1][2]);
            }
            else {
                t[i][0]=Math.max(t[i-1][1]!=0?nums[i]+t[i-1][1]:0,t[i-1][0]);
                t[i][1]=Math.max(t[i-1][2]!=0?nums[i]+t[i-1][2]:0,t[i-1][1]);
                t[i][2]=Math.max(nums[i]+t[i-1][0],t[i-1][2]);
            }
            //}
        }

        int max=0;
        for(int i=0;i< nums.length;i++){
            max=Math.max(t[i][0],max);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums={3,6,5,1,8};
        int[] nums2={1,2,3,4,4};
        int[] nums3={2,19,6,16,5,10,7,4,11,6};
        System.out.println(new GreatestSumDivisiblebyThree().maxSumDivThree(nums3));
    }
}
