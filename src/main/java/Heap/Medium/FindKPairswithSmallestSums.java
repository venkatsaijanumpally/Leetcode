package Heap.Medium;

import java.util.*;

public class FindKPairswithSmallestSums {
    /*public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer[]> queue=new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return (o1[0]+o1[1])-(o2[0]+o2[1]);
            }
        });

        List<List<Integer>> result=new ArrayList<>();
        int i=0,j=0;
        while (k>0){
            if(nums1[i]<=nums2[j]){
                for (int p=j;p<nums2.length;p++){
                    queue.add(new Integer[]{nums1[i],nums2[p]});
                }
                i++;
            }
            else {
                for (int p=i;p<nums1.length;p++){
                    queue.add(new Integer[]{nums1[p],nums2[j]});
                }
                j++;
            }

            if(i==nums1.length||j==nums2.length) break;

            int sum=nums1[i]+nums2[j];
            Integer[] arr= queue.peek();
            while (k>0 && sum< arr[0]+arr[1]){
                result.add(List.of(queue.poll()));
                k--;
            }
        }

        while (k>0){
            result.add(List.of(queue.poll()));
            k--;
        }
        return result;
    }*/

    /*public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer[]> queue=new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return (o1[0]+o1[1])-(o2[0]+o2[1]);
            }
        });

        List<List<Integer>> result=new ArrayList<>();
        int i=0,j=0;
        while (k>0){
            if(nums1[i]==nums2[j]){
                for (int p=0;p<=j;p++){
                    queue.add(new Integer[]{nums1[i],nums2[p]});
                }
                for (int p=0;p<i;p++){
                    queue.add(new Integer[]{nums1[p],nums2[j]});
                }
                i++;
                j++;
            }
            else if(nums1[i]<nums2[j]){
                for (int p=0;p<j;p++){
                    queue.add(new Integer[]{nums1[i],nums2[p]});
                }
                i++;
            }
            else {
                for (int p=0;p<i;p++){
                    queue.add(new Integer[]{nums1[p],nums2[j]});
                }
                j++;
            }

            if(i==nums1.length||j==nums2.length) break;

            int sum=nums1[i]+nums2[j];
            Integer[] arr= queue.peek();
            while (!queue.isEmpty() && k>0 && sum< arr[0]+arr[1]){
                result.add(List.of(queue.poll()));
                k--;
            }
        }

        while (queue.size()<k){
            if(i==nums1.length){
                for (int p=0;p<nums1.length;p++){
                    queue.add(new Integer[]{nums1[p],nums2[j]});
                }
                j++;
            }
            else if(j==nums2.length){
                for (int p=0;p<nums2.length;p++){
                    queue.add(new Integer[]{nums1[i],nums2[p]});
                }
                i++;
            }
        }

        while (k>0){
            result.add(List.of(queue.poll()));
            k--;
        }
        return result;
    }*/

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer[]> queue=new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return (nums1[o1[0]]+nums2[o1[1]])-(nums1[o2[0]]+nums2[o2[1]]);
            }
        });

        for(int i=0;i<nums1.length;i++){
            queue.add(new Integer[]{i,0});
        }

        List<List<Integer>> result=new ArrayList<>();
        while (k>0){
            Integer[] indexes = queue.poll();
            result.add(List.of(new Integer[]{nums1[indexes[0]], nums2[indexes[1]]}));
            k--;
            if(indexes[1]==nums2.length-1)
                continue;
            indexes[1]++;
            queue.add(indexes);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1={-10,-4,0,0,6};
        int[] nums2={3,5,6,7,8,100};
        int[] nums3={1,2,4,5,6};
        int[] nums21={3,5,7,9};
        System.out.println(new FindKPairswithSmallestSums().kSmallestPairs(nums3,nums21,20));
        //System.out.println(new FindKPairswithSmallestSums().kSmallestPairs(nums1,nums2,10));
    }
}
