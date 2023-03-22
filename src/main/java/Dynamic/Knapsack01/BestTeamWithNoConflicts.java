package Dynamic.Knapsack01;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

public class BestTeamWithNoConflicts {
    public  void mergesort(int[] score, int[] ages,int low, int high){
        if(low>=high)return;
        int mid=low+(high-low)/2;

        mergesort(score,ages,low,mid);
        mergesort(score,ages,mid+1,high);
        merge(score,ages,low,high,mid);
    }

    private void merge(int[] score, int[] ages, int low, int high,int mid) {
        if(low>high)
            return;
        int[] resultages=new int[high-low+1];
        int[] resultscore=new int[high-low+1];
        int k=0;
        int p1=low,p2=mid+1;
        while (p1<=mid&&p2<=high){
            if(ages[p1]==ages[p2]){
                resultages[k]=ages[p1];
                if(score[p1]<score[p2]) resultscore[k++]=score[p1++];
                else resultscore[k++]=score[p2++];
            }
            else if(ages[p1]<ages[p2]){
                resultages[k]=ages[p1];
                resultscore[k++]=score[p1++];
            }
            else {
                resultages[k] = ages[p2];
                resultscore[k++]=score[p2++];
            }
        }
        while (p1<=mid){
            resultages[k]=ages[p1];
            resultscore[k++]=score[p1++];
        }
        while (p2<=high){
            resultages[k] = ages[p2];
            resultscore[k++]=score[p2++];
        }

        for(int i=low;i<=high;i++){
            ages[i]=resultages[i-low];
            score[i]=resultscore[i-low];
        }
    }

    Integer[][] memoize;
    public int bestTeamScore(int[] scores, int[] ages) {
        /*Integer[] sortedAges= ArrayUtils.toObject(ages);
        Arrays.sort(sortedAges, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });*/
        /*mergesort(scores,ages,0,ages.length-1);
        memoize=new Integer[ages.length+1][ages.length+1];
        return recursive(scores,ages,0,-1);*/


        /*int[][] arr=new int[ages.length+1][2];
        for(int i=0;i<ages.length;i++){
            arr[i+1]= new int[]{ages[i], scores[i]};
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]-o2[0]==0) return o1[1]-o2[1];
                return o1[0]-o2[0];
            }
        });

        memoize=new Integer[ages.length+1][ages.length+1];
        return recursive2(arr,1,0);*/



        int[][] arr=new int[ages.length][2];
        for(int i=0;i<ages.length;i++){
            arr[i]= new int[]{ages[i], scores[i]};
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]-o2[0]==0) return o1[1]-o2[1];
                return o1[0]-o2[0];
            }
        });
        int[] memoize=new int[ages.length];
        memoize[0]=arr[0][1];
        int ans=memoize[0];
        for(int i=1;i< ages.length;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(arr[j][1]<=arr[i][1] || arr[j][0]==arr[i][0])
                    max=Math.max(memoize[j],max);
            }
            memoize[i]=max+arr[i][1];
            ans=Math.max(memoize[i],ans);
        }
        return ans;
    }

    private int recursive(int[] scores, int[] ages, int index,int prevIndex) {
        if(index==ages.length) return 0;
        if(memoize[index][prevIndex+1]!=null) return memoize[index][prevIndex+1];

        if(prevIndex==-1 || scores[prevIndex]<=scores[index]){
            return memoize[index][prevIndex+1]=Math.max(scores[index]+recursive(scores,ages,index+1,index),
                    recursive(scores,ages,index+1,prevIndex));
        }
        else return memoize[index][prevIndex+1]=recursive(scores,ages,index+1,prevIndex);
    }

    private int recursive2(int[][] arr, int index,int prevIndex) {
        if(index==arr.length) return 0;
        if(memoize[index][prevIndex]!=null) return memoize[index][prevIndex];

        if(arr[prevIndex][1]<=arr[index][1]){
            return memoize[index][prevIndex]=Math.max(arr[index][1]+recursive2(arr,index+1,index),
                    recursive2(arr,index+1,prevIndex));
        }
        else return memoize[index][prevIndex]=recursive2(arr,index+1,prevIndex);
    }

    public static void main(String[] args) {
        int[] age={1,2,3,4,5};
        int[] score={1,3,5,10,15};
        int[] age1={2,1,2,1};
        int[] score1={4,5,6,5};
        int[] score2={1,2,3,5};
        int[] age2={8,9,10,1};
        System.out.println(new BestTeamWithNoConflicts().bestTeamScore(score,age));
        System.out.println(new BestTeamWithNoConflicts().bestTeamScore(score1,age1));
        System.out.println(new BestTeamWithNoConflicts().bestTeamScore(score2,age2));
    }
}
