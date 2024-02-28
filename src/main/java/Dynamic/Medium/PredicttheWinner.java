package Dynamic.Medium;

public class PredicttheWinner {
    public boolean predictTheWinner(int[] nums) {
        long[][] opponentOptimal = new long[nums.length][nums.length];
        long[] cumulative = new long[nums.length+1];

        for (int i=0;i< nums.length;i++){
            opponentOptimal[i][i]=nums[i];
        }
        for (int i=1;i<= nums.length;i++){
            cumulative[i]=cumulative[i-1]+nums[i-1];
        }

        for (int length=2;length<=nums.length;length++){
            for (int i=0;i<= nums.length-length;i++){
                int j=i+length-1;
                opponentOptimal[i][j]=Math.max(nums[i]+cumulative[j+1]-cumulative[i+1]-opponentOptimal[i+1][j], nums[j]+cumulative[j]-cumulative[i]-opponentOptimal[i][j-1]);
            }
        }

        return opponentOptimal[0][nums.length-1]*2 >= cumulative[nums.length];
    }

    public static void main(String[] args) {
        int[] nums={1,5,2};
        int[] nums4={1,5,2,4,6};
        int[] nums2={1,5,233,7};
        int[] nums3={877854,7113184,3270279,2243110,1902970,9268285,8784788,3837608,6582224,8751349,6928223,3108757,1120749,1872910,7762600,4220578,4692740,3409910,6807125,6808582};

        System.out.println(new PredicttheWinner().predictTheWinner(nums4));
        System.out.println(new PredicttheWinner().predictTheWinner(nums3));
        System.out.println(new PredicttheWinner().predictTheWinner(nums2));
    }
}
