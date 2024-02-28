package Dynamic.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> words = new HashSet<>(wordDict);
        boolean[][] isWord = new boolean[s.length()][s.length()];

        for (int len = 1; len<=s.length();len++){
            for (int i=0;i<=s.length()-len;i++){
                int j = i+len;
                if(words.contains(s.substring(i,j)))
                    isWord[i][j-1]=true;
            }
        }

        //return recursive(words,s,0,s.length()-1);
        return recursive(isWord,s,0,s.length()-1);
    }

    private List<String> recursive(boolean[][] isWord, String s, int start, int end) {
        ArrayList<String> res = new ArrayList<>();
        if(start == end){
            if(isWord[start][start])
                res.add(String.valueOf(s.charAt(start)));
            return res;
        }

        if(isWord[start][end])
            res.add(s.substring(start,end+1));
        for(int i=start;i<end && i<start+10;i++){
            String left = s.substring(start,i+1);
            if(!isWord[start][i]) continue;
            List<String> right = recursive(isWord, s, i+1, end);

            if(right.isEmpty())
                continue;
            for (String r1: right){
                res.add(left +" "+r1);
            }
        }
        return res;
    }

    private ArrayList<String> recursive(HashSet<String> wordDict, String s, int start, int end) {
        ArrayList<String> res = new ArrayList<>();
        if(start == end){
            if(wordDict.contains(String.valueOf(s.charAt(start))))
                res.add(String.valueOf(s.charAt(start)));
            return res;
        }

        if(wordDict.contains(s.substring(start,end+1)))
            res.add(s.substring(start,end+1));
        for(int i=start;i<end && i<start+10;i++){
            String left = s.substring(start,i+1);
            if(!wordDict.contains(left)) continue;
            ArrayList<String> right = recursive(wordDict, s, i+1, end);

            if(right.isEmpty())
                continue;
            for (String r1: right){
                res.add(left +" "+r1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> dict1 = convertToList(new String[]{"cat","cats","and","sand","dog"});
        List<String> dict2 = convertToList(new String[]{"apple","pen","applepen","pine","pineapple"});
        List<String> dict3 = convertToList(new String[]{"a"});
        System.out.println(new WordBreakII().wordBreak("catsanddog",dict1));
        System.out.println(new WordBreakII().wordBreak("pineapplepenapple",dict2));
        System.out.println(new WordBreakII().wordBreak("a",dict3));
    }

    private static List<String> convertToList(String[] strings) {
        return Arrays.asList(strings);
    }
}


/*
for(int i=start;i<end && i<start+10;i++){
        ArrayList<String> left = recursive(wordDict, s, start, i);
        ArrayList<String> right = recursive(wordDict, s, i+1, end);

        if(left.isEmpty() || right.isEmpty())
        continue;
        for (String l1: left){
        for (String r1: right){
        res.add(l1+" "+r1);
        }
        }
        }*/
