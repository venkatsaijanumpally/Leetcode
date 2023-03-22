package OutsideLeetcode;

import java.util.HashMap;

public class minimumDistanceClients {

    int[] consultant=new int[]{0,0,0};
    int[][] dist;
    int k;
    HashMap<String,Integer> hm;
    public int minDistanceGreedy(int[][] dist, int[] requests, int k){
        this.k=k;
        this.dist=dist;
        return minDistanceGreedy(requests);
    }

    public int minDistance(int[][] dist, int[] requests, int k){
        this.k=k;
        this.dist=dist;
        hm=new HashMap<>();
        int min=minDistanceMemoized(requests,0,0,0,0);
        return min;
    }

    private int minDistanceMemoized(int[] requests, int r, int c1, int c2, int c3) {
        if(r==requests.length) return 0;
        StringBuilder s=new StringBuilder().append(r).append('*').append(c1).append('*').append(c2).append('*').append(c3);
        if(hm.containsKey(s.toString()))
            return hm.get(s.toString());

        if(requests[r]==c1 || requests[r]==c2 || requests[r]==c3) {
            hm.put(s.toString(), minDistanceMemoized(requests, r + 1, c1, c2, c3));
            return hm.get(s.toString());
        }
        int min=Math.min(
                dist[c1][requests[r]]+minDistanceMemoized(requests,r+1, requests[r], c2, c3),
                Math.min(
                        dist[c2][requests[r]]+minDistanceMemoized(requests,r+1, c1, requests[r], c3),
                        dist[c3][requests[r]]+minDistanceMemoized(requests,r+1, c1, c2, requests[r])
                )
            );


        hm.put(s.toString(), min);
        return hm.get(s.toString());
    }

    private int minDistanceGreedy(int[] requests) {
        int totalDistance=0;
        for(int i=0;i<requests.length;i++){
            totalDistance+=processRequest(requests,i);
        }
        return totalDistance;
    }

    private int processRequest(int[] requests, int i) {
        int min=Integer.MAX_VALUE;
        int minIndex=-1;
        int j;
        for(j=0;j<3;j++){
            if(min>dist[consultant[j]][requests[i]]){
                min=Math.min(min, dist[consultant[j]][requests[i]]);
                minIndex=j;
            }
        }
        consultant[minIndex]=requests[i];
        return min;
    }

    public static void main(String[] args) {
        int[][] dist=new int[][]{{0,3,4,5,6},{3,0,1,2,3},{4,1,0,1,2},{5,2,1,0,1},{6,3,2,1,0}};
        int[] request=new int[]{1,2,3,4,1,2,0};
        int[][] dist2=new int[][]{{0,1,2,3,4,5},{1,0,1,2,3,4},{2,1,0,1,2,3},{3,2,1,0,1,2},{4,3,2,1,0,1},{5,4,3,2,1,0}};
        int[] request2=new int[]{5,2,0};
        //Example for greedy failure below
        int[][] dist3=new int[][]{{0,1,2,3,4,5,6,7,8,9,10,11},{1,0,1,2,3,4,5,6,7,8,9,10},{2,1,0,1,2,3,4,5,6,7,8,9},{3,2,1,0,1,2,3,4,5,6,7,8},{4,3,2,1,0,1,2,3,4,5,6,7},{5,4,3,2,1,0,1,2,3,4,5,6},{6,5,4,3,2,1,0,1,2,3,4,5},{7,6,5,4,3,2,1,0,1,2,3,4},{8,7,6,5,4,3,2,1,0,1,2,3},{9,8,7,6,5,4,3,2,1,0,1,2},{10,9,8,7,6,5,4,3,2,1,0,1},{11,10,9,8,7,6,5,4,3,2,1,0}};
        int[] request3=new int[]{11,5,2,0};
        System.out.println(new minimumDistanceClients().minDistanceGreedy(dist,request,5));
        System.out.println(new minimumDistanceClients().minDistance(dist,request,5));
        System.out.println(new minimumDistanceClients().minDistanceGreedy(dist3,request3,12));
        System.out.println(new minimumDistanceClients().minDistance(dist3,request3,12));
    }
}
