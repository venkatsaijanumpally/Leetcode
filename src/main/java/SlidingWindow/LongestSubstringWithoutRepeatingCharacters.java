package SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstringOptimalHashmap("bbbbbaabb"));
        char c='1';
        //System.out.println((int)c);
        System.out.println("1-9: "+(int)'1'+(int)'9');
        System.out.println((int)'a');
        System.out.println((int)'z');
        System.out.println((int)'A');
        System.out.println((int)'Z');
        System.out.println((int)'!');
        System.out.println((int)'@');
        System.out.println((int)'&');
        System.out.println((int)')');
        System.out.println((int)']');
    }
    public int lengthOfLongestSubstringOptimal(String s){
        if(s.length()==0)
            return 0;
        HashMap<Character, Integer> lastIndex=new HashMap<>();
        int p1=0,p2=0,c=1;
        while(p2<s.length())
        {
            if(p2==p1)
            {lastIndex.put(s.charAt(p2),p2);p2++;}
            else if(lastIndex.containsKey(s.charAt(p2)))
            {
                int latestindex=lastIndex.get(s.charAt(p2));
                if(c<p2-p1)
                    c=p2-p1;
                if(latestindex>=p1){
                    p1=latestindex+1;
                }
                lastIndex.put(s.charAt(p2),p2);
                p2++;
            }
            else
            {
                lastIndex.put(s.charAt(p2),p2);
                p2++;
            }
        }
        if(c<p2-p1)
            c=p2-p1;
        return c;
    }
    public int lengthOfLongestSubstringOptimal1(String s){
        if(s.length()==0)
            return 0;
        int[] arr=new int[128];
        Arrays.fill(arr,0);
        int p1=0,p2=0,c=1;
        while(p2<s.length())
        {
            char ch=s.charAt(p2);
            arr[ch]++;
            while (arr[ch]>1){
                arr[s.charAt(p1)]--;
                p1++;
            }
            c=Math.max(c,p2-p1+1);
            p2++;
        }
        return c;
    }

    public int lengthOfLongestSubstringOptimalHashmap(String s){
        if(s.length()==0)
            return 0;
        HashMap<Character, Integer> hm=new HashMap<>();
        int p1=0,p2=0,c=1;
        /*while(p2<s.length())
        {
            char ch=s.charAt(p2);
            if(hm.containsKey(ch))
                hm.put(ch,hm.get(ch)+1);
            else
                hm.put(ch,1);

            while (hm.get(ch)>1){
                char temp=s.charAt(p1);
                hm.put(temp,hm.get(temp)-1);
                if(hm.get(temp)==0) hm.remove(temp);
                p1++;
            }
            c=Math.max(c,p2-p1+1);
            p2++;
        }*/
        for(;p2<s.length();p2++){
            char ch=s.charAt(p2);
            if(hm.containsKey(ch)){
                p1=Math.max(p1,hm.get(ch)+1);
            }
            hm.put(ch,p2);
            c=Math.max(c,p2-p1+1);
        }
        return c;
    }


    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> hm=new HashMap<Character, Integer>();
        int p1=0,p2=0,c=1;
        if(s.length()==0)
            return 0;
        else{
            while(p2<s.length())
            {
                if(p2==p1)
                {hm.put(s.charAt(p2),p2);p2++;}
                else if(hm.containsKey(s.charAt(p2)))
                {
                    if(c<p2-p1)
                        c=p2-p1;
                    //p1=hm.get(p2)+1;
                    int endindex=hm.get(s.charAt(p2));
                    for(int i=p1;i<endindex;i++)
                        hm.remove(s.charAt(i));
                    p1=hm.get(s.charAt(p2))+1;
                    hm.put(s.charAt(p2),p2);
                    p2++;
                }
                else
                {
                    hm.put(s.charAt(p2),p2);
                    p2++;
                }
            }
            if(c<p2-p1)
                c=p2-p1;
        }
        return c;
    }
}
