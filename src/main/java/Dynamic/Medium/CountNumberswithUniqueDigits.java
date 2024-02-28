package Dynamic.Medium;

public class CountNumberswithUniqueDigits {
    int[] t;
    public int countNumbersWithUniqueDigits(int n) {
        if(n==0) return 1;
        t=new int[n+1];

        t[1] = 10;

        int val = 9*9;
        int c = 8;
        for (int i=2;i<=n;i++){
            t[i] = val + t[i-1];
            val *= c--;
        }
        return t[n];
    }

    public static void main(String[] args) {

    }
}
