package Dynamic.Knapsack01;


public class KeysKeyboard {
    Integer[][] memoize;
    /*
     * https://www.geeksforgeeks.org/2-keys-keyboard-problem/
     * For example: n=24
     * which is 2^3 X 3
     * The flow to reach 24 is  1->2->4->8->24
     * Since '2' is 3 times for 3 times we copy paste the sum.
     * The result generated after 2^3 which is 8. Using 8 we set the curr to 8 and paste it 2 times.
     *
     */
    public int minSteps(int n) {
        if(n==1) return 0;
        /*memoize=new Integer[n+1][n+1];
        return 1+recursive(n,1,1);*/


        int result=0;

        int curr=2;
        //Refer geeks link for explanation
        while (n>1){
            while (n%curr==0){
                result+=curr;
                n/=curr;
            }
            curr++;
        }
        return result;
    }

    private int recursive(int n, int curr, int sum) {
        if(n==sum) return 0;
        if(n<sum) return Integer.MAX_VALUE-1;
        if(memoize[curr][sum]!=null) return memoize[curr][sum];

        if(curr!=sum)
            return memoize[curr][sum]=Math.min(1+Math.min(recursive(n,sum,sum),recursive(n,curr,sum+curr)), Integer.MAX_VALUE-1);
        else
            return memoize[curr][sum]=Math.min(1+recursive(n,curr,sum+curr), Integer.MAX_VALUE-1);
    }

    public static void main(String[] args) {
        System.out.println(new KeysKeyboard().minSteps(3));
        System.out.println(new KeysKeyboard().minSteps(7));
        System.out.println(new KeysKeyboard().minSteps(10));
    }
}
