package OutsideLeetcode;

import java.util.ArrayList;

public class MinimumClosedInterval {
    public ArrayList<Double[]> mininterval(double[] points){
        ArrayList<Double[]> list=new ArrayList<>();
        if(points.length==0) return list;
        mergeSort(points,0,points.length-1);
        double high=points[0]+1;
        list.add(new Double[]{points[0],high});
        for(int i=1;i< points.length;i++){
            System.out.println(points[i]);
            if(points[i]<=high) continue;
            high=points[i]+1;
            list.add(new Double[]{points[i],high});
        }
        for(Double[] d:list){
            System.out.println(d[0]+" "+d[1]);
        }
        return list;
    }

    private void mergeSort(double[] arr, int low, int high) {
        if(low<high){
            int mid=(low+high)/2;
            mergeSort(arr,low,mid);
            mergeSort(arr,mid+1,high);
            merge(arr,low,mid,high);
        }
    }

    private void merge(double[] arr, int low, int mid, int high) {
        double[] res=new double[high-low+1];
        int i=low,j=mid+1,k=0;
        while (i<=mid && j<=high){
            if(arr[i]<arr[j]){
                res[k]=arr[i];
                i++;
                k++;
            }
            else {
                res[k]=arr[j];
                j++;
                k++;
            }
        }
        while (i<=mid)
            res[k++]=arr[i++];
        while (j<=high)
            res[k++]=arr[j++];
        for(int p=low;p<=high;p++){
            arr[p]=res[p-low];
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumClosedInterval().mininterval(new double[]{6.1,1.5,9.2,0,3.6,6.3,2.5}));
        System.out.println(new MinimumClosedInterval().mininterval(new double[]{2.5,3.3,5,5.9,6.3}));
    }
}
