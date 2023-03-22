package Dynamic.Medium;

import java.util.HashMap;

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n==1) return 1;
        HashMap<Integer,Boolean> map=new HashMap<>();
        map.put(1,true);
        int count=1;
        int num=1;
        while (count<n){
            ++num;
            for(int prime: primes){
                if(num%prime==0 && map.get(num/prime)!=null){
                    map.put(num,true);
                    count++;
                    break;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int[] primes1={2,7,13,19};
        System.out.println(new SuperUglyNumber().nthSuperUglyNumber(12,primes1));
        System.out.println(new SuperUglyNumber().nthSuperUglyNumber(1,primes1));
    }
}
