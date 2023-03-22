package Dynamic.GridTraversal.Hard;

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int m=dungeon.length,n=dungeon[0].length;
        int[][] memoize=new int[m][n];

        memoize[m-1][n-1]=dungeon[m-1][n-1]>0?0:(-dungeon[m-1][n-1]+1);

        for(int i=n-2;i>=0;i--) {
            if (dungeon[m - 1][i] < 0)
                memoize[m-1][i]=Math.max(- dungeon[m - 1][i]+1, memoize[m - 1][i + 1] - dungeon[m - 1][i]);
            else
                memoize[m-1][i]=Math.max(memoize[m-1][i+1]-dungeon[m-1][i],0);
        }
        for(int i=m-2;i>=0;i--) {
            if (dungeon[i][n-1] < 0)
                memoize[i][n-1]=Math.max(-dungeon[i][n-1]+1, memoize[i+1][n-1] - dungeon[i][n-1]);
            else
                memoize[i][n-1]=Math.max(memoize[i+1][n-1]-dungeon[i][n-1],0);
        }

        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                int r,d;
                if(dungeon[i][j]>=0){
                    if(memoize[i+1][j]==0) d=0;
                    else d=Math.max(memoize[i+1][j]-dungeon[i][j], 0);

                    if(memoize[i][j+1]==0) r=0;
                    else r=Math.max(memoize[i][j+1]-dungeon[i][j], 0);
                }
                else {
                    int val=-dungeon[i][j];
                    d=Math.max(val+1, memoize[i+1][j]+val);
                    r=Math.max(val+1, memoize[i][j+1]+val);
                }
                memoize[i][j]=Math.min(d,r);
            }
        }
        return Math.max(memoize[0][0],1);
    }

    public static void main(String[] args) {
        int[][] arr={{-2,-3,3},{-5,-10,1},{10,30,-5}};
        int[][] arr1={{0}};
        int[][] arr2={{-3,5}};
        int[][] arr3={{-1},{5}};
        System.out.println(new DungeonGame().calculateMinimumHP(arr2));
        System.out.println(new DungeonGame().calculateMinimumHP(arr3));
        System.out.println(new DungeonGame().calculateMinimumHP(arr));
        System.out.println(new DungeonGame().calculateMinimumHP(arr1));
    }
}
