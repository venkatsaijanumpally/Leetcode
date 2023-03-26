package Heap.Medium;

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] capacityAtLocation=new int[1001];
        for (int i=0;i< trips.length;i++){
            if(trips[i][0]>capacity) return false;
            capacityAtLocation[trips[i][1]]+=trips[i][0];
            capacityAtLocation[trips[i][2]]+=-trips[i][0];
        }
        int sum=0;
        for(int i=0;i<=1000;i++){
            sum+=capacityAtLocation[i];
            if(sum>capacity) return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
