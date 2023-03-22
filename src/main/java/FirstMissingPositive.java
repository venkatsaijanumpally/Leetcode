import java.util.HashSet;

public class FirstMissingPositive {
    public static void main(String[] args) {
        int[] nums={7,8,9,11,12};
        System.out.println(new FirstMissingPositive().firstMissingPositive(nums));
    }
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> hm=new HashSet<>();
        int least=1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0 && nums[i]>least)
                hm.add(nums[i]);
            else if(nums[i]==least)
                least++;
        }
        while(true){
            if(hm.contains(least)) least++;
            else break;
        }
        return least;
    }
}
