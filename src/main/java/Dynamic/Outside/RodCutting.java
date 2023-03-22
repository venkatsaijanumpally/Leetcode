package Dynamic.Outside;

public class RodCutting {
    /*
     * https://www.youtube.com/watch?v=SZqAQLjDsag
     * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
     * I have written the solution without taking length array and just using "n".
     */

    static Integer[][] memoize;
    static int cutRod(int price[],int n){
        memoize=new Integer[n+1][price.length+1];
        return cutRod(price,n,n);
    }

    static int cutRod(int price[], int Max_len, int n){
        if(n==0 || Max_len==0) return 0;

        if(memoize[n][Max_len]!=null) return memoize[n][Max_len];
        if(n<=Max_len)
            return memoize[n][Max_len]=Math.max(price[n-1]+cutRod(price,Max_len-n,n),
                    cutRod(price,Max_len,n-1));
        else return memoize[n][Max_len]=cutRod(price,Max_len,n-1);
    }

    public static void main(String[] args) {
        int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is " +
                cutRod(arr, size));
    }
}
