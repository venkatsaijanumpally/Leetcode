package Arrays;

import java.util.*;

public class AddtoArrayFormofInteger {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> list = new ArrayList<>();
        String s = String.valueOf(k);
        //String[] newNum = s.split("");*/
        int[] newArr = new int[s.length()];
        /*for (int i = 0; i < newArr.length; i++) {
            newArr[i] = Integer.parseInt(newNum[i]);
        }*/
        int newI=s.length()-1;
        while (k>0){
            newArr[newI--]=k%10;
            k/=10;
        }


        int minSize = Math.min(newArr.length, num.length);
        int carry = 0;
        int index1 = num.length - 1;
        int index2 = newArr.length - 1;
        for (int i = minSize - 1; i >= 0; i--) {
            int sum = carry + num[index1] + newArr[index2];
            list.add(0,sum%10);
            carry=sum/10;
            index1--;
            index2--;
        }

        int[] arr=num;
        int index=index1;
        if(index1==-1){
            index=index2;
            arr=newArr;
        }
        while (index>=0){
            int sum=carry+arr[index--];
            list.add(0,sum%10);
            carry=sum/10;
        }

        if(carry!=0)
            list.add(0,carry);

        return list;
    }

    public static void main(String[] args) {
        System.out.println(new AddtoArrayFormofInteger().addToArrayForm(new int[]{1, 2,1, 3}, 9487));
    }
}
