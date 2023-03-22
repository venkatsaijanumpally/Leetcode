package Dynamic.Medium;

public class ChampagneTower {
    double[][] memoize;
    public double champagneTower(int poured, int query_row, int query_glass) {
        memoize=new double[query_row+1][query_row+1];
        /*recursive(poured,0,0);
        return memoize[query_row][query_glass];*/
        return topdown(poured,query_row,query_glass);
    }

    private double topdown(double poured, int row, int glass){
        double[][] memoize=new double[row+2][row+2];

        row++;glass++;
        memoize[1][1]=poured;
        for(int i=2;i<memoize.length;i++){
            for(int j=1;j<=i;j++){
                if(memoize[i-1][j]>1)
                    memoize[i][j]+=(memoize[i-1][j]-1)/2;
                if(memoize[i-1][j-1]>1)
                    memoize[i][j]+=(memoize[i-1][j-1]-1)/2;
            }
        }

        if(memoize[row][glass]>1) return 1d;
        return memoize[row][glass];
    }

    private void recursive(double poured, int row, int glass) {
        if(poured==0 || row==memoize.length)
            return;

        if(poured>1d-memoize[row][glass]){
            poured=poured-(1d-memoize[row][glass]);
            memoize[row][glass]=1d;
            recursive(poured/2,row+1,glass);
            recursive(poured/2,row+1,glass+1);
        }
        else memoize[row][glass]+=poured;
    }

    public static void main(String[] args) {
        System.out.println(new ChampagneTower().champagneTower(2,1,1));
        System.out.println(new ChampagneTower().champagneTower(1,1,1));
        System.out.println(new ChampagneTower().champagneTower(9,4,2));
        System.out.println(new ChampagneTower().champagneTower(100000009,33,17));
    }
}
