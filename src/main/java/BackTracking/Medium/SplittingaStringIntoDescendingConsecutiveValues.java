package BackTracking.Medium;

import java.util.ArrayList;

public class SplittingaStringIntoDescendingConsecutiveValues {
    //https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values/discuss/1186896/JAVA-Brute-Force-greater-Recursion-Step-by-Step-oror-With-Detailed-Explanation
    public boolean splitString(String s) {
        //return splitString(s,1,0,null);
        return splitString(0,s,new ArrayList<>());
    }


    //!Optimal Solution
    public boolean splitString(int pos, String s, ArrayList<Long> arrayList){
        if(pos>=s.length()) return arrayList.size()>1;

        long val=0;
        for(int i=pos;i<s.length();i++){
            val=val*10+s.charAt(i)-'0';
            if(arrayList.isEmpty()||arrayList.get(arrayList.size()-1)-1==val){
                arrayList.add(val);
                if(splitString(i+1,s,arrayList)) return true;
                arrayList.remove(arrayList.size()-1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SplittingaStringIntoDescendingConsecutiveValues().splitString("53520515049"));
        System.out.println(new SplittingaStringIntoDescendingConsecutiveValues().splitString("050043"));
        System.out.println(new SplittingaStringIntoDescendingConsecutiveValues().splitString("9080701"));

    }
}
