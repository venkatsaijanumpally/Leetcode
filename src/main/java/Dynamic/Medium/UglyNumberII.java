package Dynamic.Medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int[] t=new int[n];
        int t2=0,t3=0,t5=0;
        t[0]=1;
        for(int i=1;i<n;i++){
            int min=Math.min(t[t2]*2,Math.min(t[t3]*3,t[t5]*5));
            t[i]=min;
            if(min==t[t2]*2){
                t2++;
            }
            if(min==t[t3]*3){
                t3++;
            }
            if(min==t[t5]*5) {
                t5++;
            }
        }
        return t[n-1];
    }

    public int nthUglyNumber2(int n) {
        HashSet<Integer> hs=new HashSet<>();
        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        queue.offer(1);
        n--;
        while (n>0){
            int x= queue.poll();
            n--;
            if(!hs.contains(x*2)){
                queue.add(x*2);
                hs.add(x*2);
            }
            if(!hs.contains(x*3)){
                queue.add(x*3);
                hs.add(x*3);
            }
            if(!hs.contains(x*5)){
                queue.add(x*5);
                hs.add(x*5);
            }
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        System.out.println(new UglyNumberII().nthUglyNumber(10));
        System.out.println(new UglyNumberII().nthUglyNumber(1));
    }
}
