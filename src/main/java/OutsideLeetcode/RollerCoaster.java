package OutsideLeetcode;

import java.util.ArrayList;
import java.util.List;

public class RollerCoaster {

    int[][] parameters;
    int k,n;
    Integer[][][] memoize;
    List<Integer> result;
    List<Integer> path;
    int maxExcitement=0;
    Integer[][] loopMemoize;
    Integer[][] loopIndex;
    public int maxExcitement(int k, int n, int[][] parameters){
        this.parameters=parameters;
        this.k=k;this.n=n;
        memoize=new Integer[k][n][101];
        result=new ArrayList<>();
        path=new ArrayList<>();
        /*int x=maxExcitementMemoize(0,0, 0);
        return x;*/

        loopMemoize=new Integer[n+1][101];
        loopIndex=new Integer[n+1][101];
        int x=maxExcitementLoop(n, 0);
        /*for(int i=1;i<=n;i++){
            for(int j=0;j<=100;j++){
                if(loopMemoize[i][j]==null)
                    System.out.print("  ");
                else System.out.print(loopMemoize[i][j]+" ");
            }
            System.out.println();
        }*/

        /*System.out.println();
        System.out.println();
        for(int i=1;i<=n;i++){
            for(int j=0;j<=100;j++){
                if(loopIndex[i][j]==null)
                    System.out.print("  ");
                else System.out.print(loopIndex[i][j]+" ");
            }
            System.out.println();
        }*/

        /*System.out.println();
        System.out.println();
        System.out.println();*/
        int sickness=0,segments=10;
        for(int i=n;i>0;i--){
            System.out.print(loopIndex[segments][sickness]+" ");
            sickness=sickness+parameters[loopIndex[segments][sickness]][1];
            segments--;
        }
        System.out.println();
        return x;
    }

    private int maxExcitementMemoize(int segment, int no_segments, int sickness) {
        if(no_segments==n || segment==k)
            return 0;
        if(memoize[segment][no_segments][sickness]!=null)
            return memoize[segment][no_segments][sickness];

        if(parameters[segment][1]+sickness<=100 && parameters[segment][1]+sickness>=0)
            return memoize[segment][no_segments][sickness]=
                    Math.max(parameters[segment][0]+maxExcitementMemoize(segment,no_segments+1,sickness+parameters[segment][1]),
                        Math.max(
                            maxExcitementMemoize(segment+1,no_segments, sickness),
                            parameters[segment][0]+maxExcitementMemoize(0,no_segments+1, sickness+parameters[segment][1])
                        )
                    );
        return memoize[segment][no_segments][sickness]=maxExcitementMemoize(segment+1,no_segments, sickness);
    }

    private int maxExcitementLoop(int no_segments, int sickness) {
        if(no_segments==0)
            return 0;
        if(loopMemoize[no_segments][sickness]!=null)
            return loopMemoize[no_segments][sickness];

        int max=-1;
        int i,index=-1;
        for(i=0;i<k;i++){
            if(parameters[i][1]+sickness<=100 && parameters[i][1]+sickness>=0){
                int take=parameters[i][0]+maxExcitementLoop(no_segments-1,sickness+parameters[i][1]);
                if(take>max){
                    max=take;
                    index=i;
                }
            }
        }

        loopIndex[no_segments][sickness]=index;
        return loopMemoize[no_segments][sickness]=max;
    }

    private int maxExcitementPath(int segment, int no_segments, int sickness, int excitement) {
        if(no_segments==n || segment==k){
            if(excitement>maxExcitement){
                maxExcitement=excitement;
                result.addAll(path);
            }
            return 0;
        }
        if(memoize[segment][no_segments][sickness]!=null)
            return memoize[segment][no_segments][sickness];

        if(parameters[segment][1]+sickness<=100 && parameters[segment][1]+sickness>=0){

            int take=parameters[segment][0]+maxExcitementMemoize(segment,no_segments+1,sickness+parameters[segment][1]);
            int skip=maxExcitementMemoize(segment+1,no_segments, sickness);
            int takeandFront=parameters[segment][0]+maxExcitementMemoize(0,no_segments+1, sickness+parameters[segment][1]);
        }

        return memoize[segment][no_segments][sickness]=maxExcitementMemoize(segment+1,no_segments, sickness);
    }

    public static void main(String[] args) {
        System.out.println(new RollerCoaster().maxExcitement(2,10,new int[][]{{100,50},{0,-10}}));
        System.out.println(new RollerCoaster().maxExcitement(2,10,new int[][]{{100,11},{10,1}}));
    }
}
