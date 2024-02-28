package Dynamic.Medium;

import java.util.Arrays;

public class MinimumSidewayJumps {
    public int minSideJumps(int[] obstacles) {
        Integer[][] t = new Integer[3][obstacles.length];
        t[1][0]=0;

        jump(t,obstacles,2,0);
        int column=1;
        for (int i=1;i<obstacles.length-1;i++){
            for (int j=1;j<4;j++){
                if(obstacles[i]==j || t[j-1][i]==null) continue;
                System.out.println("R:"+j+" C:"+i);
                jump(t,obstacles,j,i);
            }
        }
        System.out.println(Arrays.deepToString(t));
        int max=Integer.MAX_VALUE;
        for (int i=0;i<3;i++){
            if(t[i][obstacles.length-1]==null) continue;
            max=Math.min(max,t[i][obstacles.length-1]);
        }
        return max;
    }

    public void jump(Integer[][] t, int[] obstacles, int row, int column){
        if(row==1){
            /*if(obstacles[column+1]!=1 && (t[row][column+1] == null || t[row][column+1] < t[row][column]))
                t[row][column+1]=t[row][column];*/
            if(obstacles[column+1]!=1 && (t[0][column+1] == null || t[0][column+1] > t[0][column]))
                t[0][column+1]=t[0][column];
            if(obstacles[column+1]!=2 && obstacles[column]!=2 && (t[1][column+1] == null || t[1][column+1] > t[0][column]+1))
                t[1][column+1] = t[0][column]+1;
            if(obstacles[column+1]!=3 && obstacles[column]!=3 && (t[2][column+1] == null || t[2][column+1] > t[0][column]+1))
                t[2][column+1] = t[0][column]+1;
        }
        else if(row==2){
            if(obstacles[column+1]!=2 && (t[1][column+1] == null || t[1][column+1] > t[1][column]))
                t[1][column+1]=t[1][column];
            if(obstacles[column+1]!=1 && obstacles[column]!=1 && (t[0][column+1] == null || t[0][column+1] > t[1][column]+1))
                t[0][column+1] = t[1][column]+1;
            if(obstacles[column+1]!=3 && obstacles[column]!=3 && (t[2][column+1] == null || t[2][column+1] > t[1][column]+1))
                t[2][column+1] = t[1][column]+1;
        }
        else if(row==3){
            if(obstacles[column+1]!=3 && (t[2][column+1] == null || t[2][column+1] > t[2][column]))
                t[2][column+1]=t[2][column];
            if(obstacles[column+1]!=2 && obstacles[column]!=2 && (t[1][column+1] == null || t[1][column+1] > t[2][column]+1))
                t[1][column+1] = t[2][column]+1;
            if(obstacles[column+1]!=1 && obstacles[column]!=1 && (t[0][column+1] == null || t[0][column+1] > t[2][column]+1))
                t[0][column+1] = t[2][column]+1;
        }
    }

    public static void main(String[] args) {
        int[] obstacles1={0,1,2,3,0};
        int[] obstacles2={0,1,1,3,3,0};
        int[] obstacles3={0,2,1,0,3,0};
        System.out.println(new MinimumSidewayJumps().minSideJumps(obstacles1));
        System.out.println(new MinimumSidewayJumps().minSideJumps(obstacles2));
        System.out.println(new MinimumSidewayJumps().minSideJumps(obstacles3));
    }
}
