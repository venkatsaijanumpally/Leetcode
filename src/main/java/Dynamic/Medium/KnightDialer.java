package Dynamic.Medium;

import java.util.Arrays;

public class KnightDialer {
    public int knightDialer(int n) {
        long[][] t=new long[n+1][10];
        int[][] possibleMovement=new int[][]{{4,6},{8,6},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{4,2}};
        Arrays.fill(t[1],1);
        for(int i=2;i<=n;i++){
            for(int num=0;num<10;num++){
                for(int nextMove:possibleMovement[num]){
                    t[i][num]+=t[i-1][nextMove];
                }
                t[i][num]%=1000000007;
            }
        }

        long sum=0;
        for(int i=0;i<10;i++)
            sum+=t[n][i];
        return (int) (sum%1000000007);
    }

    public static void main(String[] args) {
        System.out.println(new KnightDialer().knightDialer(1));
        System.out.println(new KnightDialer().knightDialer(2));
        System.out.println(new KnightDialer().knightDialer(3));
        System.out.println(new KnightDialer().knightDialer(3131));
    }
}
