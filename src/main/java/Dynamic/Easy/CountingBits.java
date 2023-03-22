package Dynamic.Easy;

import java.util.ArrayList;
import java.util.Arrays;

public class CountingBits {
    public static int[] countBits(int n) {
        int[] arr=new int[n+1];
        int pow=1;
        int p=1;
        for(int i=0;i<=n;i++){
            if(i==pow){
                p=1;
                arr[i]=1;
                pow<<=1;
            }
            else{
                arr[i]=arr[p]+1;
                p++;
            }
        }
        return arr;
    }
    public static void main(String args[]) {
        int j=0;
        String s="ahhbdmsc";
        String t="abc";
        for(int i=0;i<s.length()&&j<t.length();i++)
            if(s.charAt(i)==t.charAt(j))
                j++;
        if(j==t.length())
            System.out.println("true");
        System.out.println("false");
        System.out.println(Arrays.toString(countBits(12)));
    }
}
