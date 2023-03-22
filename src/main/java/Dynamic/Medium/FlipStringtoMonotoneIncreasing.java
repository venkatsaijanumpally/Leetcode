package Dynamic.Medium;

import java.util.Arrays;

public class FlipStringtoMonotoneIncreasing {
    public int minFlipsMonoIncr(String s) {
        int[] zeros=new int[s.length()];
        int[] ones=new int[s.length()];

        int numOnes=0;
        int numZeros=0;

        /*zeros[0]=s.charAt(0)=='0'?1:0;
        ones[0]=s.charAt(0)=='1'?1:0;*/
        if(s.charAt(0)=='0'){
            zeros[0]=1;
            numZeros++;
        }
        else {
            ones[0]=1;
            numOnes++;
        }

        for(int i=1;i<s.length();i++){
            if(s.charAt(i)=='1'){
                ones[i]=ones[i-1]+1;
                zeros[i]=zeros[i-1];
                numOnes++;
            }
            else{
                ones[i]=ones[i-1];
                zeros[i]=zeros[i-1]+1;
                numZeros++;
            }
        }
        System.out.println(Arrays.toString(zeros));
        System.out.println(Arrays.toString(ones));

        if(numOnes==s.length() || numZeros==s.length())
            return 0;

        int min=Integer.MAX_VALUE;
        for(int i=s.length()-1;i>=0;i--){
            int edits=ones[i]+(numZeros-zeros[i]);
            min=Math.min(edits,min);
        }

        //Last case making all chars 1
        min=Math.min(min,numZeros);

        return min;
    }

    public int minFlipsMonoIncr2(String s){
        int numOnes=0;
        int numZeros=0;

        int sum=0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)=='0')
                sum++;
        }

        int min=Integer.MAX_VALUE;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1')
                numOnes++;
            else numZeros++;
            int edits=numOnes+(sum-numZeros);
            min=Math.min(edits,min);
        }

        //Last case making all chars 1
        min=Math.min(min,numZeros);

        return min;
    }

    public static void main(String[] args) {
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr("00110"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr("010110"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr("0011000"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr("0000000"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr("1111"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr("11011"));

        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr2("00110"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr2("010110"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr2("0011000"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr2("0000000"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr2("1111"));
        System.out.println(new FlipStringtoMonotoneIncreasing().minFlipsMonoIncr2("11011"));
    }
}
