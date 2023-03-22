package OutsideLeetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FictionReader {
    public int maximumEnjoyment(int[][] values){
        //return recursive(values, 0, 0);
        return iterative(values);
    }

    private int iterative(int[][] values){
        int n=values.length;
        int maxTime=values[n-1][2];
        int[][] t=new int[maxTime+1][n+1];
        for(int i=1;i<=maxTime;i++){
            for(int j=1;j<=n;j++){
                //if(i>=values[j-1][1] && values[j-1][1]<=values[j-1][2])
                if(i>=values[j-1][1] && i<=values[j-1][2])
                    //t[i][j]=Math.max(values[j-1][0]+t[i-values[j-1][1]][j-1],t[i][j-1]);
                    t[i][j]=Math.max(values[j-1][0]+t[i-values[j-1][1]][j-1],t[i][j-1]);
                else if(i>values[j-1][2])
                    t[i][j]=t[values[j-1][2]][j];
                else t[i][j]=t[i][j-1];
            }
        }

        //Print Sequence
        int i=maxTime,j=n;
        List<Integer> subset=new ArrayList<>();
        System.out.print("Elements: ");
        while (i>0 && j>0){
            if(t[i][j]>t[i][j-1]){
                //System.out.print(j+" ");
                subset.add(j);
                i-=values[j-1][1];
                j--;
            }
            else if(i>values[j-1][2])
                i=values[j-1][2];
            else j--;
        }
        System.out.println(subset);
        System.out.println();

        return t[maxTime][n];
    }

    private int iterative3(int[][] values){
        int n=values.length;
        int maxTime=values[n-1][2];
        int[][] t=new int[maxTime+1][n+1];
        for(int i=1;i<=maxTime;i++){
            for(int j=1;j<=n;j++){
                if(i>=values[j-1][1] && i<=values[j-1][2])
                    t[i][j]=Math.max(values[j-1][0]+t[i-values[j-1][1]][j-1],t[i][j-1]);
                else if(i>values[j-1][2])
                    t[i][j]=t[values[j-1][2]][j];
                else t[i][j]=t[i][j-1];
            }
        }

        //Print Sequence
        int i=maxTime,j=n;
        List<Integer> subset=new ArrayList<>();
        System.out.print("Elements: ");
        while (i>0 && j>0){
            if(t[i][j]>t[i][j-1]){
                subset.add(j);
                i-=values[j-1][1];
                j--;
            }
            else if(i>values[j-1][2])
                i=values[j-1][2];
            else j--;
        }
        System.out.println();

        return t[maxTime][n];
    }

    //To test Failure Scenario
    private int iterative2(int[][] values){
        int n=values.length;
        int maxTime=values[n-1][2];
        int[][] t=new int[maxTime+1][n+1];
        for(int i=1;i<=maxTime;i++){
            for(int j=1;j<=n;j++){
                if(i>=values[j-1][1])
                    t[i][j]=Math.max(values[j-1][0]+t[i-values[j-1][1]][j-1],t[i][j-1]);
                else t[i][j]=t[i][j-1];
            }
        }
        return t[maxTime][n];
    }

    //Integral Knapsack
    private int recursive(int[][] values, int i, int time) {
        if(i>=values.length) return 0;

        if(values[i][2]>=time+values[i][1])
            return Math.max(values[i][0]+recursive(values,i+1,time+values[i][1]), recursive(values,i+1,time));
        return recursive(values,i+1,time);
    }

    //Integral Knapsack
    private int recursive2(int[][] values, int i, int time) {
        if(i==-1) return 0;

        if(time<=values[i][2] && time>=values[i][1])
            return Math.max(values[i][0]+recursive2(values,i-1,time-values[i][1]), recursive2(values,i-1,time));
        else if(time>values[i][2])
            return recursive2(values,i,values[i][2]);
        return recursive2(values,i-1,time);
    }

    public static void main(String[] args) {
        int[][] arr={{120,3,2},{110,3,4},{140,6,10},{160,7,10}};
        int[][] arr2={{120,3,2},{110,3,4},{140,6,10},{160,7,11}};
        int[][] arr3={{120,3,2},{110,3,3}};
        int[][] arr4={{120,3,1}};
        int[][] arr5={{120,3,1},{20,4,5}};
        System.out.println(new FictionReader().maximumEnjoyment(arr));
        System.out.println(new FictionReader().maximumEnjoyment(arr2));
        System.out.println(new FictionReader().maximumEnjoyment(arr3));
        System.out.println(new FictionReader().maximumEnjoyment(arr4));
        System.out.println(new FictionReader().maximumEnjoyment(arr5));
        System.out.println(new FictionReader().iterative2(arr5));//This is to check a wrong condition

        System.out.println();
        int[][] arr6={{80,2,4},{90,3,5},{100,2,6},{140,5,8}};
        int[][] arr7={{80,2,4},{90,3,5},{100,2,6},{80,5,8},{100,4,9}};
        int[][] arr8={{120,3,3},{110,3,4},{150,2,10}};
        int[][] arr9={{120,3,3},{110,3,4},{130,4,9},{150,2,10}};
        int[][] arr10={{120,3,3},{200,8,7}};
        int[][] arr11={{120,3,2},{200,8,7},{100,9,8}};
        System.out.println(new FictionReader().maximumEnjoyment(arr6));
        System.out.println(new FictionReader().maximumEnjoyment(arr7));
        System.out.println(new FictionReader().maximumEnjoyment(arr8));
        System.out.println(new FictionReader().maximumEnjoyment(arr9));
        System.out.println(new FictionReader().maximumEnjoyment(arr10));
        System.out.println(new FictionReader().maximumEnjoyment(arr11));
        System.out.println(new FictionReader().recursive2(arr6,3,8));
        System.out.println(new FictionReader().recursive2(arr7,4,9));
        System.out.println(new FictionReader().recursive2(arr8,2,10));
        System.out.println(new FictionReader().recursive2(arr9,3,10));

        System.out.println();
        System.out.println("Iter 3");
        System.out.println(new FictionReader().iterative3(arr));
        System.out.println(new FictionReader().iterative3(arr2));
        System.out.println(new FictionReader().iterative3(arr3));
        System.out.println(new FictionReader().iterative3(arr4));
        System.out.println(new FictionReader().iterative3(arr5));
        System.out.println(new FictionReader().iterative3(arr6));
        System.out.println(new FictionReader().iterative3(arr7));
        System.out.println(new FictionReader().iterative3(arr8));
        System.out.println(new FictionReader().iterative3(arr9));
        System.out.println(new FictionReader().iterative3(arr10));
    }
}
