package Dynamic.Easy;

import java.util.ArrayList;

public class ClimbingStairs70 {
    static ArrayList<Integer> arr=new ArrayList<>();
    //!optimal Solution
    public static int climbStairs(int n) {
        for(int i=2;i<=n;i++)
            arr.add(i, arr.get(i-1)+ arr.get(i-2));
        return arr.get(n);
    }


    static int[] result;
    public static int recursiveSolution(int n) {
        result=new int[n+1];
        result[1]=1;
        result[2]=2;
        return recursiveMemoize(n);
    }

    private static int recursiveMemoize(int n) {
        if(result[n]!=0) return result[n];
        int oneStep=recursiveMemoize(n-1);
        int twoStep=recursiveMemoize(n-2);
        result[n]=oneStep+twoStep;
        return result[n];
    }

    public static int recursivePlain(int n){
        if(n==1 || n==2) return n;

        int oneStep=recursivePlain(n-1);
        int twoStep=recursivePlain(n-2);
        return oneStep+twoStep;
    }

    public static void main(String args[]) {
        //int[] arr={-1,0,1,2,-1,-4};
        arr.add(1);
        arr.add(1);
        System.out.println(climbStairs(6));
        System.out.println(arr);

        System.out.println();
        long start= System.currentTimeMillis();
        System.out.println(recursivePlain(36));
        System.out.println("Time:"+(System.currentTimeMillis()-start));

        System.out.println();
        long start1= System.currentTimeMillis();
        System.out.println(recursiveSolution(36));
        System.out.println("Time:"+(System.currentTimeMillis()-start1));
    }
}
