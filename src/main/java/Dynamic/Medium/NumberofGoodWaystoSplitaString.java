package Dynamic.Medium;

import java.util.HashMap;
import java.util.HashSet;

public class NumberofGoodWaystoSplitaString {
    public int numSplits(String s) {
        HashMap<Character, Integer> hm=new HashMap<>();

        for (int i=0;i<s.length();i++){
            hm.put(s.charAt(i),hm.getOrDefault(s.charAt(i),0)+1);
        }

        int count=0;
        HashSet<Character> left=new HashSet<>();
        for (int i=0;i<s.length();i++){
            left.add(s.charAt(i));
            int newValue=hm.get(s.charAt(i))-1;
            if(newValue==0)
                hm.remove(s.charAt(i));
            else hm.put(s.charAt(i),newValue);

            if(left.size()==hm.size())
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NumberofGoodWaystoSplitaString().numSplits("aacaba"));
        System.out.println(new NumberofGoodWaystoSplitaString().numSplits("abcd"));
    }
}
