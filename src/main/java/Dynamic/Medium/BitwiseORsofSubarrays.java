package Dynamic.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BitwiseORsofSubarrays {
    int count=0;
    HashSet<Integer> hs=new HashSet<>();
    int[][] memoize;
    public int subarrayBitwiseORs(int[] arr) {
        /*memoize=new int[arr.length][arr.length];
        memoize[arr.length-1][0]=arr[arr.length-1];
        hs.add(memoize[arr.length-1][0]);
        recursive(arr,arr.length-2);*/
        return loop(arr);
        //return hs.size();
    }

    /*private int recursive(int[] arr, int i) {
        int rec=recursive(arr,i+1);
        int xor=arr[i]^rec;
        hs.add(xor);
        //hs.add(rec);
        return xor;
    }*/
    private void recursive(int[] arr, int index) {
        if(index<0) return;
        int xor=arr[index];
        hs.add(xor);
        memoize[index][0]=xor;
        int[] res=memoize[index+1];
        for(int i=1;i<arr.length-index;i++){
            xor=xor|res[i-1];
            hs.add(xor);
            memoize[index][i]=xor;
        }
        recursive(arr,index-1);
    }

    //!Optimal
    private int loop(int[] arr){
        HashSet<Integer> result=new HashSet<>();
        HashSet<Integer> prev=new HashSet<>();
        for(int i=0;i<arr.length;i++){
            HashSet<Integer> next=new HashSet<>();
            next.add(arr[i]);
            result.add(arr[i]);
            for(int val:prev){
                int or=val|arr[i];
                next.add(or);
                result.add(or);
            }
            prev=next;
        }
        return result.size();
    }
    public static void main(String[] args) {
        int[] arr={1,1,2};
        int[] arr1={1,2,4};
        HashSet<Integer> hs1=new HashSet<>();
        HashSet<Integer> hs2=new HashSet<>();
        HashSet<Integer> hs3=new HashSet<>();
        hs1.add(1);
        hs1.add(10);
        hs1.add(2);
        hs1.add(3);
        hs2.add(2);
        hs2.add(3);
        hs2.add(4);
        hs2.add(5);
        hs3.addAll(hs1=hs2);

        for(Integer i: hs3)
            System.out.print(i+" ");


        System.out.println();
        System.out.println(new BitwiseORsofSubarrays().subarrayBitwiseORs(arr));
        System.out.println(new BitwiseORsofSubarrays().subarrayBitwiseORs(arr1));
    }
}
