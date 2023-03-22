package Dynamic.Knapsack01;

public class OnesandZeroes {
    Integer[][][] memoize;
    public int findMaxForm(String[] strs, int m, int n) {
        memoize=new Integer[strs.length][m+1][n+1];
        int[] zeros=new int[strs.length];
        int[] ones=new int[strs.length];
        for(int i=0;i< strs.length;i++){
            for(int j=0;j<strs[i].length();j++){
                if(strs[i].charAt(j)=='0') zeros[i]++;
                else ones[i]++;
            }
        }
        return findMaxForm(zeros,ones,m,n, strs.length-1);
    }

    public int findMaxForm(int[] zeros, int[] ones, int m, int n, int index){
        if(index==-1) return 0;

        if(memoize[index][m][n]!=null) return memoize[index][m][n];
        if(zeros[index]<=m && ones[index]<=n)
            return memoize[index][m][n]=Math.max(1+findMaxForm(zeros,ones,m-zeros[index],n-ones[index],index-1),
                    findMaxForm(zeros,ones,m,n,index-1));
        else return memoize[index][m][n]=findMaxForm(zeros,ones,m,n,index-1);
    }

    public static void main(String[] args) {
        String[] arr={"10","0001","111001","1","0"};
        String[] arr1={"10","0","1"};
        System.out.println(new OnesandZeroes().findMaxForm(arr,5,3));
        System.out.println(new OnesandZeroes().findMaxForm(arr1,1,1));
    }
}
