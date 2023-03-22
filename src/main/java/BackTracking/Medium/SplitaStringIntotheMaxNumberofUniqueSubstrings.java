package BackTracking.Medium;

import java.util.ArrayList;
import java.util.HashSet;

public class SplitaStringIntotheMaxNumberofUniqueSubstrings {
    int maxSplit=0;
    public int maxUniqueSplit(String s) {
        maxUniqueSplit(s,0, new HashSet<>());
        return maxSplit;
    }

    public HashSet<String> maxUniqueSplit(String s, int index, HashSet<String> hs){
        if(index==s.length()) {
            if(hs.size()>maxSplit)
                maxSplit= hs.size();
            return hs;
        }

        for(int i=index+1;i<=s.length();i++){
            String st=s.substring(index,i);
            if(!hs.contains(st)){
                hs.add(st);
                maxUniqueSplit(s,i,hs);
                hs.remove(st);
            }
        }
        return hs;
    }

    public static void main(String[] args) {
        ArrayList<StringBuilder> s=new ArrayList<>();
        s.add(new StringBuilder("a"));
        ArrayList<String> string=new ArrayList<>();
        string.add("ab");
        System.out.println(new SplitaStringIntotheMaxNumberofUniqueSubstrings().maxUniqueSplit("ababccc"));
    }
}
