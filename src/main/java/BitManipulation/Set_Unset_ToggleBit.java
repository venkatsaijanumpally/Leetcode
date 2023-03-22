package BitManipulation;

public class Set_Unset_ToggleBit {
    public static void main(String[] args) {
        int n;
        n=set(18,2);
        System.out.println("Set:"+n);
        n=unset(18,1);
        System.out.println("Unset:"+n);
        n=toggle(18,1);
        System.out.println("Toggle:"+n);
        n=toggle(18,0);
        System.out.println("Toggle:"+n);

        System.out.println("check if it is power of 2:"+checkpowerof2(16));
        System.out.println("check if it is power of 2:"+checkpowerof2(18));

        System.out.println("Remove last set bit:"+removeLastSetBit(18));

        System.out.println("count no of set bits:"+countNoOfSetBits(10));

        System.out.println("find last set bit:"+findRightmostSetBit(10));



        System.out.println(Integer.toBinaryString(-2147483648));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2147483647));

        int test=2147483647;
        while (test!=0){
            System.out.println(Integer.toBinaryString(test));
            System.out.println(test);
            test>>=1;
        }
    }

    private static int findRightmostSetBit(int i) {
        return (i^i-1)&i;
    }

    private static int countNoOfSetBits(int n) {
        int res=0;
        while (n>0){
            n=n&n-1;
            res++;
        }
        return res;
    }

    private static int removeLastSetBit(int i) {
        return i&(i-1);
    }

    private static boolean checkpowerof2(int i) {
        return (i&(i-1))==0;
    }

    private static int unset(int n, int k) {
        int temp=1<<k;
        int not=~temp;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(not));
        System.out.println("test:"+(n&not));
        System.out.println("Temp:"+temp);
        System.out.println("Temp Not:"+~temp);
        return n&~temp;
    }

    private static int set(int n, int k) {
        return n|(1<<k);
    }

    private static int toggle(int n, int k) {
        //int temp=1<<k;
        //temp=n&temp;
        //if(temp>0) return unset(n,k);
        //return set(n,k);
        return n^(1<<k);
    }


}
