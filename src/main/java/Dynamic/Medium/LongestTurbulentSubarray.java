package Dynamic.Medium;

import java.util.Arrays;

public class LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] arr) {
        int[] inc=new int[arr.length];
        int[] dec=new int[arr.length];
        Arrays.fill(inc,1);
        Arrays.fill(dec,1);

        int max=1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                inc[i]+=dec[i-1];
                max=Math.max(max,inc[i]);
            }
            else if (arr[i]<arr[i-1]) {
                dec[i]+=inc[i-1];
                max=Math.max(max,dec[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr={9,4,2,10,7,8,8,1,9};
        int[] arr2={4,8,12,16};
        int[] arr3={100};
        System.out.println(new LongestTurbulentSubarray().maxTurbulenceSize(arr));
        System.out.println(new LongestTurbulentSubarray().maxTurbulenceSize(arr2));
        System.out.println(new LongestTurbulentSubarray().maxTurbulenceSize(arr3));
    }
}
