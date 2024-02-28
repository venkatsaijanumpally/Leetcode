package Dynamic.Medium;

import java.util.HashMap;

public class LengthofLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] arr) {
        int[][] t=new int[arr.length][arr.length];
        HashMap<Integer,Integer> hm=new HashMap<>();
        int max=2;
        for (int j=0;j<arr.length;j++){
            hm.put(arr[j],j);
            for (int i=0;i<j;i++){
                int k=hm.getOrDefault(arr[j]-arr[i],-1);
                t[i][j]=(k>=0 && arr[j]-arr[i]<arr[i])? t[k][i]+1:2;
                max=Math.max(max,t[i][j]);
            }
        }
        if(max==2)
            return 0;
        return max;
    }

    public static void main(String[] args) {
        int[] arr1={1,2,3,4,5,6,7,8};
        System.out.println(new LengthofLongestFibonacciSubsequence().lenLongestFibSubseq(arr1));
    }
}
