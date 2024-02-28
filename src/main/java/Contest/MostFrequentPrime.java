package Contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

public class MostFrequentPrime {
    HashSet<Integer> notPrime;
    HashSet<Integer> primes;
    public int mostFrequentPrime(int[][] mat) {
        HashMap<Integer,Integer> hm=new HashMap();
        notPrime = new HashSet<>();
        primes = new HashSet<>();
        int val;
        int rows = mat.length,cols = mat[0].length;
        for (int i=0;i< rows;i++){
            for (int j=0;j<cols;j++){
                val=0;
                for (int k=j;k<cols;k++){
                    val*=10;
                    val+=mat[i][k];
                    if(isPrime(val)){
                        hm.put(val,1+hm.getOrDefault(val,0));
                    }
                }
            }
        }

        for (int i=0;i< rows;i++){
            for (int j=cols-1;j>-1;j--){
                val=0;
                for (int k=j;k>-1;k--){
                    val*=10;
                    val+=mat[i][k];
                    if(isPrime(val)){
                        hm.put(val,1+hm.getOrDefault(val,0));
                    }
                }
            }
        }

        for (int j=0;j< cols;j++){
            for (int i=0;i<rows;i++){
                val=0;
                for (int k=i;k<rows;k++){
                    val*=10;
                    val+=mat[k][j];
                    if(isPrime(val)){
                        hm.put(val,1+hm.getOrDefault(val,0));
                    }
                }
            }
        }

        for (int j=0;j< cols;j++){
            for (int i=rows-1;i>-1;i--){
                val=0;
                for (int k=i;k>-1;k--){
                    val*=10;
                    val+=mat[k][j];
                    if(isPrime(val)){
                        hm.put(val,1+hm.getOrDefault(val,0));
                    }
                }
            }
        }
        for (int i=0;i< rows;i++){
            for (int j=0;j<cols;j++){
                val=0;
                int k1=i,k2=j;
                while (k1<rows && k2<cols){
                    val*=10;
                    val+=mat[k1++][k2++];
                    if(isPrime(val)){
                        hm.put(val,1+hm.getOrDefault(val,0));
                    }
                }

                val=0;
                k1=i;k2=j;
                while (k1>-1 && k2>-1){
                    val*=10;
                    val+=mat[k1--][k2--];
                    if(isPrime(val)){
                        hm.put(val,1+hm.getOrDefault(val,0));
                    }
                }

                val=0;
                k1=i;k2=j;
                while (k1>-1 && k2<cols){
                    val*=10;
                    val+=mat[k1--][k2++];
                    if(isPrime(val)){
                        hm.put(val,1+hm.getOrDefault(val,0));
                    }
                }


                val=0;
                k1=i;k2=j;
                while (k1<rows && k2>-1){
                    val*=10;
                    val+=mat[k1++][k2--];
                    if(isPrime(val)){
                        hm.put(val,1+hm.getOrDefault(val,0));
                    }
                }
            }
        }

        int max=0;
        int count=0;
        for (Map.Entry<Integer,Integer> entry: hm.entrySet()){
            if(entry.getValue()>count){
                max=entry.getKey();
                count=entry.getValue();
            } else if (entry.getValue()==count) {
                max=Math.max(max,entry.getKey());
            }
        }
        return max==0?-1:max;
    }

    private boolean isPrime(int n)
    {
        if (n  < 10)
            return false;
        else if(notPrime.contains(n))
            return false;
        else if(primes.contains(n))
            return true;
        else if (n % 2 == 0)
            return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0){
                notPrime.add(n);
                return false;
            }
        }
        primes.add(n);
        return true;
    }

    public static void main(String[] args) {
        int[][] mat1={{1,6},{6,7}};
        System.out.println(new MostFrequentPrime().mostFrequentPrime(mat1));
    }
}
