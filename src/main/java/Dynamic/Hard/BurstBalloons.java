package Dynamic.Hard;

import java.util.*;

public class BurstBalloons {
    HashMap<Integer,HashMap<ArrayEntry,Integer>> map = new HashMap<>();
    /*public int maxCoins(int[] nums) {
        ArrayEntry initialArray = new ArrayEntry(nums);
        map.put(nums.length, new HashMap<>());
        map.get(nums.length).put(initialArray,0);

        for (int i= nums.length; i>0; i--){
            map.put(i-1,new HashMap<>());
            for (Map.Entry<ArrayEntry,Integer> entry: map.get(i).entrySet()){
                int[] array = entry.getKey().array;
                int cost = entry.getValue();
                int val;
                for (int j=0;j< array.length;j++){
                    int left,right;
                    left = j==0?1:array[j-1];
                    right = j== array.length-1?1:array[j+1];
                    val = left*array[j]*right;
                    int[] temp = new int[i-1];
                    copy(temp,array,j);
                    ArrayEntry arrayEntry = new ArrayEntry(temp);
                    map.get(i-1).put(arrayEntry,Math.max(map.get(i-1).getOrDefault(arrayEntry,0), val+cost));
                }
            }
        }
        return map.get(0).values().iterator().next();
    }*/


    //Memoization
    Integer[][] t;
    /*public int maxCoins(int[] nums){
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        for (int i: nums)
            numbers.add(i);
        numbers.add(1);
        t = new Integer[numbers.size()][numbers.size()];
        return solve(numbers,1,numbers.size()-2);
    }*/

    private int solve(List<Integer> numbers, int start, int end) {
        if(start>end)
            return 0;
        else if (start==end) {
            int temp = numbers.get(start);
            if(start-1>0)
                temp *= numbers.get(start-1);
            if(start+1<numbers.size())
                temp *= numbers.get(start+1);
            return temp;
        }

        if(t[start][end]!=null) return t[start][end];
        int val=0;
        for (int i=start;i<=end;i++){
            int left = solve(numbers,start,i-1);
            int right = solve(numbers,i+1,end);
            val = Math.max(numbers.get(start-1)*numbers.get(i)*numbers.get(end+1) + left + right,val);
        }
        return t[start][end] = val;
    }

    private void copy(int[] temp, int[] array, int j) {
        int tempIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != j) {
                temp[tempIndex] = array[i];
                tempIndex++;
            }
        }
    }


    //DP
    public int maxCoins(int[] nums){
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        for (int i: nums)
            numbers.add(i);
        numbers.add(1);
        t = new Integer[numbers.size()][numbers.size()];

        for (int i=0;i<numbers.size();i++){
            t[i][0]=0;
        }
        for (int i=1;i<numbers.size()-1;i++){
            int j=i-1;
            t[i][j]=0;
        }
        t[numbers.size()-1][numbers.size()-2]=0;
        //Arrays.fill(t[0],0);
        //Arrays.fill(t[numbers.size()-1],0);


        for (int len=1;len<numbers.size()-1;len++){
            for (int  start=1;start<=numbers.size()-1-len;start++){
                int end = start+len-1;
                if (start==end) {
                    int temp = numbers.get(start);
                    if(start-1>0)
                        temp *= numbers.get(start-1);
                    if(start+1<numbers.size())
                        temp *= numbers.get(start+1);
                    t[start][end] = temp;
                    continue;
                }

                int val=0;
                for (int i=start;i<=end;i++){
                    int left = t[start][i-1];
                    int right = t[i+1][end];
                    val = Math.max(numbers.get(start-1)*numbers.get(i)*numbers.get(end+1) + left + right,val);
                }
                t[start][end] = val;
            }
        }
        return t[1][numbers.size()-2];
    }
    public static void main(String[] args) {
        int[] nums1={3,1,5,8};
        System.out.println(new BurstBalloons().maxCoins(nums1));
    }
}
