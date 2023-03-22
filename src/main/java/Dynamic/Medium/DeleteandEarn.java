package Dynamic.Medium;

import java.util.HashMap;

public class DeleteandEarn {
    public int deleteAndEarn(int[] nums) {
        int[] arr=new int[10001];
        for(int i=0;i<nums.length;i++)
            arr[nums[i]]+=nums[i];

        for(int i=9998;i>=0;i--){
            arr[i]=Math.max(arr[i+1],arr[i+2]+arr[i]);
        }
        return arr[0];
    }

    Integer[] memoize=new Integer[100001];
    public int recursionCaller(int[] nums){
        int[] arr=new int[10001];
        for(int i=0;i<nums.length;i++)
            arr[nums[i]]+=nums[i];
        return recursiveMemoize(arr,0,0);
    }

    public int recursiveMemoize(int[] numsSum, int index, int sum){
        if(index>10000) return sum;
        if(memoize[index]!=null) return memoize[index];

        int take=recursiveMemoize(numsSum,index+2,sum)+numsSum[index];
        int skip=recursiveMemoize(numsSum,index+1,sum);
        memoize[index]=Math.max(take,skip);
        return memoize[index];
    }

    public static void main(String[] args) {
        int[] arr={2,2,3,3,3,4};
        System.out.println(new DeleteandEarn().deleteAndEarn(arr));


        System.out.println();
        System.out.println(new DeleteandEarn().recursionCaller(arr));
    }
}
