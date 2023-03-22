package Dynamic.Medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class NonoverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)  return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int end=intervals[0][1];
        int count=1;
        for(int i=1;i<intervals.length;i++){
            if(end<=intervals[i][0]){
                end=intervals[i][1];
                count++;
            }
        }
        return intervals.length-count;
    }

    public static void main(String[] args) {
        int[][] intervals1= new int[][]{{1,2},{2,3},{3,4},{1,3}};
        System.out.println(new NonoverlappingIntervals().eraseOverlapIntervals(intervals1));
    }
}
