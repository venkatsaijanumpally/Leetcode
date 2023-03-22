import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {
    public static void main(String[] args) {
        int matrix[][] = { { 1,4 },
                { 2,3 }};

        // Sort this matrix by 3rd Column
        int col = 3;
        matrix=merge(matrix);

        // Display the sorted Matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                /*if(o1[0]==o2[0])
                    return o1[1]>o2[1]?1:-1;*/
                return o1[0]-o2[0];
            }
        });
        int[][] temp=new int[intervals.length][intervals[0].length];
        int size=1;
        temp[0]=intervals[0];
        /*for (int i=0;i<intervals.length;i++)
            temp[i][0]=intervals[i][0];*/
        for(int i=1;i<intervals.length;i++){
            if(temp[size-1][1]>=intervals[i][0]){
                /*if(temp[size-1][1]<intervals[i][1])
                    temp[size-1][1]=intervals[i][1];
                else
                    continue;*/
                temp[size-1][1]=intervals[i][1]>temp[size-1][1]?intervals[i][1]:temp[size-1][1];
            }
            else {
                temp[size][0]=intervals[i][0];
                temp[size][1]=intervals[i][1];
                ++size;
            }
        }
        if(size==intervals.length)
            return temp;
        int[][] result=new int[size][2];
        System.arraycopy(temp, 0, result, 0, size);
        return result;
    }
}
