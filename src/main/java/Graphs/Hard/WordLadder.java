package Graphs.Hard;

import Graphs.Utils.WordPair;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord))
            return 0;
        set.remove(beginWord);

        Queue<WordPair> queue=new LinkedList<>();
        queue.add(new WordPair(beginWord,1));
        while (!queue.isEmpty()){
            WordPair pair=queue.poll();
            if(pair.str.equals(endWord)) return pair.steps;
            for(int i=0;i<pair.str.length();i++){
                for(char c='a';c<='z';c++){
                    if(pair.str.charAt(i)==c)
                        continue;
                    char[] string=pair.str.toCharArray();
                    string[i]=c;
                    String replacedString=String.valueOf(string);
                    if(set.contains(replacedString)){
                        queue.add(new WordPair(replacedString, pair.steps+1));
                        set.remove(replacedString);
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] wordList1={"hot","dot","dog","lot","log","cog"};
        String[] wordList2={"hot","dot","dog","lot","log"};
        List<String> list= new ArrayList<>(List.of(wordList1));
        List<String> list2= new ArrayList<>(List.of(wordList2));
        System.out.println(new WordLadder().ladderLength("hit","cog",list));
        System.out.println(new WordLadder().ladderLength("hit","cog",list2));
    }
}
