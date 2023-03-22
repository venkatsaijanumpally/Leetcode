package Heap.Medium;

import java.util.*;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list=new ArrayList<>();
        if(x<arr[0]){
            for(int i=0;i<k;i++)
                list.add(arr[i]);
        }
        else if(x>arr[arr.length-1]){
            for (int i=arr.length-k;i<arr.length;i++)
                list.add(arr[i]);
        }
        else {
            int index=binarySearchIndex(arr,x);
            int ptr1=index,ptr2=index;
            while (ptr2-ptr1+1<k){
                int p1=ptr1-1;
                int p2=ptr2+1;
                if(p1>-1 && p2<arr.length){
                    int val=getLeast(p1,p2,arr,x);
                    if(val==1){
                        ptr1=p1;
                    }
                    else ptr2=p2;
                } else if (p1==-1) {
                    ptr2++;
                }
                else ptr1--;
            }
            for (int i=ptr1;i<=ptr2;i++)
                list.add(arr[i]);
        }
        return list;
    }

    private int getLeast(int p1, int p2, int[] arr,int x) {
        int v1=Math.abs(arr[p1]-x);
        int v2=Math.abs(arr[p2]-x);
        if (v1>v2) {
            return 2;
        }
        else return 1;
    }


    private int binarySearchIndex(int[] arr, int x) {
        if(arr.length==1)
            return 0;
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if(arr[mid]==x)
                return mid;
            else if(arr[mid]<x)
                low=mid+1;
            else
                high=mid-1;
        }
        if(Math.abs(arr[low]-x)==Math.abs(arr[high]-x))
            return high;
        else if(Math.abs(arr[low]-x)<Math.abs(arr[high]-x))
            return low;
        else return high;

        /*return Math.abs(arr[low]-x)<Math.abs(arr[high]-x)?low:high;*/
    }

    public static void main(String[] args) {
        int[] arr1={1,2,3,4,5};
        int[] arr2={1};
        int[] arr3={1,2,3};
        int[] arr4={10,10,11,11};
        int[] arr5={0,0,0,1,3,5,6,7,8,8};
        System.out.println(new FindKClosestElements().findClosestElements(arr5,2,2));
        System.out.println(new FindKClosestElements().findClosestElements(arr1,4,3));
        System.out.println(new FindKClosestElements().findClosestElements(arr1,3,-1));
        System.out.println(new FindKClosestElements().findClosestElements(arr2,1,1));
        System.out.println(new FindKClosestElements().findClosestElements(arr3,2,2));
        System.out.println(new FindKClosestElements().findClosestElements(arr3,3,2));
        System.out.println(new FindKClosestElements().findClosestElements(arr4,2,10));
    }
}
