package OutsideLeetcode;

import java.util.HashMap;
import java.util.HashSet;

public class Test {
    public int longest(int[] arr){
        int MAX=0;
        int temp=0;
        for(int i=2;i<arr.length;i++){
            if(arr[i]>2*arr[i-1]-arr[i-2])
                temp++;
            else {
                MAX=Math.max(MAX,temp);
                temp=0;
            }
        }
        MAX=Math.max(MAX,temp);
        return MAX;
    }


    public int countOfSubsequence(int[] X, int[] Y, int k, int n){
        return recursion(X,Y,k-1,n-1);
    }

    private int recursion(int[] x, int[] y, int i, int j) {
        if(i<0)
            return 1;
        if(j<0)
            return 0;

        if(x[i]==y[j])
            return Math.max(1+recursion(x,y,i-1,j-1),recursion(x,y,i,j-1));

        return recursion(x,y,i,j-1);
    }

    private int recursionChar(String x, String y, int i, int j) {
        if(i<0)
            return 1;
        if(j<0)
            return 0;

        if(x.charAt(i)==y.charAt(j))
            return recursionChar(x,y,i-1,j-1)+recursionChar(x,y,i,j-1);

        return recursionChar(x,y,i,j-1);
    }

    private int recursionCharIterative(String x, String y, int k, int n) {
        int[][] t=new int[k+1][n+1];
        for(int i=0;i<n;i++)
            t[0][i]=1;
        for(int j=0;j<k;j++)
            t[j][0]=0;
        t[0][0]=1;

        for(int i=1;i<=k;i++){
            for(int j=1;j<=n;j++){
                if(x.charAt(i-1)==y.charAt(j-1))
                    t[i][j]=t[i-1][j-1]+t[i][j-1];
                else t[i][j]=t[i][j-1];
            }
        }
        return t[k][n];
    }

    private int maxProfitRestaurants(int[] m, int[] p, int n, int k){
        int[] t=new int[n];
        t[0]=p[0];
        for(int i=1;i<n;i++){
            int temp=0;
            for(int j=0;j<i;j++){
                if(m[i]-m[j]>=k)
                    temp=Math.max(temp,t[j]+p[i]);
                else temp=Math.max(temp,t[j]);
            }
            t[i]=temp;
        }
        return t[n-1];
    }


    private boolean isStringReconstructable(HashSet<String> hs, String s){
        int n=s.length();
        boolean[][] t=new boolean[n+1][n+1];
        //System.out.println(s.substring(2,2));
        for (int size=1;size<=n;size++){
            for(int i=1;i<=n-size+1;i++){
                int j=i+size-1;
                if(j-i==0)
                    t[i][j]=hs.contains(s.substring(i-1,j));
                else if(hs.contains(s.substring(i-1,j))){
                    t[i][j]=true;
                    System.out.println(s.substring(i-1,j));
                }
                else{
                    for(int k=i+1;k<=j;k++){
                        if (!t[i][j])
                            t[i][j]= t[i][k-1] && t[k][j];
                        else break;
                    }
                }
            }
        }
        return t[1][n];
    }

    //Works for single digit
    public int maxValueParenthesis(String equation){
        int[][] memoize=new int[equation.length()+1][equation.length()+1];
        for(int i=1;i<=equation.length();i=i+2){
            memoize[i][i]=Character.getNumericValue(equation.charAt(i-1));
        }

        for(int size=3;size<=equation.length();size+=2){
            for(int i=1;i<=equation.length()-size+1;i+=2){
                int j=size+i-1;
                int max=Integer.MIN_VALUE;
                for(int k=i;k<j;k=k+2){
                    System.out.println(equation.charAt(k));
                    switch (equation.charAt(k)){
                        case '+': {
                            max=Math.max(memoize[i][k]+memoize[k+2][j],max);
                            break;
                        }
                        case '-': {
                            max=Math.max(memoize[i][k]-memoize[k+2][j],max);
                            break;
                        }
                        case  '*': {
                            max=Math.max(memoize[i][k]*memoize[k+2][j],max);
                            break;
                        }
                        default : {max=Math.max(memoize[i][k]/memoize[k+2][j],max);break;}
                    }
                }
                memoize[i][j]=max;
            }
        }
        return memoize[1][equation.length()];
    }

