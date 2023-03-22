package Heap.Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestPrimeFraction {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        /*Queue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (int)(double)(o2[0]/o2[1])-(int)(double)(o1[0]/o1[1]);
            }
        });*/
        Queue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Double.compare((double) o2[0]/o2[1],(double) o1[0]/o1[1]);
            }
        });
        for (int i=0;i< arr.length-1;i++){
            for (int j=i+1;j<arr.length;j++){
                queue.add(new int[]{arr[i],arr[j]});
                if(queue.size()>k)
                    queue.poll();
            }
        }
        return queue.poll();
    }

    //!Optimal
    // https://leetcode.com/problems/k-th-smallest-prime-fraction/solutions/862410/c-binary-search-short-and-easy-faster-than-99/
    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {
        double low=0d,high=1d;
        int n= arr.length;
        while (low<high){
            double mid=(low+high)/2;
            int count=0,j=1;
            double max_frac=0d;
            int p=0,q=n;
            for(int i=0;i< n-1;i++){
                while (j<n && ((double)arr[i]/arr[j]>mid))
                    j++;
                count+=n-j;
                if(j==n)
                    break;
                double frac=(double) arr[i]/arr[j];
                if(max_frac<frac){
                    max_frac=frac;
                    p=i;
                    q=j;
                }
            }
            if(count==k)
                return new int[]{arr[p],arr[q]};
            else if(count>k){
                high=mid;
            }
            else low=mid;
        }
        return new int[]{};
    }


    public static void main(String[] args) {
        int[] arr={1,2,3,5};
        int[] arr2={2,3,5,7};
        System.out.println(Arrays.toString(new KthSmallestPrimeFraction().kthSmallestPrimeFraction(arr, 3)));
        System.out.println(Arrays.toString(new KthSmallestPrimeFraction().kthSmallestPrimeFraction2(arr2, 3)));
    }
}
