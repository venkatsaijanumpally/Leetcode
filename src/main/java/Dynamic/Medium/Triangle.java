package Dynamic.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Triangle {
    Integer[][] memoize;
    public int minimumTotal(List<List<Integer>> triangle) {
        memoize=new Integer[triangle.size()][triangle.size()];
        return recursive(triangle,0,0);
    }

    //!Optimal Memoized
    private int recursive(List<List<Integer>> triangle, int row, int column) {
        if(row>triangle.size()-1) return 0;
        if(memoize[row][column]!=null) return memoize[row][column];

        int moveRight=recursive(triangle,row+1,column+1)+triangle.get(row).get(column);
        int noMove=recursive(triangle,row+1,column)+triangle.get(row).get(column);
        memoize[row][column]=Math.min(moveRight, noMove);
        return memoize[row][column];
    }


    public static void main(String[] args) {
        List<List<Integer>> arr=new ArrayList<>();
        arr.add(new ArrayList<>(Arrays.asList(2)));
        arr.add(new ArrayList<>(Arrays.asList(3,4)));
        arr.add(new ArrayList<>(Arrays.asList(6,5,7)));
        arr.add(new ArrayList<>(Arrays.asList(4,1,8,3)));
        System.out.println(new Triangle().minimumTotal(arr));
    }
}