    public int maxValueParenthesisAll(String equation){
        String[] arr=equation.split("[-+*/]");
        equation=equation.replaceAll("\\d","");
        char[] characters=new char[equation.length()];
        for(int i=0;i<equation.length();i++)
            characters[i]=equation.charAt(i);
        int[] arrint=new int[arr.length];
        for (int i=0;i<arr.length;i++)
            arrint[i]=Integer.parseInt(arr[i]);
        int[][] memoize=new int[arrint.length+1][arrint.length+1];
        for(int i=1;i<=arrint.length;i++)
            memoize[i][i]=arrint[i-1];
        for(int size=1;size<arrint.length;size++){
            for(int i=1;i<=arrint.length-size;i++){
                int j=i+size;
                int max=Integer.MIN_VALUE;
                for(int k=i;k<j;k++){
                    switch (characters[k-1]){
                        case '+':{
                            max=Math.max(memoize[i][k]+memoize[k+1][j],max);
                            break;
                        }
                        case '-':{
                            max=Math.max(memoize[i][k]-memoize[k+1][j],max);
                            break;
                        }
                        case '*':{
                            max=Math.max(memoize[i][k]*memoize[k+1][j],max);
                            break;
                        }
                        default:{
                            max=Math.max(memoize[i][k]/memoize[k+1][j],max);
                            break;
                        }
                    }
                }
                memoize[i][j]=max;
            }
        }
        return memoize[1][arrint.length];
    }

    public int min3DiagonalsTriangulation(int[] points, int n){
        //https://leetcode.com/problems/minimum-score-triangulation-of-polygon/discuss/286705/JavaC%2B%2BPython-DP
        int[][] dp = new int[n][n];
        for (int d = 2; d < n; ++d) {
            for (int i = 0; i + d < n; ++i) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + points[i] * points[j] * points[k]);
            }
        }
        return dp[0][n - 1];
    }

    public double probabilityA(int i, int j, int n){
        int A=n-i;
        int B=n-j;
        double[][] memoize=new double[A+1][B+1];
        for(int r=0;r<=B;r++)
            memoize[0][r]=1;
        for(int r=1;r<=A;r++){
            for(int c=1;c<=B;c++){
                memoize[r][c]=(memoize[r-1][c]+memoize[r][c-1])/2;
            }
        }
        return memoize[A][B];
    }

    public boolean possibleDenomination(int[] denomination, int v){
        boolean[] memoize=new boolean[v+1];
        memoize[0]=true;
        for(int i=1;i<=v;i++){
            for(int k=0;k<denomination.length && memoize[i]!=true;k++){
                if(i-denomination[k]>=0)
                    memoize[i]=memoize[i-denomination[k]];
            }
        }
        return memoize[v];
    }

    public boolean possibleDenominationK(int[] denomination, int v, int k){
        int[][] memoize=new int[denomination.length+1][v+1];
        for(int i=0;i<denomination.length+1;i++)
            memoize[i][0]=0;
        for(int i=1;i<=v;i++){
            for(int j=0;j<=denomination.length;j++)
                memoize[j][i]=Integer.MAX_VALUE-1;
        }
        for(int i=1;i<=v;i++){
            for(int j=1;j<=denomination.length;j++){
                if(i-denomination[j-1]>=0)
                    memoize[j][i]=Math.min(1+memoize[j][i-denomination[j-1]],memoize[j-1][i]);
                else memoize[j][i]=memoize[j-1][i];
            }
        }
        return memoize[denomination.length][v]<=k && memoize[denomination.length][v]>=0;
    }

    public static void main(String[] args) {
        int[] arr1={1,3,5,4,6,9,2,6,11,22};
        System.out.println(new Test().longest(arr1));
        System.out.println(new Test().recursionChar("Gks","GeeksforGeeks",2,12));
        System.out.println(new Test().recursionCharIterative("Gks","GeeksforGeeks",3,13));
        int[] m={1,4,6,7,9};
        int[] profit={2,3,2,12,3};
        System.out.println(new Test().maxProfitRestaurants(m,profit,5,2));
        HashSet<String> hs=new HashSet<>();
        hs.add("i");
        hs.add("really");
        hs.add("like");
        hs.add("dynamic");
        hs.add("programming");
        hs.add("problems");
        System.out.println(new Test().isStringReconstructable(hs,"ireallylikedynamicprogrammingproblems"));
        String s="ireallylikedynamicprogrammingproblems";
        System.out.println(hs.contains(s.substring(0,1)));
        System.out.println(new Test().maxValueParenthesisAll("12+34-10+5-3-7"));
        System.out.println(new Test().probabilityA(7,5,8));
        int[] deno=new int[]{5,10};
        int[] deno2=new int[]{5,8,10,13};
        System.out.println(new Test().possibleDenomination(deno,15));
        System.out.println(new Test().possibleDenomination(deno,12));
        System.out.println(new Test().possibleDenomination(deno2,22));
        System.out.println(new Test().possibleDenomination(deno2,38));

        System.out.println();
        System.out.println(new Test().possibleDenominationK(deno,55,6));
        System.out.println(new Test().possibleDenominationK(deno,60,6));
        System.out.println(new Test().possibleDenominationK(deno,65,6));

        //Q15 is Coin Change
        //Q16 is find sum of entire array
        // now traverse from left tto right of array and find 3 partitions when trying to find first sum/3 if the current
        //array sum goes beyong the sum/3 then return false else go to next partition and so on.
    }
}
