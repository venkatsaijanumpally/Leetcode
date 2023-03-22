package Dynamic.Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class LongestStringChain {


    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] maxTill=new int[words.length];
        Arrays.fill(maxTill,1);
        int max=1;
        for(int i=1;i<words.length;i++){
            for(int j=i-1;j>=0;j--){
                if(words[j].length()+1==words[i].length()){
                    int p1=0,p2=0;
                    //Find first mismatch
                    while (p2<words[i].length() && p1<words[j].length()){
                        if(words[j].charAt(p1)!=words[i].charAt(p2))
                            break;
                        p1++;
                        p2++;
                    }

                    //Ignore the mismatch and mov to next index
                    p2++;

                    //all the next indexes should match else the strings dont match
                    while (p2<words[i].length() && p1<words[j].length()){
                        if(words[j].charAt(p1)!=words[i].charAt(p2))
                            break;
                        p1++;
                        p2++;
                    }
                    if(p1==words[j].length() && p2==words[i].length()){
                        maxTill[i]=Math.max(maxTill[i],maxTill[j]+1);
                        if(max<maxTill[i])max=maxTill[i];
                    }
                }
            }
        }
        return max;
    }


    HashMap<String,Integer> map=new HashMap<>();
    public int longestStrChain2(String[] words) {
        //Sort strings based on length
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for(String word:words){
            map.put(word,1);
        }

        int overallMax=1;
        for(String word: words){
            int max=1;
            for(int i=0;i< word.length();i++){
                //Try to remove a character and check if it exists
                String str= word.substring(0,i)+word.substring(i+1);
                if(map.containsKey(str) && map.get(str)+1>max)
                    max=map.get(str)+1;
            }
            map.put(word,max);
            if(overallMax<max)
                overallMax=max;
        }
        return overallMax;
    }


    public static void main(String[] args) {
        String[] words1={"a","b","ba","bca","bda","bdca"};
        String[] words2= {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        String[] words3= {"a","b","ab","bac"};
        System.out.println(new LongestStringChain().longestStrChain(words1));
        System.out.println(new LongestStringChain().longestStrChain(words2));
        System.out.println(new LongestStringChain().longestStrChain(words3));
        System.out.println(new LongestStringChain().longestStrChain2(words1));
        System.out.println(new LongestStringChain().longestStrChain2(words2));
        System.out.println(new LongestStringChain().longestStrChain2(words3));
    }
}
