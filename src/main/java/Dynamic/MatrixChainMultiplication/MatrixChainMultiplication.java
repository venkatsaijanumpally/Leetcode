package Dynamic.MatrixChainMultiplication;

public class MatrixChainMultiplication {
    /*
     * https://www.youtube.com/watch?v=kMK148J9qEE&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=33
     * https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
     */

    public int solve(int[] arr, int i, int j){
        if(i>=j) return 0;

        int res=Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int temp=solve(arr,i,k)+solve(arr,k+1,j)+arr[i-1]*arr[k]*arr[j];
            if(temp<res) res=temp;
        }
        return res;
    }

    Integer[][] memoize;
    public int solveMemoize(int[] arr){
        memoize=new Integer[arr.length][arr.length];
        return solveMemoize(arr,1,arr.length-1);
    }

    public int solveMemoize(int[] arr, int i, int j){
        if(i>=j) return 0;
        if(memoize[i][j]!=null) return memoize[i][j];

        int res=Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int temp=solveMemoize(arr,i,k)+solveMemoize(arr,k+1,j)+arr[i-1]*arr[k]*arr[j];
            if(temp<res) res=temp;
        }
        return memoize[i][j]=res;
    }

    public int TopDown(int[] arr){
        /*
         * Form the memoization matrix size as [arr.length][arr.length] it is easier in this way.
         */
        int[][] memoize=new int[arr.length][arr.length];
        int n= arr.length;

        for(int Length=2;Length<arr.length;Length++){
            for(int i=1;i<n-Length+1;i++){
                int j=i+Length-1;
                memoize[i][j] = Integer.MAX_VALUE;
                for(int k=i;k<j;k++){
                    int val = memoize[i][k]+memoize[k+1][j]+ arr[i-1]*arr[k]*arr[j];
                    memoize[i][j] = Math.min(memoize[i][j], val);
                }
            }
        }

        return memoize[1][arr.length-1];
    }

    public static void main(String[] args) {
        int[] arr={40,20,30,10,30};
        System.out.println(new MatrixChainMultiplication().solve(arr,1,arr.length-1));
        System.out.println(new MatrixChainMultiplication().solveMemoize(arr));
        System.out.println(new MatrixChainMultiplication().TopDown(arr));
    }
}
