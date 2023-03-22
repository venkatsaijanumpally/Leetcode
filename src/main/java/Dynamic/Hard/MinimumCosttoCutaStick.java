package Dynamic.Hard;

public class MinimumCosttoCutaStick {
    public static void main(String args[]) {
        int[] arr={0,2,4,7,8};
        int[] arr1={1,3,4,5};
        //System.out.println(maxCost(4,arr));
        //System.out.println(maxCostRecursive(4,arr));
        System.out.println(maxCostRecursive(7,arr1));
    }
    public static int maxCost(int n, int[] cuts) {
        int[] arr=new int[n+1];
        arr[0]=cuts[0];
        for(int i=1;i<=n;i++){
            int MAXVALUE=Integer.MIN_VALUE;
            for(int j=1;j<=i;j++)
                MAXVALUE=Math.max(MAXVALUE,cuts[j]+arr[i-j]);
            arr[i]=MAXVALUE;
        }
        return arr[n];
    }

    public static int maxCostRecursive(int n, int[] cuts){
        return maxCost(cuts,n,cuts.length);
    }

    static int maxCost(int price[], int Max_len, int index){
        if(index==0 || Max_len==0) return 0;

        if(index<=Max_len)
            return Math.max(price[index-1]+maxCost(price,Max_len-index,index),
                    maxCost(price,Max_len,index-1));
        else return maxCost(price,Max_len,index-1);
    }
}
