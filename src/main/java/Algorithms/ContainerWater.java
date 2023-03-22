package Algorithms;

import java.util.Arrays;

public class ContainerWater {
    public static int maxArea(int[] height) {
        int[] start = new int[height.length];
        int[] end = new int[height.length];
        start[0]=height[0];
        end[height.length-1]=height[height.length-1];
        for(int i=1;i<height.length;i++){
            if(height[i]>start[i-1])
                start[i]=height[i];
            else
                start[i]=start[i-1];
        }
        for(int i=height.length-2;i>=0;i--){
            if(height[i]>end[i+1])
                end[i]=height[i];
            else
                end[i]=end[i+1];
        }
        System.out.println(Arrays.toString(start));
        System.out.println(Arrays.toString(end));

        int startp=0,endp=height.length-1;
        int area=0;
        while(startp<endp){
            area=Math.max(area,Math.min(height[startp],height[endp])*(endp-startp));
            if(height[startp]<height[endp])
                startp++;
            else
                endp--;
        }
        return area;
    }

    public static void main(String args[]) {
        int[] arr={1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(arr));
    }
}
