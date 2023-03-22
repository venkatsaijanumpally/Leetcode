package Enumeration.Medium;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits {
    int count=0;
    ArrayList<Integer> list=new ArrayList<>();
    public List<Integer> sequentialDigits(int low, int high) {
        String lowStr=String.valueOf(low);
        String highStr=String.valueOf(high);
        int val=low;
        int lowLength=lowStr.length();
        while (lowLength>1){
            val%=10;
            lowLength--;
        }
        int incrementVal=val;
        generateLowValues(val,low,high);
        //TODO
        return list;
    }

    private void generateLowValues(int val,int low, int high) {

    }
}
