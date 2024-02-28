package Dynamic.Hard;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            else {
                return a[0] - b[0];
            }
        });
        int nums[]=new int [envelopes.length];
        int j=0;
        for(int i=0;i<envelopes.length;i++){
            nums[j]=envelopes[i][1];
            j++;
        }
        return lengthOfLIS(nums);
    }
// the below part is taken exact from problem 300 , read the link above.

    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> al=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int index=binary(al,nums[i]);
            if(index==al.size()){
                al.add(nums[i]);
            }
            else{
                al.set(index,nums[i]);
            }

        }
        return al.size();
    }


    public int binary(ArrayList<Integer> al, int target){
        int s=0;
        int e=al.size();
        int result=al.size();
        while(s<e){
            int mid=(s+e)/2;
            if(al.get(mid)<target){
                s=mid+1;
            }
            else{
                e=mid;
                result=mid;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int[][] arr3 = {{5, 4}, {5, 5}, {5, 6}, {5, 7}};
        int[][] arr2 = {{2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}};
        //System.out.println(new RussianDollEnvelopes().maxEnvelopes(arr1));
        System.out.println(new Test().maxEnvelopes(arr3));
    }
}
